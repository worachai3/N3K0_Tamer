package com.nekotamer.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
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
	private ArrayList<Food> foodList;

	private Texture foodImg;
	private Texture catImg3;
	private Texture catImg2;
	private Texture catImg1;
	private Texture alienImg;

	public WorldRenderer(NekoTamer nekotTamer, World world) {
		this.nekoTamer = nekoTamer;
		this.world = world;

		buyMenu = world.getBuyMenu();
		catList = world.getCat();
		alienList = world.getAlien();
		foodList = world.getFood();

		catImg3 = new Texture("cathunger3.png");
		catImg2 = new Texture("cathunger2.png");
		catImg1 = new Texture("cathunger1.png");
		alienImg = new Texture("alien.png");
		foodImg = new Texture("food.png");
	}

	public void render(float delta, SpriteBatch batch) {

		batch.begin();
		for (Food food : foodList) {
			Rectangle foodHitbox = food.getHitbox();
			batch.draw(foodImg, foodHitbox.x, foodHitbox.y, foodHitbox.width, foodHitbox.height);
		}
		for (Cat cat : catList) {
			Rectangle catHitbox = cat.getHitbox();
			int hunger = cat.getHunger();
			if (hunger > 3) {
				batch.draw(catImg3, catHitbox.x, catHitbox.y, catHitbox.width, catHitbox.height);
			}
			else if (hunger > 1) {
				batch.draw(catImg2, catHitbox.x, catHitbox.y, catHitbox.width, catHitbox.height);
			}
			else if (hunger == 1){
				batch.draw(catImg1, catHitbox.x, catHitbox.y, catHitbox.width, catHitbox.height);
			}
		}
		for (Alien alien : alienList) {
			Rectangle alienHitbox = alien.getHitbox();
			batch.draw(alienImg, alienHitbox.x, alienHitbox.y, alienHitbox.width, alienHitbox.height);
		}
		buyMenu.render(delta, batch);
		batch.end();
	}
}
