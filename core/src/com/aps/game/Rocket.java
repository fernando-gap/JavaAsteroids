package com.aps.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Rocket {
   private static int size = 40;
   private static int rotationOffset = 5;
   private int velocity = 5;
   private Sprite rocket;
   private ArrayList<Bullet> bullets = new ArrayList<>();

   public Rocket() {
      rocket = new Sprite(new Texture("rocket40.png"));
      rocket.setSize(size, size);
      rocket.setPosition(Utils.SCREEN_WIDTH/2-(size/2), Utils.SCREEN_HEIGHT/2-(size/2));
   }

   private void controls() {
      double rotation = (2 * Math.PI * rocket.getRotation()) / 360;

      if (Gdx.input.isKeyPressed(Keys.LEFT)) {
         rocket.rotate(rotationOffset);
      }
      if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
         rocket.rotate(-rotationOffset);
      }
      if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
         bullets.add(new Bullet(rocket.getX(), rocket.getY(), (float)rotation));
      }
      if (Gdx.input.isKeyPressed(Keys.UP)) {
         rocket.setPosition(
            (float) (rocket.getX() + (velocity * Math.cos(rotation))),
            (float) (rocket.getY() + (velocity * Math.sin(rotation)))
         );
      }
   }

   public ArrayList<Bullet> getBullets() {
      return bullets;
   }

   public boolean overlaps(Asteroids asteroid) {
      Rectangle boxAsteroid = new Rectangle(asteroid.getX(), asteroid.getY(), asteroid.getSize()-5, asteroid.getSize()-5);
      Rectangle boxRocket = new Rectangle(rocket.getX(), rocket.getY(), size, size);
      return boxRocket.overlaps(boxAsteroid);
   }

   public void respawn() {
      int w = Utils.SCREEN_WIDTH/2-(size/2);
      int h = Utils.SCREEN_HEIGHT/2-(size/2);
      rocket.setPosition(w, h);
   }

   public void draw(SpriteBatch batch) {
      if (rocket.getX() > Utils.SCREEN_WIDTH) {
         rocket.setPosition(0, rocket.getY());
      } else if (rocket.getX() < 0) {
         rocket.setPosition(Utils.SCREEN_WIDTH, rocket.getY());
      } else if (rocket.getY() < 0) {
         rocket.setPosition(rocket.getX(), Utils.SCREEN_HEIGHT);
      } else if (rocket.getY() > Utils.SCREEN_HEIGHT) {
         rocket.setPosition(rocket.getX(), 0);
      }
      rocket.draw(batch);
      controls();
   }

   public void dispose() {
      bullets.clear();
   }
}
