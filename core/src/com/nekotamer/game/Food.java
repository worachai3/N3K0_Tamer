package com.nekotamer.game;

import com.badlogic.gdx.math.Vector2;

public class Food {
	private Vector2 position;

	public Food(int x, int y) {
		position = new Vector2(x, y);
	}

	public Vector2 getPosition() {
		return position;
	}
}
