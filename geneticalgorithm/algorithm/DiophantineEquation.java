package by.bsu.geneticalgorithm.algorithm;

import by.bsu.geneticalgorithm.mathhelp.MathHelp;

import static by.bsu.geneticalgorithm.algorithm.AlgorithmConstants.*;

public class DiophantineEquation {
    private Individual []population;
    private int countOfIterations;

    public DiophantineEquation() {
        this.population  = new Individual[COUNT_OF_POPULATION];
    }

    public Individual[] getPopulation() {
        return population;
    }

    public int getCountOfIterations() {
        return countOfIterations;
    }

    private void fillIndividualByRandomGenes(Individual individual) {
        for (int i = 0; i < COUNT_OF_VALUES; i++) {
            individual.getGenes()[i] = AlgorithmConstants.getRandomGene();
        }
    }

    private void createInitialPopulation() {
        for (int i = 0; i < COUNT_OF_POPULATION; i++) {
            population[i] = new Individual();
            fillIndividualByRandomGenes(population[i]);
        }
    }

    private int fillIndividualFitnesses() {
        for (int i = 0; i < COUNT_OF_POPULATION; i++) {
            double actualFitness = population[i].computeFitness();
            population[i].setFitness(actualFitness);
            if (actualFitness == -1) {
                return i;
            }
        }
        return -2;
    }

    private double getSumOfAllIndividualFitnesses() {
        double sumOfAllIndividualFitnesses = 0.0d;
        for (int i = 0; i < COUNT_OF_POPULATION; i++) {
            sumOfAllIndividualFitnesses += population[i].getFitness();
        }
        return sumOfAllIndividualFitnesses;
    }

    private void fillIndividualProbability() {
        double sumOfAllIndividualFitnesses = getSumOfAllIndividualFitnesses();
        double lastProbability = 0.0d;
        for (int i = 0; i < COUNT_OF_POPULATION; i++) {
            double probability = lastProbability + (100 * population[i].getFitness() / sumOfAllIndividualFitnesses);
            lastProbability = probability;
            population[i].setProbability(probability);
        }
    }

    private int getRandomIndividual(double random) {
        int i;
        for (i = 0; i < COUNT_OF_POPULATION; i++) {
            if (random <= population[i].getProbability()) {
                return i;
            }
        }
        return i - 1;
    }

    private int[][] getPairsForCrossover() {
        int secondDimension = 2;
        int firstRandomBorder = 0;
        int secondRandomBorder = 100;
        int[][] pairs = new int[COUNT_OF_POPULATION][secondDimension];
        for (int i = 0; i < COUNT_OF_POPULATION; i++) {
            double random = MathHelp.getRandomDouble(firstRandomBorder, secondRandomBorder);
            int firstIndividual = getRandomIndividual(random);
            int secondIndividual;
            do {
                random = MathHelp.getRandomDouble(firstRandomBorder, secondRandomBorder);
                secondIndividual = getRandomIndividual(random);
            } while (firstIndividual == secondIndividual);
            pairs[i][0] = firstIndividual;
            pairs[i][1] = secondIndividual;
        }
        return pairs;
    }

    private Individual[] doCrossoverAndMutation(int[][] pairs) {
        Individual []nextGeneration = new Individual[COUNT_OF_POPULATION];
        for (int i = 0; i < COUNT_OF_POPULATION; i++) {
            Individual firstParent = population[pairs[i][0]];
            Individual secondParent = population[pairs[i][1]];
            Individual child = firstParent.singlePointCrossing(secondParent);
            nextGeneration[i] = child;
            nextGeneration[i] = nextGeneration[i].mutation();
        }
        return nextGeneration;
    }

    public Individual solveEquation() {
        this.createInitialPopulation();
        Individual individual = null;
        while (countOfIterations < MAXIMUM_ITERATIONS) {
            int individualWithFitnesses = this.fillIndividualFitnesses();
            if (individualWithFitnesses != -2) {
                individual = this.getPopulation()[individualWithFitnesses];
                break;
            }
            this.fillIndividualProbability();
            int[][] pairs = this.getPairsForCrossover();
            this.population = this.doCrossoverAndMutation(pairs);
            countOfIterations++;
        }
        if (individual == null) {
            return new Individual(new int[]{-1, -1, -1});
        }
        return individual;
    }
}