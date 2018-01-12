package pl.pkuzia.models;

import java.util.Random;

/**
 * Created by Przemysław Kuzia on 08.01.2018.
 */
public final class RandomPixel {
    static Random randomGenerator = new Random();
    public static double[] values() {
        return new double[] {randomGenerator.nextInt(256), randomGenerator.nextInt(256),
                randomGenerator.nextInt(256)};
    }
}
