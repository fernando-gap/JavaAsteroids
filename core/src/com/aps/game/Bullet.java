package com.aps.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    private Sprite bullet;
    private int size = 5;
    private float x, y;
    private int velocity = 10;
    private float rotation;
    private int limit = (Utils.SCREEN_WIDTH + Utils.SCREEN_HEIGHT) / 2;

    public Bullet(float x, float y, float rotation) {
        this.x = x+20;
        this.y = y+20;
        this.rotation = rotation;

        bullet = new Sprite(new Texture("bullet.png"));
        bullet.setSize(size, size);
        bullet.setPosition(this.x, this.y);
    }

    public boolean overlaps(Asteroids asteroid) {
        Rectangle boxAsteroid = new Rectangle(asteroid.getX(), asteroid.getY(), asteroid.getSize(), asteroid.getSize());
        Rectangle boxBullet = new Rectangle(x, y, size, size);
        return boxBullet.overlaps(boxAsteroid);
    }

    public boolean draw(SpriteBatch batch) {
        if (this.x < limit || this.y < limit) {
            bullet.draw(batch);
            this.x += velocity * Math.cos(rotation);
            this.y += velocity * Math.sin(rotation);
            bullet.setPosition(this.x, this.y);
            return true;
        }
        return false;
    }
}
