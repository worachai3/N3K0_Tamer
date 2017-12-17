package com.nekotamer.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Alien {
	// private Vector2 position;
	private Rectangle hitbox;
	private boolean clicked = false;
	private int hp;
	private Cat targetCat;
	private int speed = 1;
	private int axisX = 0;
	private int axisY = 0;
	private float time = 0;

	public Alien(int x, int y, int hp) {
		hitbox = new Rectangle(x, y, 226, 337);
		this.hp = hp;
	}

	public void update(float delta, World world) {
		if (targetCat == null) {
			randomMove(delta);
		} else {
			moveTowardTarget(delta, targetCat);
		}
		if (Gdx.input.isButtonPressed(0) && !clicked) {
			if (getClicked(Gdx.input.getX(), Gdx.input.getY())) {
				this.hp -= 1;
			}
		}
		if (!Gdx.input.isButtonPressed(0)) {
			clicked = false;
		} else {
			clicked = true;
		}
	}

	private void moveTowardTarget(float delta, Cat cat) {
		Rectangle catHitbox = cat.getHitbox();
		float dx = catHitbox.x - hitbox.x;
		float dy = catHitbox.y - hitbox.y;
		double distance = Math.sqrt(dx * dx + dy * dy);
		hitbox.x = (float) (hitbox.x + (dx / distance * speed));
		hitbox.y = (float) (hitbox.y + (dy / distance * speed));
	}

	private void randomMove(float delta) {
		time += delta;
		Random rand = new Random();
		int x = rand.nextInt(10);
		int y = rand.nextInt(10);
		double distance = Math.sqrt(x * x + y * y);

		if (time >= 3) {
			axisX = rand.nextInt(2);
			axisY = rand.nextInt(2);
			time = 0;
		}

		if (hitbox.x <= 0) {
			axisX = 1;
			time = -5;
		}
		if (hitbox.y <= 0) {
			axisY = 1;
			time = -5;
		}
		if (hitbox.x + hitbox.width >= World.width) {
			axisX = 0;
			time = -5;
		}
		if (hitbox.y + hitbox.height >= World.height) {
			axisY = 0;
			time = -5;
		}

		if (distance > 0) {
			if (axisX == 1) {
				hitbox.x += speed;
			} else {
				hitbox.x -= speed;
			}
			if (axisY == 1) {
				hitbox.y += speed;
			} else {
				hitbox.y -= speed;
			}
		}
	}

	public boolean getClicked(int x, int y) {
		if (hitbox.x <= x && hitbox.y <= 720 - y && hitbox.x + hitbox.width >= x && hitbox.y + hitbox.height >= 720 - y) {
			System.out.println("clicked");
			return true;
		} else {
			return false;
		}

	}

	public boolean noTarget() {
		if (targetCat == null) {
			return true;
		} else {
			return false;
		}
	}

	public void setTarget(Cat target) {
		targetCat = target;
	}

	public boolean isDead() {
		if (this.hp <= 0) {
			return true;
		}
		return false;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}
}
