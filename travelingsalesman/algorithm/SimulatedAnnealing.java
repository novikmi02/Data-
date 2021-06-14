package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealing {
    private static final double MINIMUM_TEMPERATURE = 1.0;
    private static final double MAXIMUM_TEMPERATURE = 1000.0;
    private static final double COOLING_RATE = 0.002;
    private int[][] weightMatrix;
    private int minimumWeight;
    private List<Integer> possiblePath;
    private int iteration;

    public SimulatedAnnealing(int[][] weightMatrix) {
        this.weightMatrix = weightMatrix;
        this.possiblePath = new ArrayList<>();
    }

    public int getMinimumWeight() {
        return minimumWeight;
    }

    public List<Integer> getPossiblePath() {
        return Collections.unmodifiableList(possiblePath);
    }

    public int getIteration() {
        return iteration;
    }

    private int getRandomInt(int maximum) {
        return (int) (new Random().nextDouble() * maximum);
    }

    private boolean isAcceptanceProbability(double energy, double newEnergy, double temperature) {
        if (newEnergy < energy) {
            return true;
        }
        double delta = energy - newEnergy;
        double probability = Math.exp(delta / temperature);
        return probability > Math.random();
    }

    public void simulatedAnnealingAlgorithm() {
        Greedy greedy = new Greedy(weightMatrix);
        greedy.greedyAlgorithm();
        List<Integer> initialPath = greedy.getVertices();
        initialPath.remove(initialPath.size() - 1);
        possiblePath = initialPath;
        double temperature = MAXIMUM_TEMPERATURE;
        while (temperature > MINIMUM_TEMPERATURE) {
            List<Integer> actualPath = new ArrayList<>(possiblePath);
            List<Integer> newPath = generateNewPath(actualPath);
            int actualWeight = calculateMinimumWeight(actualPath);
            int newWeight = calculateMinimumWeight(newPath);
            if (isAcceptanceProbability(actualWeight, newWeight, temperature)) {
                actualPath = newPath;
            }
            minimumWeight = calculateMinimumWeight(possiblePath);
            actualWeight = calculateMinimumWeight(actualPath);
            if (actualWeight < minimumWeight) {
                possiblePath = actualPath;
                minimumWeight = actualWeight;
            }
            temperature *= (1 - COOLING_RATE);
            iteration++;
        }
    }

    private int calculateMinimumWeight(List<Integer> path) {
        int verticesCount = path.size();
        int weight = 0;
        for (int i = 0; i < verticesCount; i++) {
            int j;
            if (i < verticesCount - 1) {
                j = i + 1;
            } else {
                j = 0;
            }
            weight += weightMatrix[path.get(i)][path.get(j)];
        }
        return weight;
    }

    private List<Integer> generateNewPath(List<Integer> actualPath) {
        List<Integer> newPath = new ArrayList<>(actualPath);
        int firstVertexPosition = getRandomInt(newPath.size());
        int secondVertexPosition = getRandomInt(newPath.size());
        while (firstVertexPosition == secondVertexPosition) {
            secondVertexPosition = getRandomInt(newPath.size());
        }
        Collections.swap(actualPath, firstVertexPosition, secondVertexPosition);
        return newPath;
    }
}
