package com.nekotamer.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;

public class Alien {
//	private Vector2 position;
	private Rectangle hitbox;

	public Alien(int x, int y, int hp) {
//		position = new Vector2(x, y);
		hitbox = new Rectangle(x, y, 343, 373);
	}

	public void update(float delta, World world) {
		if(getClicked(Gdx.input.getX(), Gdx.input.getY())) {
			
		}
	}
	
	public boolean getClicked(int x, int y) {
		hitbox = getHitbox();
		if (hitbox.x >= x && hitbox.y >= y && hitbox.x + hitbox.width <= x
				&& hitbox.y + hitbox.height <= y) {
			System.out.println("clicked");
			return true;
		} else {
			return false;
		}
	}

//	public Vector2 getPosition() {
//		return position;
//	}
	public Rectangle getHitbox() {
		return hitbox;
	}
}
