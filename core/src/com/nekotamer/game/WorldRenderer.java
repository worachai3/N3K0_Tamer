package com.nekotamer.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class WorldRenderer {
	private NekoTamer nekoTamer;
	private World world;
	private Alien alien;
	private Cat cat;
	private BuyMenu buyMenu;
	private ArrayList<Alien> alienList;
	private ArrayList<Cat> catList;
	private ArrayList<Coin> coinList;
	private ArrayList<Food> foodList;

	private BitmapFont font;
	private String text;

	private Texture alienImg;
	private Texture backgroundImg;
	private Texture catImg1;
	private Texture catImg2;
	private Texture catImg3;
	private Texture coinImg;
	private Texture foodImg;
	private Texture winImg;
	private Texture loseImg;

	public WorldRenderer(NekoTamer nekotTamer, World world) {
		this.nekoTamer = nekoTamer;
		this.world = world;

		alienList = world.getAlien();
		buyMenu = world.getBuyMenu();
		catList = world.getCat();
		coinList = world.getCoin();
		foodList = world.getFood();

		backgroundImg = new Texture("background.png");
		catImg3 = new Texture("cathunger3.png");
		catImg2 = new Texture("cathunger2.png");
		catImg1 = new Texture("cathunger1.png");
		coinImg = new Texture("coin.png");
		alienImg = new Texture("alien.png");
		foodImg = new Texture("food.png");
		winImg = new Texture("win.png");
		loseImg = new Texture("lose.png");

		font = new BitmapFont();
		font.setColor(Color.WHITE);
	}

	public void render(float delta, SpriteBatch batch) {
		text = "Money: " + " " + world.getPlayer().getMoney();
		batch.begin();
		batch.draw(backgroundImg, 0, 0, world.width, world.height);
		if (!world.getLose() && !world.getWin()) {
			font.draw(batch, text, 20, 20);
			for (Food food : foodList) {
				Rectangle foodHitbox = food.getHitbox();
				batch.draw(foodImg, foodHitbox.x, foodHitbox.y, foodHitbox.width, foodHitbox.height);
			}
			for (Cat cat : catList) {
				Rectangle catHitbox = cat.getHitbox();
				int hunger = cat.getHunger();
				if (hunger > 3) {
					batch.draw(catImg3, catHitbox.x, catHitbox.y, catHitbox.width, catHitbox.height);
				} else if (hunger > 1) {
					batch.draw(catImg2, catHitbox.x, catHitbox.y, catHitbox.width, catHitbox.height);
				} else if (hunger == 1) {
					batch.draw(catImg1, catHitbox.x, catHitbox.y, catHitbox.width, catHitbox.height);
				}
			}
			for (Coin coin : coinList) {
				if (coin != null) {
					Rectangle coinHitbox = coin.getHitbox();
					batch.draw(coinImg, coinHitbox.x, coinHitbox.y, coinHitbox.width, coinHitbox.height);
				}
			}
			for (Alien alien : alienList) {
				Rectangle alienHitbox = alien.getHitbox();
				batch.draw(alienImg, alienHitbox.x, alienHitbox.y, alienHitbox.width, alienHitbox.height);
			}
			buyMenu.render(delta, batch);
		}
		
		else if (world.getWin()) {
			batch.draw(winImg, 0, 0, world.width, world.height);
		}
		else if (world.getLose()) {
			batch.draw(loseImg, 0, 0, world.width, world.height);
		}
		batch.end();
	}
}
