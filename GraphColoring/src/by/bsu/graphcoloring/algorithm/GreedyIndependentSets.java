package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GreedyIndependentSets {
    private int[][] adjacencyMatrix;
    private int verticesCount;
    private int chromaticNumber;
    private List<Integer> colorsOfVertices;

    public GreedyIndependentSets(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.verticesCount = adjacencyMatrix.length;
        this.colorsOfVertices = new ArrayList<>();
    }

    public int getChromaticNumber() {
        return chromaticNumber;
    }

    private boolean isAdjacent(int firstVertex, int secondVertex) {
        return adjacencyMatrix[firstVertex][secondVertex] == 1;
    }

    public void greedyIndependentSetsAlgorithm() {
        colorsOfVertices.add(0);
        List<Integer> coloredVertices = new ArrayList<>();
        coloredVertices.add(0);
        for (int i = 1; i < verticesCount; i++) {
            boolean[] availableColors = new boolean[verticesCount];
            for (int j = 0; j < verticesCount; j++) {
                availableColors[j] = true;
            }
            for (int k = 0; k < coloredVertices.size(); k++) {
                if (isAdjacent(i, k)) {
                    int color = colorsOfVertices.get(k);
                    availableColors[color] = false;
                }
            }
            int satisfyingColor;
            for (satisfyingColor = 0; satisfyingColor < availableColors.length; satisfyingColor++) {
                if (availableColors[satisfyingColor]) {
                    break;
                }
            }
            colorsOfVertices.add(satisfyingColor);
            coloredVertices.add(i);
        }
        chromaticNumber = new HashSet<>(colorsOfVertices).size();
    }

    public void showVerticesColor() {
        for (int i = 0; i < verticesCount; i++) {
            System.out.println("Vertex[" + i + "] = color--> " + colorsOfVertices.get(i));
        }
    }
}
