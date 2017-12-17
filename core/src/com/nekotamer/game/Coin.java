package com.nekotamer.game;

import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.Gdx;

public class Coin {
	private Rectangle hitbox;
	private boolean clicked;
	private boolean collected;

	public Coin(int x, int y) {
		hitbox = new Rectangle(x, y, 100, 100);
		collected = false;
	}

	public void update(float delta) {
		if (Gdx.input.isButtonPressed(0) && !clicked) {
			if (getClicked(Gdx.input.getX(), Gdx.input.getY())) {
				collected = true;
			}
		}
		if (!Gdx.input.isButtonPressed(0)) {
			clicked = false;
		} else {
			clicked = true;
		}
	}

	public boolean isCollected() {
		return collected;
	}

	public boolean getClicked(int x, int y) {
		if (hitbox.x <= x && hitbox.y <= 720 - y && hitbox.x + hitbox.width >= x
				&& hitbox.y + hitbox.height >= 720 - y) {
			return true;
		} else {
			return false;
		}
	}

	public Rectangle getHitbox() {
		return hitbox;
	}
}
