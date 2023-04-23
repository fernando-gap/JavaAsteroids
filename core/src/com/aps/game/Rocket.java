package com.aps.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Rocket {
   private static int size = 40;
   private static int rotationOffset = 5;
   private int velocity = 5;
   Sprite rocket;

   public Rocket() {
      rocket = new Sprite(new Texture("rocket40.png"));
      rocket.setSize(size, size);
      rocket.setPosition(0, 0);
   }

   public void controls() {
      if (Gdx.input.isKeyPressed(Keys.LEFT)) {
         rocket.rotate(rotationOffset);
      }
      if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
         rocket.rotate(-rotationOffset);
      }
      if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
         System.out.println("SHOOT");
      }
      if (Gdx.input.isKeyPressed(Keys.UP)) {
         double rotation = (2 * Math.PI * rocket.getRotation()) / 360;
         rocket.setPosition(
            (float) (rocket.getX() + (velocity * Math.cos(rotation))),
            (float) (rocket.getY() + (velocity * Math.sin(rotation)))
         );
      }
   }
   public void draw(SpriteBatch batch) {
      rocket.draw(batch);
   }
}
