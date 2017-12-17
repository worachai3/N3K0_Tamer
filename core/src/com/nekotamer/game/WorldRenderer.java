package com.nekotamer.game;

import java.util.ArrayList;
//import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	private NekoTamer nekoTamer;
	private World world;
	private Alien alien;
	private Cat cat;
	private BuyMenu buyMenu;
	private ArrayList<Alien> alienList;
	private ArrayList<Cat> catList;
	private ArrayList<Food> foodList;
	// private Iterator<Food> foodList;

	private Texture foodImg;
	private Texture catImg;
	private Texture alienImg;

	public WorldRenderer(NekoTamer nekotTamer, World world) {
		this.nekoTamer = nekoTamer;
		this.world = world;

		buyMenu = world.getBuyMenu();
		catList = world.getCat();
		alienList = world.getAlien();
		foodList = world.getFood();

		catImg = new Texture("cat.png");
		alienImg = new Texture("alien.png");
		foodImg = new Texture("food.png");
	}

	public void render(float delta, SpriteBatch batch) {

		// Vector2 catPos = cat.getPosition();
		// Vector2 alienPos = alien.getPosition();

		batch.begin();
		buyMenu.render(delta, batch);
		for (Food food : foodList) {
			// Vector2 foodPos = food.getPosition();
			Rectangle foodHitbox = food.getHitbox();
			batch.draw(foodImg, foodHitbox.x, foodHitbox.y, foodHitbox.width, foodHitbox.height);
		}
		for (Cat cat : catList) {
			Rectangle catHitbox = cat.getHitbox();
			batch.draw(catImg, catHitbox.x, catHitbox.y, catHitbox.width, catHitbox.height);
		}
		for (Alien alien : alienList) {
			Rectangle alienHitbox = alien.getHitbox();
			batch.draw(alienImg, alienHitbox.x, alienHitbox.y, alienHitbox.width, alienHitbox.height);
		}
		batch.end();
	}
}
