package algorithm;

import java.util.*;

public class BruteForce {
    private static final int MINIMUM = Integer.MAX_VALUE;
    private int[][] weightMatrix;
    private int verticesCount;
    private int minimumWeight;
    private List<List<Integer>> possiblePaths;

    private int iteration;

    public int getIteration() {
        return iteration;
    }

    public List<List<Integer>> getPossiblePaths() {
        return Collections.unmodifiableList(possiblePaths);
    }

    public BruteForce(int[][] weightMatrix) {
        this.weightMatrix = weightMatrix;
        this.verticesCount = weightMatrix.length;
        this.minimumWeight = MINIMUM;
        this.possiblePaths = new ArrayList<>();
    }

    public int getMinimumWeight() {
        return minimumWeight;
    }

    private void possibleTourPaths(List<Integer> vertices) {
        List<Integer> path = new ArrayList<>();
        path.add(0);
        path.addAll(vertices);
        path.add(0);
        possiblePaths.add(path);
    }

    public void bruteForceAlgorithm() {
        int startVertex = 0;
        List<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < verticesCount; i++) {
            if (i != startVertex) {
                vertices.add(i);
            }
        }
        do {
            int currentWeight = 0;
            int nextVertex = startVertex;
            for (Integer vertex : vertices) {
                currentWeight += weightMatrix[nextVertex][vertex];
                nextVertex = vertex;
            }
            currentWeight += weightMatrix[nextVertex][startVertex];
            if (currentWeight < minimumWeight) {
                minimumWeight = currentWeight;
                possibleTourPaths(vertices);
            }
            iteration++;
        } while (nextPermutation(vertices));
    }

    private void swap(List<Integer> vertices, int i, int j) {
        int temp = vertices.get(i);
        vertices.set(i, vertices.get(j));
        vertices.set(j, temp);
    }

    private void reverse(List<Integer> vertices, int i, int j) {
        while (i < j) {
            swap(vertices, i, j);
            i++;
            j--;
        }
    }

    private int getLongestNonIncreasingSequenceIndex(List<Integer> vertices) {
        int n = vertices.size();
        for (int i = n - 2; i >= 0; i--) {
            if (vertices.get(i) <= vertices.get(i + 1)) {
                return i;
            }
        }
        return -1;
    }

    private boolean nextPermutation(List<Integer> vertices) {
        int n = vertices.size();
        int i = getLongestNonIncreasingSequenceIndex(vertices);
        if (i == -1) {
            return false;
        }
        int j = i + 1;
        while(j < n && vertices.get(j) > vertices.get(i)) {
            j++;
        }
        j--;
        swap(vertices, i, j);
        int left = i + 1;
        int right = n - 1;
        reverse(vertices, left, right);
        return true;
    }
}
