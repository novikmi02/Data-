package main;

import algorithm.BruteForce;
import algorithm.DSatur;
import algorithm.GreedyIndependentSets;

public class Main {
    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
                { 0, 1, 1, 0, 0},
                { 1, 0, 1, 0, 1},
                { 1, 1, 0, 0, 1},
                { 0, 0, 0, 0, 1},
                { 0, 1, 1, 1, 0}
        };
        int reduce = 1_000_000;
        long startTime;
        long endTime;
        int chromaticNumber;
        System.out.println("BruteForce algorithm");
        BruteForce bruteForce = new BruteForce(adjacencyMatrix);
        startTime = System.nanoTime() / reduce;
        bruteForce.bruteForceAlgorithm();
        endTime = System.nanoTime() / reduce;
        System.out.println("Time spent: " + (endTime - startTime) + "ms.");
        chromaticNumber = bruteForce.getChromaticNumber();
        System.out.println("Chromatic number: " + chromaticNumber);
        bruteForce.showVerticesColor();
        System.out.println("GreedyIndependentSets algorithm");
        GreedyIndependentSets greedyIndependentSets = new GreedyIndependentSets(adjacencyMatrix);
        startTime = System.nanoTime();
        greedyIndependentSets.greedyIndependentSetsAlgorithm();
        endTime = System.nanoTime();
        System.out.println("Time spent: " + (endTime - startTime) + "ns.");
        chromaticNumber = greedyIndependentSets.getChromaticNumber();
        System.out.println("ChromaticNumber: " + chromaticNumber);
        greedyIndependentSets.showVerticesColor();
        System.out.println("DSatur algorithm");
        DSatur dSatur = new DSatur(adjacencyMatrix);
        startTime = System.nanoTime() / reduce;
        dSatur.dSaturAlgorithm();
        endTime = System.nanoTime() / reduce;
        System.out.println("Time spent: " + (endTime - startTime) + "ms.");
        chromaticNumber = dSatur.getChromaticNumber();
        System.out.println("ChromaticNumber: " + chromaticNumber);
        dSatur.showVerticesColor();
    }
}