package main;

import algorithm.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int [][]weightMatrix = {
                {0,  2,  1,  40, 1,  8, 3,  13, 56, 3, 12, 40, 1},
                {2,  0,  16, 78, 66, 8, 16, 11, 33, 4, 99, 41, 2},
                {1,  16, 0,  45, 6,  3, 77, 4,  45, 5, 98, 42, 3},
                {40, 78, 45, 0,  4, 56, 10, 9, 67, 6, 97,  43, 4},
                {1,  66, 6,  4,  0, 20, 30, 9, 78, 7, 96,  44, 5},
                {8,  8,  3,  56, 20, 0, 50, 13, 6, 8, 95,  45, 6},
                {3,  16, 77, 10, 30, 50, 0, 23, 8, 9, 94,  46, 7},
                {13, 11, 4,  9,  9,  13, 23, 0, 4, 10, 93, 47, 8},
                {56, 33, 45, 67, 78, 6,  8, 4,  0, 11, 92, 48, 9},
                {3,  4,  5,   6, 7,  8,  9, 10, 11, 0, 91, 49, 10},
                {12, 99, 98, 97, 96, 95, 94,93, 92, 91, 0, 50, 11},
                {40, 41, 42, 43, 44, 45, 46,47, 48, 49, 50, 0, 12},
                {1,  2,  3,  4,   5,  6,  7, 8,  9, 10, 11, 12, 0}
        };
        System.out.println("BruteForce algorithm");
        BruteForce bruteForce = new BruteForce(weightMatrix);
        bruteForce.bruteForceAlgorithm();
        System.out.println("Weight: " + bruteForce.getMinimumWeight());
        System.out.println("Vertices: " + bruteForce.getPossiblePaths().toString());
        System.out.println("Iteration count: " + bruteForce.getIteration());
        System.out.println("Backtracking algorithm");
        Backtracking backtracking = new Backtracking(weightMatrix);
        backtracking.backtrackingAlgorithm();
        System.out.println("Weight: " + backtracking.getMinimumWeight());
        System.out.println("Vertices: " + backtracking.getPossiblePaths().toString());
        System.out.println("Iteration count: " + backtracking.getIteration());
        System.out.println("Greedy algorithm");
        Greedy greedy = new Greedy(weightMatrix);
        greedy.greedyAlgorithm();
        System.out.println("Weight: " + greedy.getWeight());
        System.out.println("Vertices: " + greedy.getVertices());
        System.out.println("Iteration count: " + greedy.getIteration());
        System.out.println("LocalSearch algorithm");
        LocalSearch localSearch = new LocalSearch(weightMatrix);
        localSearch.localSearchAlgorithm();
        System.out.println("Weight: " + localSearch.getMinimumWeight());
        System.out.println("Vertices: " + Arrays.toString(localSearch.getPossiblePath()));
        System.out.println("Iteration count: " + localSearch.getIteration());
        System.out.println("Simulated Annealing algorithm");
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(weightMatrix);
        simulatedAnnealing.simulatedAnnealingAlgorithm();
        System.out.println("Weight: " + simulatedAnnealing.getMinimumWeight());
        System.out.println("Vertices: " + simulatedAnnealing.getPossiblePath());
        System.out.println("Iteration count: " + simulatedAnnealing.getIteration());
    }
}

