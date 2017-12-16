package com.nekotamer.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	private NekoTamer nekoTamer;
	private World world;	
	private Alien alien;
	private Cat cat;
	private ArrayList<Food> foodList;
	
	private Texture foodImg;
	private Texture catImg;
	private Texture alienImg;


	public WorldRenderer(NekoTamer nekotTamer, World world) {
		this.nekoTamer = nekoTamer;
		this.world = world;

		cat = world.getCat();
		alien = world.getAlien();
		foodList = world.getFood();

		catImg = new Texture("cat.png");
		alienImg = new Texture("alien.png");
		foodImg = new Texture("food.png");
	}

	public void render(float delta, SpriteBatch batch) {

//		Vector2 catPos = cat.getPosition();
//		Vector2 alienPos = alien.getPosition();
		Rectangle catHitbox = cat.getHitbox();
		Rectangle alienHitbox = alien.getHitbox();
		
		batch.begin();
		for(Food food : foodList) {
//			Vector2 foodPos = food.getPosition();
			Rectangle foodHitbox = food.getHitbox();
			batch.draw(foodImg, foodHitbox.x, foodHitbox.y, foodHitbox.width, foodHitbox.height);
		}
		batch.draw(catImg, catHitbox.x, catHitbox.y, catHitbox.width, catHitbox.height);
		batch.draw(alienImg, alienHitbox.x, alienHitbox.y, alienHitbox.width, alienHitbox.height);
		batch.end();
	}
}
