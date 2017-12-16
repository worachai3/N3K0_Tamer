package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private NekoTamerGame nekoTamerGame;
	
	private int randomNum;
	private Texture catImg;
	
	private int x;
	private int y;
	private int accelX;
	private int accelY;
	private int speed;
	
	private float countDown;
	
	public GameScreen(NekoTamerGame nekoTamerGame) {
		this.nekoTamerGame = nekoTamerGame;
		catImg = new Texture("cat.png");
		
		speed = 5;
		accelX = 0;
		accelY = 0;
		x = 100;
		y = 100;
		
	}
	
	public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        x += speed*accelX;
        y += speed*accelY;
        
        countDown += delta;
        
		SpriteBatch batch = nekoTamerGame.batch;
		batch.begin();
		batch.draw(catImg, x, y);
		batch.end();
		randomNum = (int)(Math.random() * 10);
		System.out.println(countDown);
		if(countDown > 1) {
			countDown = 0;
			if(randomNum >= 8) {
				accelX = 1;
			}
			else if(randomNum > 4) {
				accelX = -1;
			}
			else {
				accelX = 0;
			}
			randomNum = (int)(Math.random() * 10);
			if(randomNum >= 8) {
				accelY = 1;
			}
			else if(randomNum > 4) {
				accelY = -1;
			}
			else {
				accelY = 0;
			}
		}
	}
}
