package com.nekotamer.game;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;

public class Cat {
	// private Vector2 position;
	private Rectangle hitbox;
	private Alien alien;
	private Food targetFood;
	private float time;
	private int axisX = 0;
	private int axisY = 0;

	private static final int speed = 3;

	public Cat(int x, int y) {
		// position = new Vector2(x, y);
		hitbox = new Rectangle(x, y, 142, 119);
	}

	public boolean getEated(Rectangle other) {
		hitbox = getHitbox();
		if (Math.abs((hitbox.x + hitbox.width/2) - (other.x + other.width/2)) <= hitbox.width/2 + other.width/2
			&& Math.abs((hitbox.y + hitbox.height/2) - (other.y + other.height/2)) <= hitbox.height/2 + other.height/2) {
			System.out.println("eated");
			return true;
		} else {
			return false;
		}
	}

	public void update(float delta, World world) {
		// alien = world.getAlien();
		// if (getEated(alien.getHitbox())) {
		// this.remove();
		// }
		// hitbox.x += 5;
		if (targetFood == null) {
			randomMove(delta);
		} else {
			moveTowardTarget(delta, targetFood);
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

		if (time >= 3) {
			axisX = rand.nextInt(2);
			axisY = rand.nextInt(2);
			time = 0;
		}

		if (hitbox.x <= 0) {
			axisX = 1;
			time = -5;
			System.out.println("fuck");
		}
		if (hitbox.y <= 0) {
			axisY = 1;
			time = -5;
		}
		if (hitbox.x + hitbox.width >= World.width) {
			axisX = 0;
			time = -5;
			System.out.println("fuck");
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
			System.out.println("hitboxX: " + hitbox.x + " " + "hitboxY: " + hitbox.y + " " + "axisX: " + axisX + " "
					+ " " + "axisY: " + axisY);
		}
	}
	
	public boolean noTarget() {
		if (targetFood == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setTarget(Food target) {
		targetFood = target;
		System.out.println(targetFood);
	}

	// public Vector2 getPosition() {
	// return position;
	// }

	public Rectangle getHitbox() {
		return hitbox;
	}
}
