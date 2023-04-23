package com.aps.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	ArrayList<Asteroids> asteroids = new ArrayList<>();
	/* all bullets MUST be stored here */
	ArrayList<Bullet> bullets = new ArrayList<>();
	Rocket rocket;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Asteroids.createRandomAsteroids(asteroids);
		rocket = new Rocket();
	}

	public void drawSmallAsteroids(Asteroids asteroid, int size, int quantity) {
		for (int i = 0; i < quantity; i++) {
			Asteroids small = new Asteroids(size, asteroid.getX(), asteroid.getY());
			asteroids.add(small);
			small.draw(batch);
		}
		asteroids.remove(asteroid);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		rocket.draw(batch);
		rocket.controls();
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < asteroids.size(); j++) {
				Asteroids asteroid = asteroids.get(i);

				if (bullets.get(i).overlaps(asteroid)) {
					if (asteroid.getSize() == 80) {
						drawSmallAsteroids(asteroid, 40, 2);
					} else if (asteroid.getSize() == 40) {
						drawSmallAsteroids(asteroid, 20, 4);
					}
				}
			}
		}

		for (Asteroids asteroid : asteroids) asteroid.draw(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
