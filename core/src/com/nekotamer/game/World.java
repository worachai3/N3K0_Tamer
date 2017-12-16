package com.nekotamer.game;

import com.badlogic.gdx.Gdx;

public class World {
	private NekoTamer nekoTamer;
	private Cat cat;
	private Alien alien;

	World(NekoTamer nekoTamer) {
		this.nekoTamer = nekoTamer;
		cat = new Cat(100, 100);
		Food[] foods = new Food[20];
		alien = new Alien(800, 200);
	}

	public void update(float delta) {
		cat.update(delta);
		alien.update(delta);
	}
	
	public Cat getCat() {
		return cat;
	}
	
	public Alien getAlien() {
		return alien;
	}
}
