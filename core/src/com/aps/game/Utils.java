package com.aps.game;

import java.util.Random;

public class Utils {
    static int SCREEN_HEIGHT = 480;
    static int SCREEN_WIDTH = 640;

    public static int getRandomNumber(int limit) {
        return new Random().nextInt(limit);
    }

    public static double getRandomNumber(double limit) {
        return new Random().nextDouble(limit);
    }
}
