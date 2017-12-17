package com.nekotamer.game;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;

public class Cat {
	private Rectangle hitbox;
	private Alien alien;
	private Food targetFood;
	
	private float time;
	private float hungerTime;
	
	private int axisX = 0;
	private int axisY = 0;
	private int hunger;
	private boolean dead = false;

	private int speed = 3;

	public Cat(int x, int y, int hunger) {
		// position = new Vector2(x, y);
		hitbox = new Rectangle(x, y, 142, 119);
		this.hunger = hunger;
		hungerTime = 0;
	}
	
	public int getHunger() {
		return this.hunger;
	}

	public boolean getEated(Rectangle other) {
		hitbox = getHitbox();
		if (Math.abs((hitbox.x + hitbox.width / 2) - (other.x + other.width / 2)) <= hitbox.width / 2 + other.width / 2
				&& Math.abs((hitbox.y + hitbox.height / 2) - (other.y + other.height / 2)) <= hitbox.height / 2
						+ other.height / 2) {
			return true;
		} else {
			return false;
		}
	}

	public void update(float delta, World world) {
		if (targetFood == null) {
			randomMove(delta);
		} else {
			moveTowardTarget(delta, targetFood);
		}
		if (hunger > 3) {
			speed = 3;
		}
		else if (hunger > 1) {
			speed = 2;
		}
		else if (hunger == 1) {
			speed = 1;
		}
		hungerTime += delta;
		if (hungerTime >= 6) {
			this.hunger -= 1;
			hungerTime = 0;
		}
		if (hunger <= 0) {
			dead = true;
		}
	}

	private void moveTowardTarget(float delta, Food food) {
		Rectangle foodHitbox = food.getHitbox();
		float dx = foodHitbox.x - hitbox.x;
		float dy = foodHitbox.y - hitbox.y;
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
		if (time >= 1) {
			axisX = rand.nextInt(2);
			axisY = rand.nextInt(2);
			time = 0;
		}

		if (hitbox.x <= 0) {
			axisX = 1;
			time = (float) -0.5;
		}
		if (hitbox.y <= 0) {
			axisY = 1;
			time = (float) -0.5;
		}
		if (hitbox.x + hitbox.width >= World.width) {
			axisX = 0;
			time = (float) -0.5;
		}
		if (hitbox.y + hitbox.height >= World.height) {
			axisY = 0;
			time = (float) -0.5;
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
	
	public void fed() {
		hunger += 1;
		hungerTime = 0;
	}
	
	public boolean isDead() {
		return dead;
	}

	public boolean noTarget() {
		if (targetFood == null) {
			return true;
		} else {
			return false;
		}
	}

	public void setTarget(Food target) {
		targetFood = target;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}
}
