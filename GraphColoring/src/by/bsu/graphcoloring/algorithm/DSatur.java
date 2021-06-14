package algorithm;

import java.util.*;

public class DSatur {
    private int[][] adjacencyMatrix;
    private int verticesCount;
    private Map<Integer, Integer> verticesWithMatchingColors;
    private int chromaticNumber;

    public DSatur(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.verticesCount = adjacencyMatrix.length;
        this.verticesWithMatchingColors = new HashMap<>();
    }

    public int getChromaticNumber() {
        return chromaticNumber;
    }

    private boolean isAdjacent(int firstVertex, int secondVertex) {
        return adjacencyMatrix[firstVertex][secondVertex] == 1;
    }

    private void fillInitialColors(List<Integer> colors) {
        for (int i = 0; i < verticesCount; i++) {
            colors.add(i, -1);
        }
    }

    private void fillVerticesWithoutColor(List<Integer> verticesWithoutColor) {
        for (int i = 0; i < verticesCount; i++) {
            verticesWithoutColor.add(i);
        }
    }

    public void dSaturAlgorithm() {
        List<Integer> sequenceOfColoredVertices = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();
        fillInitialColors(colors);
        List<Integer> verticesWithoutColor = new ArrayList<>();
        fillVerticesWithoutColor(verticesWithoutColor);
        int vertexWithTheHighestDegree = getVertexWithTheHighestDegree();
        colors.set(vertexWithTheHighestDegree, 0);
        sequenceOfColoredVertices.add(vertexWithTheHighestDegree);
        verticesWithMatchingColors.put(vertexWithTheHighestDegree, 0);
        verticesWithoutColor.removeIf(o -> o == vertexWithTheHighestDegree);
        while (verticesWithoutColor.size() > 0) {
            int vertexWithMaxSaturation = getVertexWithMaxSaturation(colors);
            boolean[] availableColors = new boolean[verticesCount];
            for (int j = 0; j < verticesCount; j++) {
                availableColors[j] = true;
            }
            for (int actualVertex : sequenceOfColoredVertices) {
                if (isAdjacent(vertexWithMaxSaturation, actualVertex)) {
                    int color = verticesWithMatchingColors.get(actualVertex);
                    availableColors[color] = false;
                }
            }
            int satisfyingColor;
            for (satisfyingColor = 0; satisfyingColor < availableColors.length; satisfyingColor++) {
                if (availableColors[satisfyingColor]) {
                    break;
                }
            }
            verticesWithMatchingColors.put(vertexWithMaxSaturation, satisfyingColor);
            final int vertex = vertexWithMaxSaturation;
            verticesWithoutColor.removeIf(o -> o == vertex);
            sequenceOfColoredVertices.add(vertexWithMaxSaturation);
            colors.set(vertexWithMaxSaturation, satisfyingColor);
        }
        chromaticNumber = new HashSet<>(new ArrayList<>(verticesWithMatchingColors.values())).size();
    }

    private int getVertexWithTheHighestDegree() {
        int vertexWithHighestDegree = 0;
        int numberOfAdjacentVertices = 0;
        for (int i = 0; i < verticesCount; i++) {
            int actualNumberAdjacentVertices = 0;
            for (int j = 0; j < verticesCount; j++) {
                if (isAdjacent(i, j)) {
                    actualNumberAdjacentVertices++;
                }
            }
            if (actualNumberAdjacentVertices > numberOfAdjacentVertices) {
                numberOfAdjacentVertices = actualNumberAdjacentVertices;
                vertexWithHighestDegree = i;
            }
        }
        return vertexWithHighestDegree;
    }

    private int getVertexWithMaxSaturation(List<Integer> colors) {
        int maxSaturation = 0;
        int vertexWithMaxSaturation = 0;
        for(int i = 0; i < verticesCount; i++) {
            if (colors.get(i) == -1) {
                Set<Integer> actualColors = new TreeSet<>();
                for (int j = 0; j < verticesCount; j++) {
                    int color = colors.get(j);
                    if (isAdjacent(i, j) && color != -1) {
                        actualColors.add(color);
                    }
                }
                int actualSaturation = actualColors.size();
                if (actualSaturation > maxSaturation) {
                    maxSaturation = actualSaturation;
                    vertexWithMaxSaturation = i;
                } else if (actualSaturation == maxSaturation && computeVertexDegree(i) >=
                        computeVertexDegree(vertexWithMaxSaturation)) {
                    vertexWithMaxSaturation = i;
                }
            }
        }
        return vertexWithMaxSaturation;
    }

    private int computeVertexDegree(int vertex) {
        int degree = 0;
        for(int i = 0 ; i < verticesCount; i++) {
            if (isAdjacent(vertex, i)) {
                degree++;
            }
        }
        return degree;
    }

    public void showVerticesColor() {
        System.out.println(verticesWithMatchingColors);
    }
}
