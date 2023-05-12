package com.aps.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Heart {
    private int heartCount = 3;
    private Sprite heart[] = new Sprite[heartCount];
    private int heartSize = 40;
    private boolean isGameOver = false;

    public Heart() {
        for (int i = 0; i < heartCount; i++) {
            heart[i] = new Sprite(new Texture("heart.png"));
            heart[i].setSize(heartSize, heartSize);
            heart[i].setPosition(i*heartSize, Utils.SCREEN_HEIGHT-heartSize);
        }
    }

    public void decrease() {
        if (heartCount > 0) {
            heartCount -= 1;
        }

        if (heartCount <= 0) {
            isGameOver = true;
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void reset(int count) {
        heartCount = count;
        isGameOver = false;
    }

    public void draw(SpriteBatch batch) {
      for (int i = 0; i < heartCount; i++) {
         heart[i].draw(batch);
      }
    }
}
