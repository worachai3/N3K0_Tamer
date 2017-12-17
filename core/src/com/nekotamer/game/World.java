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
				if(cat.noTarget()) {
					cat.setTarget(food);
				}
				if (food.getEated(cat.getHitbox())) {
					// System.out.println(i);
					// Food i = foodList.get(foodList.indexOf(food));
					// removedFoodList.add(food);
					cat.setTarget(null);
					foodList.remove(food);
					j--;
				}
			}
		}

		for (int i = 0; i < alienList.size(); i++) {
			Alien alien = alienList.get(i);
			alien.update(delta, this);
			for (int j = 0; j < catList.size(); j++) {
				Cat cat = catList.get(j);
				if (cat.getEated(alien.getHitbox())) {
					// System.out.println(i);
					// Food i = foodList.get(foodList.indexOf(food));
					// removedFoodList.add(food);
					catList.remove(cat);
					j--;
					if (alien.isDead()) {
						alienList.remove(alien);
						i--;
					}
				}
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

	public float getAngle(float x, float y, float x2, float y2) {
		float angle = (float) Math.toDegrees(Math.atan2(y - y2, x - x2));

		if (angle < 0) {
			angle += 360;
		}

		return angle;
	}

	// private boolean catIsCloser(Alien alien, Cat cat, float oldDistance){
	// // calculate angle from enemy to player
	// Rectangle alienPos = alien.getHitbox();
	// Rectangle catPos = cat.getHitbox();
	// float angle = getAngle(alienPos.x, alienPos.y, catPos.x, catPos.y);
	// // work out how much x and y will change in this step
	// // math.cos and math.sin will be between -1 and +1
	// // multiplying by (dt*speed) means the enemy will move speed pixels in one
	// whole second
	// float dx = (float) Math.cos(angle);
	// float dy = (float) Math.sin(angle);
	// float distance = (float) Math.sqrt(dx*dx+dy*dy);
	// // move to our new x and y
	// // alienPos.x = alienPos.x + dx
	// // alienPos.y = alienPos.y + dy
	// if (distance < oldDistance) {
	// return true;
	// } else {
	// return false;
	// }
	// }

	// private boolean foodIsCloser(Cat cat, Food food, float oldDistance){
	// // calculate angle from enemy to player
	// Rectangle foodPos = food.getHitbox();
	// Rectangle catPos = cat.getHitbox();
	// float angle = getAngle(foodPos.x, foodPos.y, catPos.x, catPos.y);
	// System.out.println(angle);
	// // work out how much x and y will change in this step
	// // math.cos and math.sin will be between -1 and +1
	// // multiplying by (dt*speed) means the enemy will move speed pixels in one
	// whole second
	// float dx = (float) Math.cos(angle);
	// float dy = (float) Math.sin(angle);
	// float distance = (float) Math.sqrt(dx*dx+dy*dy);
	// // move to our new x and y
	// // alienPos.x = alienPos.x + dx
	// // alienPos.y = alienPos.y + dy
	//// System.out.println(distance+" "+oldDistance);
	// if (distance < oldDistance) {
	// cat.setDistance(distance);
	// return true;
	// } else {
	// return false;
	// }
	// }

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
