package by.bsu.geneticalgorithm.algorithm;

import by.bsu.geneticalgorithm.mathhelp.MathHelp;

import static by.bsu.geneticalgorithm.algorithm.AlgorithmConstants.*;

public class Individual {
    private int []genes;
    private double fitness;
    private double probability;

    public Individual() {
        this.genes = new int[COUNT_OF_VALUES];
    }

    public Individual(int []genes) {
        this.genes = genes;
    }

    public int[] getGenes() {
        return genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    private int function(int x, int y, int z) {
        return x * x * y * y * z * z - 2*x*y -3*x -4*y - z;
    }

    public double computeFitness() {
        int x = genes[0];
        int y = genes[1];
        int z = genes[2];
        int proximity = Math.abs(function(x, y, z) - TARGET_VALUE_OF_FUNCTION);
        if (0 != proximity) {
            return 1.0d / (double) proximity;
        } else {
            return -1.0d;
        }
    }

    private int getCrossoverLine() {
        return MathHelp.getRandomInt(0, COUNT_OF_VALUES - 2);
    }

    private Individual[] getChildren(Individual individual) {
        int crossoverLine = getCrossoverLine();
        Individual[] children = new Individual[2];
        children[0] = new Individual();
        children[1] = new Individual();
        for (int i = 0; i < COUNT_OF_VALUES; i++) {
            if (i <= crossoverLine) {
                children[0].getGenes()[i] = this.getGenes()[i];
                children[1].getGenes()[i] = individual.getGenes()[i];
            } else {
                children[0].getGenes()[i] = individual.getGenes()[i];
                children[1].getGenes()[i] = this.getGenes()[i];
            }
        }
        return children;
    }

    public Individual singlePointCrossing(Individual individual) {
        Individual[] children = getChildren(individual);
        int randomChildNumber = MathHelp.getRandomInt(0, 1);
        return children[randomChildNumber];
    }

    public Individual mutation() {
        int randomMaximumBorder = 100;
        for (int i = 0; i < COUNT_OF_VALUES; i++) {
            double randomPercent = MathHelp.getRandomDouble(0, randomMaximumBorder);
            if (randomPercent < MUTATION_PROBABILITY) {
                int newGene = AlgorithmConstants.getRandomGene();
                this.getGenes()[i] = newGene;
            }
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder values = new StringBuilder();
        values.append("[x, y, z] = [");
        for (int i = 0; i < COUNT_OF_VALUES; ++i) {
            values.append(genes[i]);
            values.append(i < COUNT_OF_VALUES - 1 ? ", " : "");
        }
        values.append("]");
        return values.toString();
    }
}