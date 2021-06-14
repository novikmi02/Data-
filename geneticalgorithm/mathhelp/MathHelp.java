package by.bsu.geneticalgorithm.mathhelp;

import java.util.Random;

public class MathHelp {
    public static double getRandomDouble(double min, double max) {
        return (double) (Math.random() * max + min);
    }

    public static int getRandomInt(int min, int max) {
        return new Random().nextInt(max + 1) + min;
    }
}
