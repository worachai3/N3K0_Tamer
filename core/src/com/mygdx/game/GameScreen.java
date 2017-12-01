package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private NekoTamerGame nekoTamerGame;
	
	private Texture catImg;
	
	public GameScreen(NekoTamerGame nekoTamerGame) {
		this.nekoTamerGame = nekoTamerGame;
		
		catImg = new Texture("cat.png");	
	}
	
	public void render(float delta) {
		SpriteBatch batch = nekoTamerGame.batch;
		batch.begin();
		batch.draw(catImg, 100, 100);
		batch.end();
	}
}
