package com.aps.game;

import java.util.Random;

public class Utils {
    public static int getRandomNumber(int limit) {
        return new Random().nextInt(limit);
    }

    public static double getRandomNumber(double limit) {
        return new Random().nextDouble(limit);
    }
}
