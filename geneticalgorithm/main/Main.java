package by.bsu.geneticalgorithm.main;

import by.bsu.geneticalgorithm.algorithm.DiophantineEquation;
import by.bsu.geneticalgorithm.algorithm.Individual;

public class Main {
    public static void main(String[] args) {
        System.out.println("Diophantine equation x * x * y * y * z * z - 2*x*y -3*x -4*y - z = 45" + "\n");
        DiophantineEquation diofantineEquation = new DiophantineEquation();
        Individual individual = diofantineEquation.solveEquation();
        System.out.println("Solution: " + individual.toString());
        System.out.println("Count Of Iterations: " + diofantineEquation.getCountOfIterations());
    }
}