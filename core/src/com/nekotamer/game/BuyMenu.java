package com.nekotamer.game;

import java.awt.Rectangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BuyMenu {
	private Texture notHoverIcon;
	private Texture hoverIcon;
	private Rectangle hitbox;
	private boolean clicked;
	private boolean stop;

	private int nCat;

	public BuyMenu(int x, int y) {
		hitbox = new Rectangle(x, y, 100, 200);
		notHoverIcon = new Texture("catmenunothover.png");
		hoverIcon = new Texture("catmenuhover.png");
		stop = false;
	}

	public void update(float delta) {
		if (getClicked()) {
			if (!stop) {
				nCat += 1;
				stop = true;
				System.out.println(nCat);
			}
		}
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void render(float delta, SpriteBatch batch) {
		if (getHover(Gdx.input.getX(), Gdx.input.getY())) {
			batch.draw(hoverIcon, hitbox.x, hitbox.y);
		} else {
			batch.draw(notHoverIcon, hitbox.x, hitbox.y);
		}
	}

	private boolean getHover(int x, int y) {
		if (hitbox.x <= x && hitbox.y <= 720 - y && hitbox.x + hitbox.width >= x) {
			return true;
		} else {
			return false;
		}
	}
	
	public void bought() {
		nCat--;
	}

	public int spawnNCat() {
		return nCat;
	}

	public boolean getClicked() {
		int x = Gdx.input.getX();
		int y = Gdx.input.getY();
		if (getHover(x, y) && Gdx.input.isButtonPressed(0) &&!clicked) {
			if (hitbox.x <= x && hitbox.y <= 720 - y && hitbox.x + hitbox.width >= x && hitbox.y + hitbox.height >= 720 - y) {
				return true;
			} 
		}
		if (!Gdx.input.isButtonPressed(0)) {
			clicked = false;
		} else {
			clicked = true;
		}
		stop = false;
		return false;
	}
}
