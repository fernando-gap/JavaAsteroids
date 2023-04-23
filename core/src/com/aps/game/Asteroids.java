package com.aps.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Asteroids {
    private static String[] images = {"meteor-1.png", "meteor-2.png", "meteor-3.png"};
    private float x;
    private float y;
    private float directionY;
    private float directionX;
    private int velocity;
    private Sprite sprite;
    private int size = 80;

    public static void createRandomAsteroids(ArrayList<Asteroids> asteroids) {
        for (int i = 0; i < Utils.getRandomNumber(6)+1; i++) {
            asteroids.add(new Asteroids());
        }
    }


    public Asteroids() {
        x = Utils.getRandomNumber(640);
        y = Utils.getRandomNumber(480);
        directionY = (float) Math.sin(Utils.getRandomNumber(Math.PI));
        directionX = (float) Math.cos(Utils.getRandomNumber(Math.PI));
        velocity = Utils.getRandomNumber(2) + 1;
        sprite = getRandomImage();
    }

    public Asteroids(int size) {
        this();
        this.size = size;
    }

    public Asteroids(int size, float y, float x) {
        directionY = (float) Math.sin(Utils.getRandomNumber(Math.PI));
        directionX = (float) Math.cos(Utils.getRandomNumber(Math.PI));
        velocity = Utils.getRandomNumber(2) + 1;
        sprite = getRandomImage();
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public Sprite getRandomImage() {
        String imageSelect = Asteroids.images[Utils.getRandomNumber(Asteroids.images.length)];
        Texture image = new Texture(imageSelect);
        return new Sprite(image);
    }

    public int getSize() {
        return size;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public void draw(SpriteBatch batch) {
        sprite.setPosition(x, y);
        x = x + (velocity * directionX);
        y = y + (velocity * directionY);

        if (x <= 0) {
            x += 640;
        }
        if (x >= 640) {
            x -= 640;
        }
        if (y <= 0) {
            y += 480;
        }
        if (y >= 480){
            y -= 480;
        }

        batch.draw(sprite, x, y, size, size);
    }
}
