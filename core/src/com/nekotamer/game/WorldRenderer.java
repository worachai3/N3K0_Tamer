package com.nekotamer.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

		Vector2 catPos = cat.getPosition();
		Vector2 alienPos = alien.getPosition();
		
		batch.begin();
		for(Food food : foodList) {
			Vector2 foodPos = food.getPosition();
			batch.draw(foodImg, foodPos.x, foodPos.y);
		}
		batch.draw(catImg, catPos.x, catPos.y);
		batch.draw(alienImg, alienPos.x, alienPos.y);
		
		batch.end();
	}
}
