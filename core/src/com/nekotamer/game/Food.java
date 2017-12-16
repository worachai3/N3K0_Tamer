package com.nekotamer.game;

import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;

public class Food {
//	private Vector2 position;
	private Rectangle hitbox;

	public Food(int x, int y) {
//		position = new Vector2(x, y);
		hitbox = new Rectangle(x, y, 30, 30);
	}

//	public Vector2 getPosition() {
//		return position;
//	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
}
