package com.nekotamer.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;

public class World {
	public static final int width = 1200;
	public static final int height = 720;

	private float time = 0;

	private NekoTamer nekoTamer;
	private ArrayList<Alien> alienList;
	private ArrayList<Cat> catList;
	private ArrayList<Food> foodList;
	private BuyMenu buyMenu;

	private boolean clicked = false;

	World(NekoTamer nekoTamer) {
		this.nekoTamer = nekoTamer;
		buyMenu = new BuyMenu(0, height - 200);
		alienList = new ArrayList<Alien>();
		catList = new ArrayList<Cat>();
		foodList = new ArrayList<Food>();

		// Start
		catList.add(new Cat(100, 100, 5));
		alienList.add(new Alien(800, 200, 5));
	}
	
	private void spawnCat(int n) {
		for (int i = 0;i < n;i++) {
			Random rand = new Random();
			catList.add(new Cat(rand.nextInt(width - 300), rand.nextInt(height - 300),5));
			buyMenu.bought();
		}
	}

	public void update(float delta) {
		time += delta;
		
		buyMenu.update(delta);
		spawnCat(buyMenu.spawnNCat());
		for (int i = 0; i < catList.size(); i++) {
			// food.update(delta, this);
			Cat cat = catList.get(i);
			cat.update(delta, this);
			for (int j = 0; j < foodList.size(); j++) {
				Food food = foodList.get(j);
				if (cat.noTarget() && cat.getHunger() < 5) {
					cat.setTarget(food);
				}
				if (food.getEated(cat.getHitbox()) && cat.getHunger() < 5) {
					for (Cat temp : catList) {
						temp.setTarget(null);
					}
					cat.fed();
					foodList.remove(food);
					j--;
				}
			}
			if (cat.isDead()) {
				catList.remove(cat);
			}
		}

		for (int i = 0; i < alienList.size(); i++) {
			Alien alien = alienList.get(i);
			alien.update(delta, this);
			for (int j = 0; j < catList.size(); j++) {
				Cat cat = catList.get(j);
				if (alien.noTarget()) {
					alien.setTarget(cat);
				}
				if (cat.getEated(alien.getHitbox())) {
					alien.setTarget(null);
					catList.remove(cat);
					j--;
				}

			}
			if (alien.isDead()) {
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

	public BuyMenu getBuyMenu() {
		return buyMenu;
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
