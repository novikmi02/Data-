package by.bsu.geneticalgorithm.algorithm;

import by.bsu.geneticalgorithm.mathhelp.MathHelp;

public class AlgorithmConstants {
    public static final int MINIMUM_GENE = 1;
    public static final int MAXIMUM_GENE = 40;
    public static final int COUNT_OF_POPULATION = 10;
    public static final int TARGET_VALUE_OF_FUNCTION = 45;
    public static final int COUNT_OF_VALUES = 3;
    public static final int MAXIMUM_ITERATIONS = 10000;
    public static final double MUTATION_PROBABILITY = 6.0d;

    public static int getRandomGene() {
        return MathHelp.getRandomInt(MINIMUM_GENE, MAXIMUM_GENE);
    }
}