package com.nekotamer.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;

public class World {
	public static final int width = 1200;
	public static final int height = 720;

	private float time = 0;
	private int nCat;
	private int nAlien;
	private boolean win;
	private boolean lose;

	private NekoTamer nekoTamer;
	private BuyMenu buyMenu;
	private Player player;

	private ArrayList<Alien> alienList;
	private ArrayList<Cat> catList;
	private ArrayList<Food> foodList;
	private ArrayList<Coin> coinList;

	private boolean clicked = false;

	World(NekoTamer nekoTamer) {
		this.nekoTamer = nekoTamer;
		player = new Player();
		buyMenu = new BuyMenu(0, height - 200, player);
		alienList = new ArrayList<Alien>();
		catList = new ArrayList<Cat>();
		coinList = new ArrayList<Coin>();
		foodList = new ArrayList<Food>();

		win = false;
		lose = false;
		// Start
		catList.add(new Cat(100, 100, 5));
		nCat++;
		nAlien = 1;
	}

	private void spawnCat(int n) {
		for (int i = 0; i < n; i++) {
			Random rand = new Random();
			catList.add(new Cat(rand.nextInt(width - 300), rand.nextInt(height - 300), 5));
			nCat++;
			buyMenu.bought();
		}
	}

	public void update(float delta) {
		Random rand = new Random();
		time += delta;
		buyMenu.update(delta);
		spawnCat(buyMenu.spawnNCat());

		if (nCat <= 0) {
			lose = true;
		} else if (nCat >= 10) {
			win = true;
		}
		if (!win && !lose) {
			if (time >= 6) {
				nAlien++;
				for (int i = 0; i < nAlien; i++) {
					alienList.add(new Alien(rand.nextInt(width), 1000, 5));
				}
				time = 0;
			}

			for (int i = 0; i < catList.size(); i++) {
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
				if (cat.getSpawnCoin()) {
					coinList.add(new Coin((int) cat.getHitbox().x, (int) cat.getHitbox().y));
					cat.setSpawnCoin();
				}
				if (cat.isDead()) {
					catList.remove(cat);
					nCat--;
				}
			}

			for (int i = 0; i < coinList.size(); i++) {
				Coin coin = coinList.get(i);
				coin.update(delta);
				if (coin.isCollected()) {
					coinList.remove(coin);
					i--;
					player.addMoney(3);
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
						nCat--;
						j--;
					}
				}
				if (alien.isDead()) {
					alienList.remove(alien);
					i--;
				}
			}
		}

		if (Gdx.input.isButtonPressed(1) && !clicked && player.getMoney() >= 1) {
			foodList.add(new Food(Gdx.input.getX(), height - Gdx.input.getY(), this));
			player.removeMoney(1);
		}

		if (!Gdx.input.isButtonPressed(1)) {
			clicked = false;
		} else {
			clicked = true;
		}
	}
	
	public boolean getWin() {
		return win;
	}
	
	public boolean getLose() {
		return lose;
	}

	public Player getPlayer() {
		return player;
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

	public ArrayList<Coin> getCoin() {
		return coinList;
	}
}
