package com.nekotamer.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private NekoTamer nekoTamer;
	WorldRenderer worldRenderer;
	World world;

	public GameScreen(NekoTamer nekoTamer) {
		this.nekoTamer = nekoTamer;

		world = new World(nekoTamer);
		worldRenderer = new WorldRenderer(nekoTamer, world);
	}

	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		SpriteBatch batch = nekoTamer.batch;

		worldRenderer.render(delta, batch);
	}

	public void update(float delta) {
		world.update(delta);
	}
}
