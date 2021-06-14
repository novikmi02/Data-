package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BruteForce {
    private int [][] adjacencyMatrix;
    private int verticesCount;
    private int chromaticNumber;
    private List<Integer> colorsOfVertices;

    public BruteForce(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.verticesCount = adjacencyMatrix.length;
        this.chromaticNumber = Integer.MAX_VALUE;
        this.colorsOfVertices = new ArrayList<>();
        for (int i = 0; i < verticesCount; i++) {
            colorsOfVertices.add(i, 0);
        }
    }

    public int getChromaticNumber() {
        return chromaticNumber;
    }

    public void showVerticesColor() {
        for (int i = 0; i < verticesCount; i++) {
            System.out.println("Vertex[" + i + "] = color--> " + colorsOfVertices.get(i));
        }
    }

    private boolean isAdjacent(int firstVertex, int secondVertex) {
        return adjacencyMatrix[firstVertex][secondVertex] == 1;
    }

    private boolean isCorrectGraphColoring() {
        boolean correctGraphColoring = true;
        for (int i = 0; i < verticesCount; i++) {
            for (int j = i + 1; j < verticesCount; j++) {
                if (isAdjacent(i, j)) {
                    if (colorsOfVertices.get(i).equals(colorsOfVertices.get(j))) {
                        correctGraphColoring = false;
                    }
                }
            }
        }
        return correctGraphColoring;
    }

    private boolean sequenceVertexColoring(int actualVertex) {
        if (actualVertex == verticesCount) {
            if (isCorrectGraphColoring()) {
                chromaticNumber = new HashSet<>(colorsOfVertices).size();
                return true;
            }
            return false;
        }
        for (int color = 1; color <= verticesCount; color++) {
            colorsOfVertices.set(actualVertex, color);
            if (sequenceVertexColoring(actualVertex + 1)) {
                return true;
            }
            colorsOfVertices.set(actualVertex, 0);
        }
        return false;
    }

    public void bruteForceAlgorithm() {
        sequenceVertexColoring(0);
    }
}
