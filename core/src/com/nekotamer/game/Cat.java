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

	private static final int speed = 10;

	public Cat(int x, int y) {
		// position = new Vector2(x, y);
		hitbox = new Rectangle(x, y, 142, 119);
	}

	public boolean getEated(Rectangle other) {
		hitbox = getHitbox();
		if (hitbox.x >= other.x && hitbox.y >= other.y && hitbox.x + hitbox.width <= other.x + other.width
				&& hitbox.y + hitbox.height <= other.y + other.height) {
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
		}
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
		if (hitbox.y + hitbox.height>= World.height) {
			axisY = 0;
			time = -5;
		}
		
		if (distance > 0) {
			if (axisX == 1) {
				hitbox.x += x / distance * speed * delta * 10;
			} else {
				hitbox.x -= x / distance * speed * delta * 10;
			}
			if (axisY == 1) {
				hitbox.y += y / distance * speed * delta * 10;
			} else {
				hitbox.y -= y / distance * speed * delta * 10;
			}
			System.out.println("hitboxX: "+hitbox.x+" "+"hitboxY: "+hitbox.y+" "+"axisX: "+axisX+" "+" "+"axisY: "+axisY);
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
