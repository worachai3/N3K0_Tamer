package com.nekotamer.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;

public class Alien {
//	private Vector2 position;
	private Rectangle hitbox;
	private boolean clicked = false;
	private int hp;

	public Alien(int x, int y, int hp) {
//		position = new Vector2(x, y);
		hitbox = new Rectangle(x, y, 343, 373);
		this.hp = hp;
	}

	public void update(float delta, World world) {
		if (Gdx.input.isButtonPressed(0) && !clicked) {
			if(getClicked(Gdx.input.getX(), Gdx.input.getY())) {
				
			}

		}
		if (!Gdx.input.isButtonPressed(0)) {
			clicked = false;
		} else {
			clicked = true;
		}
	}
	
	public boolean getClicked(int x, int y) {
		hitbox = getHitbox();
		System.out.println(x+" "+y);
		if (hitbox.x <= x && hitbox.y <= y && hitbox.x + hitbox.width >= x
				&& hitbox.y + hitbox.height >= y) {
			System.out.println("clicked");
			this.hp -= 1;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isDead() {
		if(this.hp <= 0) {
			return true;
		}
		return false;
	}

//	public Vector2 getPosition() {
//		return position;
//	}
	public Rectangle getHitbox() {
		return hitbox;
	}
}
