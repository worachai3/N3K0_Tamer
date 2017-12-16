package com.nekotamer.game;

import com.badlogic.gdx.math.Vector2;

public class Alien {
	private Vector2 position;

	public Alien(int x, int y) {
		position = new Vector2(x, y);
	}

	public void update(float delta) {

	}

	public Vector2 getPosition() {
		return position;
	}
}
