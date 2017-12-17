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
	private Cat cat;
	private Alien alien;
	// private Food food;
	ArrayList<Food> foodList;
	ArrayList<Food> removedFoodList;
//	Iterator<Food> addFoodList;

	private boolean clicked;

	World(NekoTamer nekoTamer) {
		this.nekoTamer = nekoTamer;
		cat = new Cat(100, 100);
		alien = new Alien(800, 200, 5);
		foodList = new ArrayList<Food>();
	}

	public void update(float delta) {
		time += delta;
		System.out.println(time);
		cat.update(delta, this);
		alien.update(delta, this);
		if (Gdx.input.isButtonPressed(0) && !clicked) {
			foodList.add(new Food(Gdx.input.getX(), height - Gdx.input.getY(), this));
		}
		for (int i = 0;i < foodList.size();i++) {
//			food.update(delta, this);
			Food food = foodList.get(i);
			if(food.getEated(cat.getHitbox())) {
//				System.out.println(i);
//				Food i = foodList.get(foodList.indexOf(food));
//				removedFoodList.add(food);
				foodList.remove(food);
				i--;
			}
		}
//		foodList.removeAll(removedFoodList);
//		removedFoodList.clear();

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
