package com.nekotamer.game;

import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;

public class Food {
	// private Vector2 position;
	private Rectangle hitbox;
	private Cat cat;

	public Food(int x, int y, World world) {
		// position = new Vector2(x, y);
		hitbox = new Rectangle(x, y, 30, 30);
	}

// public Vector2 getPosition() {
// return position;
// }

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

	public Rectangle getHitbox() {
		return hitbox;
	}

//	public void remove() {
//
//	}

	public void update(float delta, World world) {
		// cat = world.getCat();
		// if (getEated(cat.getHitbox())) {
		// this.remove();
		// }
	}
}
