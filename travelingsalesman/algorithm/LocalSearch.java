package algorithm;

public class LocalSearch {
    private int[][] weightMatrix;
    private int verticesCount;
    private int minimumWeight;
    private int[] possiblePath;
    private int iteration;

    public LocalSearch(int[][] weightMatrix) {
        this.weightMatrix = weightMatrix;
        this.verticesCount = weightMatrix.length;
        this.possiblePath = new int[verticesCount];
    }

    public int getMinimumWeight() {
        return minimumWeight;
    }

    public int[] getPossiblePath() {
        return possiblePath;
    }

    public int getIteration() {
        return iteration;
    }

    private void arrayCopy(int[] arrayFrom, int[] arrayTo) {
        for (int i = 0; i < verticesCount; i++) {
            arrayTo[i] = arrayFrom[i];
        }
    }

    private int[] getBasePath() {
        int[] basePath = new int[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            basePath[i] = i;
        }
        return basePath;
    }

    public void localSearchAlgorithm() {
        int[] actualPath = getBasePath();
        arrayCopy(actualPath, possiblePath);
        boolean canImprove = true;
        while (canImprove) {
            canImprove = false;
            minimumWeight = getActualWeight(actualPath);
            for (int i = 0; i < verticesCount - 1; i++) {
                for (int k = i + 1; k < verticesCount - 1; k++) {
                    possiblePath = twoOptSwap(actualPath, i, k);
                    int actualWeight = getActualWeight(possiblePath);
                    if (actualWeight < minimumWeight) {
                        minimumWeight = actualWeight;
                        arrayCopy(possiblePath, actualPath);
                        canImprove = true;
                    }
                    iteration++;
                }
            }
        }
    }

    private int[] twoOptSwap(int[] actualPath, int i, int k) {
        int []newPath = new int[verticesCount];
        for (int j = 0; j <= i - 1; j++) {
            newPath[j] = actualPath[j];
        }
        int change = 0;
        for (int j = i; j <= k; j++) {
            newPath[j] = actualPath[k - change];
            change++;
        }
        for (int j = k + 1; j < verticesCount; j++) {
            newPath[j] = actualPath[j];
        }
        return newPath;
    }

    private int getActualWeight(int[] actualPath) {
        int actualWeight = 0;
        int n = verticesCount - 1;
        for (int i = 0; i < n; i++) {
            actualWeight += weightMatrix[actualPath[i]][actualPath[i + 1]];
        }
        actualWeight += weightMatrix[actualPath[n]][actualPath[0]];
        return actualWeight;
    }
}
