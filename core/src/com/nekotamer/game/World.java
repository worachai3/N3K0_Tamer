package com.nekotamer.game;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class World {
	public static final int width = 1200;
	public static final int height = 720;

	private float time = 0;

	private NekoTamer nekoTamer;
	// private Cat cat;
	// private Alien alien;
	// private Food food;
	ArrayList<Alien> alienList;
	ArrayList<Cat> catList;
	ArrayList<Food> foodList;
	// ArrayList<Food> removedFoodList;
	// Iterator<Food> addFoodList;

	private boolean clicked = false;

	World(NekoTamer nekoTamer) {
		this.nekoTamer = nekoTamer;
		// cat = new Cat(100, 100);
		// alien = new Alien(800, 200, 5);
		alienList = new ArrayList<Alien>();
		catList = new ArrayList<Cat>();
		foodList = new ArrayList<Food>();

		// test
		catList.add(new Cat(100, 100));
		alienList.add(new Alien(800, 200, 5));
	}

	public void update(float delta) {
		time += delta;
		// System.out.println(time);
		// cat.update(delta, this);
		// alien.update(delta, this);

		for (int i = 0; i < catList.size(); i++) {
			// food.update(delta, this);
			Cat cat = catList.get(i);
			cat.update(delta, this);
			for (int j = 0; j < foodList.size(); j++) {
				Food food = foodList.get(j);
				if (food.getEated(cat.getHitbox())) {
					// System.out.println(i);
					// Food i = foodList.get(foodList.indexOf(food));
					// removedFoodList.add(food);
					foodList.remove(food);
					j--;
				}
			}
		}
		
		for(int i = 0;i < alienList.size(); i++) {
			Alien alien = alienList.get(i);
			alien.update(delta, this);
			if(alien.isDead()) {
				alienList.remove(alien);
				i--;
			}
		}
		
		if (Gdx.input.isButtonPressed(1) && !clicked) {
			foodList.add(new Food(Gdx.input.getX(), height - Gdx.input.getY(), this));
		}

		if (!Gdx.input.isButtonPressed(1)) {
			clicked = false;
		} else {
			clicked = true;
		}
	}

	public ArrayList<Food> getFood() {
		return foodList;
	}

	public ArrayList<Cat> getCat() {
		return catList;
	}

	public ArrayList<Alien> getAlien() {
		return alienList;
	}
}
