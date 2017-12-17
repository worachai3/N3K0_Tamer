package com.nekotamer.game;

import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;

public class Cat {
//	private Vector2 position;
	private Rectangle hitbox;
	private Alien alien;
	
	public Cat(int x, int y) {
//		position = new Vector2(x, y);
		hitbox = new Rectangle(x, y, 142, 119);
	}
	
	public boolean getEated(Rectangle other) {
		hitbox = getHitbox();
		if(hitbox.x >= other.x && hitbox.y >= other.y && hitbox.x+hitbox.width <= other.x+other.width && hitbox.y+hitbox.height <= other.y+other.height) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void update(float delta, World world) {
//		alien = world.getAlien();
//		if (getEated(alien.getHitbox())) {
//			this.remove();
//		}
		hitbox.x += 5;
	}
	
	private void remove() {
	
	}

//	public Vector2 getPosition() {
//		return position;
//	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
}
