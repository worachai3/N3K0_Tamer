package com.nekotamer.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

public class World {
	public static final int width = 1200;
	public static final int height = 720;
	
	private NekoTamer nekoTamer;
	private Cat cat;
	private Alien alien;
//	private Food food;
	ArrayList<Food> foodList;
	
	private boolean clicked;

	World(NekoTamer nekoTamer) {
		this.nekoTamer = nekoTamer;
		cat = new Cat(100, 100);
		alien = new Alien(800, 200);
		foodList = new ArrayList<Food>();
	}

	public void update(float delta) {
		cat.update(delta);
		alien.update(delta);

		if (Gdx.input.isButtonPressed(0) && !clicked) {
			foodList.add(new Food(Gdx.input.getX(), height-Gdx.input.getY()));
		}
		if (!Gdx.input.isButtonPressed(0)) {
			clicked = false;
		} else {
			clicked = true;
		}
	}
	
	public ArrayList<Food> getFood() {
		return foodList;
	}

	public Cat getCat() {
		return cat;
	}

	public Alien getAlien() {
		return alien;
	}
}
