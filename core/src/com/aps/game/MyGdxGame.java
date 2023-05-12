package com.aps.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Rocket rocket;
	private Heart heart;
	private int asteroidsLimit = 10;
	private BitmapFont font;
	private int score = 0;
	private ArrayList<Asteroids> asteroids = new ArrayList<>();

	@Override
	public void create () {
		batch = new SpriteBatch();
		rocket = new Rocket();
		heart = new Heart();
		Asteroids.createRandomAsteroids(asteroids, asteroidsLimit);
		font = new BitmapFont();
		font.setColor(Color.WHITE);
	}

	public void gameOver() {
		asteroids.clear();
		rocket.dispose();
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
		if (!heart.isGameOver()) {
			heart.draw(batch);
			rocket.draw(batch);
			System.out.println(score);
			font.draw(batch, "" + score, 5, Utils.SCREEN_HEIGHT-45);

			ArrayList<Bullet> bullets = rocket.getBullets();

			for (int i = 0; i < bullets.size(); i++) {
				Bullet bullet = bullets.get(i);
				for (int j = 0; j < asteroids.size(); j++) {
					Asteroids asteroid = asteroids.get(j);

					if (bullet.overlaps(asteroid)) {
						if (asteroid.getSize() == 80) {
							drawSmallAsteroids(asteroid, 40, 2);
							score += (10 * 1.01);
						} else if (asteroid.getSize() == 40) {
							drawSmallAsteroids(asteroid, 20, 4);
							score += 20 * 1.05;
						} else {
							asteroids.remove(asteroid);
							score += 30 * 1.1;
						}
						bullets.remove(bullet);
					}
				}
			}

			if (asteroids.size() == 0) {
				asteroidsLimit *= 1.2;
				Asteroids.createRandomAsteroids(asteroids, asteroidsLimit);
			}

			for (Asteroids asteroid : asteroids) {
				if (rocket.overlaps(asteroid)) {
					heart.decrease();
					rocket.respawn();
				}
			}

			for (Asteroids asteroid : asteroids) asteroid.draw(batch);
			for (int i = 0; i < bullets.size(); i++) {
				if (!bullets.get(i).draw(batch)) {
					bullets.remove(i);
				}
			}

		} else {
			String message = "GAME OVER!\nScore: " + score + "\n\n(Pressione espaço para reiniciar)";
			font.draw(batch, message, Utils.SCREEN_WIDTH/2-80, Utils.SCREEN_HEIGHT/2+30);
			gameOver();
			score = 0;
			if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
				heart.reset(3);
			}
		}
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
