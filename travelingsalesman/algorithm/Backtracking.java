package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Backtracking {
    private static final int MINIMUM = Integer.MAX_VALUE;
    private int[][] weightMatrix;
    private int verticesCount;
    private int minimumWeight;
    private boolean []visitedVertices;
    private List<List<Integer>> possiblePaths;
    private int iteration;

    public Backtracking(int[][] weightMatrix) {
        this.weightMatrix = weightMatrix;
        this.verticesCount = weightMatrix.length;
        this.minimumWeight = MINIMUM;
        this.visitedVertices = new boolean[this.verticesCount];
        this.possiblePaths = new ArrayList<>();
    }

    public int getIteration() {
        return iteration;
    }

    public List<List<Integer>> getPossiblePaths() {
        return Collections.unmodifiableList(possiblePaths);
    }

    public int getMinimumWeight() {
        return minimumWeight;
    }

    private void possibleTourPaths(List<Integer> verticesPath) {
        List<Integer> path = new ArrayList<>();
        for (int i = 0; i < verticesCount; i++) {
            path.add(verticesPath.get(i));
        }
        path.add(verticesPath.get(0));
        possiblePaths.add(path);
    }

    private int getMinimumWeightOfFirstEdge(int i) {
        int minimumActualWeight = MINIMUM;
        for (int j = 0; j < verticesCount; j++) {
            if (i != j) {
                if (weightMatrix[i][j] < minimumActualWeight) {
                    minimumActualWeight = weightMatrix[i][j];
                }
            }
        }
        return minimumActualWeight;
    }

    private int getMinimumWeightOfSecondEdge(int i) {
        int firstMinimumActualWeight = MINIMUM;
        int secondMinimumActualWeight = MINIMUM;
        for (int j = 0; j < verticesCount; j++) {
            if (i != j) {
                if (weightMatrix[i][j] <= firstMinimumActualWeight) {
                    secondMinimumActualWeight = firstMinimumActualWeight;
                    firstMinimumActualWeight = weightMatrix[i][j];
                } else if (weightMatrix[i][j] <= secondMinimumActualWeight &&
                        weightMatrix[i][j] != firstMinimumActualWeight) {
                    secondMinimumActualWeight = weightMatrix[i][j];
                }
            }
        }
        return secondMinimumActualWeight;
    }

    private void updateVisitedVerticesArray(int nextVertex, List<Integer> actualPath) {
        Arrays.fill(visitedVertices,false);
        for (int i = 0; i <= nextVertex - 1; i++) {
            visitedVertices[actualPath.get(i)] = true;
        }
    }

    private void computeLastWeight(int nextVertex, int actualWeight, List<Integer> actualPath) {
        if (nextVertex == verticesCount) {
            int lastWeight = weightMatrix[actualPath.get(nextVertex - 1)][actualPath.get(0)];
            if (lastWeight != 0) {
                int fullWeight = actualWeight + lastWeight;
                if (fullWeight < minimumWeight) {
                    minimumWeight = fullWeight;
                    possibleTourPaths(actualPath);
                }
            }
        }
    }

    private void computeMinimumHamiltonianCycle(int boundWeight, int actualWeight, int nextVertex, List<Integer> actualPath) {
        computeLastWeight(nextVertex, actualWeight, actualPath);
        for (int i = 0; i < verticesCount; i++) {
            int nextWeight = weightMatrix[actualPath.get(nextVertex - 1)][i];
            if (nextWeight != 0 && !visitedVertices[i]) {
                int rememberedBound = boundWeight;
                actualWeight += nextWeight;
                if (nextVertex == 1) {
                    boundWeight -= ((getMinimumWeightOfFirstEdge(actualPath.get(nextVertex - 1)) +
                            getMinimumWeightOfFirstEdge(i)) / 2);
                } else {
                    boundWeight -= ((getMinimumWeightOfSecondEdge(actualPath.get(nextVertex - 1)) +
                            getMinimumWeightOfFirstEdge(i)) / 2);
                }
                if (boundWeight + actualWeight < minimumWeight) {
                    actualPath.add(nextVertex, i);
                    visitedVertices[i] = true;
                    computeMinimumHamiltonianCycle(boundWeight, actualWeight, nextVertex + 1, actualPath);
                }
                actualWeight -= nextWeight;
                boundWeight = rememberedBound;
                updateVisitedVerticesArray(nextVertex, actualPath);
                iteration++;
            }
        }
    }

    private int getFirstApproximatedWeight() {
        int lowerBound = 0;
        for (int i = 0; i < verticesCount; i++) {
            lowerBound += (getMinimumWeightOfFirstEdge(i) + getMinimumWeightOfSecondEdge(i));
        }
        lowerBound = lowerBound / 2;
        return lowerBound;
    }

    public void backtrackingAlgorithm() {
        List<Integer> actualPath = new ArrayList<>();
        int firstApproximatedWeight = getFirstApproximatedWeight();
        visitedVertices[0] = true;
        actualPath.add(0);
        computeMinimumHamiltonianCycle(firstApproximatedWeight, 0, 1, actualPath);
    }
}
