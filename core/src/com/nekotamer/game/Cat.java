package com.nekotamer.game;

import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;

public class Cat {
//	private Vector2 position;
	private Rectangle hitbox;
	
	public Cat(int x, int y) {
//		position = new Vector2(x, y);
		hitbox = new Rectangle(x, y, 142, 119);
	}

	public void update(float delta, World world) {
		
	}

//	public Vector2 getPosition() {
//		return position;
//	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
}
