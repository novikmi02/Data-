package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BruteForce2 {
    private int [][] graph;
    private int numberOfVertices;
    public int chromaticNumber;
    private List<Integer> colorsOfVertices;

    public BruteForce2(int[][] graph) {
        this.graph = graph;
        this.numberOfVertices = graph.length;
        this.chromaticNumber = Integer.MAX_VALUE;
        this.colorsOfVertices = new ArrayList<>();
    }

    public int getChromaticNumber() {
        return chromaticNumber;
    }

    private void bruteForceAlgorithm() {
        List<Integer> currentColorOfVertices = new ArrayList<>();
        for (int colors = 1; colors <= numberOfVertices; colors++) {
            for (int i = 0; i < numberOfVertices; i++) {
                currentColorOfVertices.add(i, 0);
            }
            int indexOfActualVertex = 0;
            boolean notAllCombinationsConsidered = true;
            while (notAllCombinationsConsidered) {
                boolean trueGraphColoring = true;
                for (int i = 0; i < numberOfVertices - 1; i++) {
                    for (int j = i + 1; j < numberOfVertices; j++) {
                        if (graph[i][j] != 0) {
                            if (currentColorOfVertices.get(i).equals(currentColorOfVertices.get(j))) {
                                trueGraphColoring = false;
                            }
                        }
                    }
                }
                if (trueGraphColoring) {
                    int currentChromaticNumber = new HashSet<>(currentColorOfVertices).size();
                    if (currentChromaticNumber < chromaticNumber) {
                        chromaticNumber = currentChromaticNumber;
                        for (int i = 0; i < numberOfVertices; i++) {
                            colorsOfVertices.add(i, currentColorOfVertices.get(i));
                        }
                    }
                }
                currentColorOfVertices.set(0, currentColorOfVertices.get(0) + 1);
                while (currentColorOfVertices.get(indexOfActualVertex) == colors) {
                    if (indexOfActualVertex == numberOfVertices - 1) {
                        notAllCombinationsConsidered = false;
                        break;
                    }
                    else {
                        currentColorOfVertices.set(indexOfActualVertex, 0);
                        indexOfActualVertex++;
                        currentColorOfVertices.set(indexOfActualVertex,
                                currentColorOfVertices.get(indexOfActualVertex) + 1);
                    }
                }
                indexOfActualVertex = 0;
            }
        }
    }

    public void resultingVerticesColor() {
        for (int i = 0; i < numberOfVertices; i++) {
            System.out.println("Vertex[" + i + "] = color--> " + colorsOfVertices.get(i));
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
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
        BruteForce2 bruteForce2 = new BruteForce2(graph);
        startTime = System.nanoTime() / reduce;
        bruteForce2.bruteForceAlgorithm();
        endTime = System.nanoTime() / reduce;
        System.out.println("Time spent: " + (endTime - startTime) + "ms.");
        chromaticNumber = bruteForce2.getChromaticNumber();
        System.out.println("Chromatic number: " + chromaticNumber);
        bruteForce2.resultingVerticesColor();
    }
}

