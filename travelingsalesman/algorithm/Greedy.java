package algorithm;

import java.util.LinkedList;

public class Greedy {
    private static final int MINIMUM = Integer.MAX_VALUE;
    private int[][] weightMatrix;
    private int verticesCount;
    private int minimumWeight;
    private LinkedList<Integer> vertices;
    private int iteration;

    public Greedy(int[][] weightMatrix) {
        this.weightMatrix = weightMatrix;
        this.verticesCount = weightMatrix.length;
        this.vertices = new LinkedList<>();
    }

    public int getWeight() {
        return minimumWeight;
    }

    public int getIteration() {
        return iteration;
    }

    public LinkedList<Integer> getVertices() {
        return vertices;
    }

    public void greedyAlgorithm(){
        vertices.add(0);
        while(vertices.size() < verticesCount) {
            findNextVertex();
        }
        vertices.add(0);
        minimumWeight += weightMatrix[vertices.getLast()][vertices.getFirst()];
    }

    private void findNextVertex() {
        int actualWeight = MINIMUM;
        int lastVertex = vertices.getLast();
        int nextBestVertex = lastVertex;
        for (int i = 0; i < verticesCount; i++){
            final int k = i;
            if (vertices.stream().noneMatch(c -> c == k)) {
                int weight = weightMatrix[lastVertex][k];
                if (weight < actualWeight) {
                    actualWeight = weight;
                    nextBestVertex = k;
                }
                iteration++;
            }
        }
        vertices.add(nextBestVertex);
        minimumWeight += actualWeight;
    }
}
