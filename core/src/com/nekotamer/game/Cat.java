package com.nekotamer.game;

import com.badlogic.gdx.math.Vector2;

public class Cat {
	private Vector2 position;

	public Cat(int x, int y) {
		position = new Vector2(x, y);
	}

	public void update(float delta) {

	}

	public Vector2 getPosition() {
		return position;
	}
}
