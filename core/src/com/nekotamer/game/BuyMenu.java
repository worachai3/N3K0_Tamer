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
	private Player player;

	private int nCat;
	
	private static final int catPrice = 10;

	public BuyMenu(int x, int y, Player player) {
		hitbox = new Rectangle(x, y, 100, 200);
		notHoverIcon = new Texture("catmenunothover.png");
		hoverIcon = new Texture("catmenuhover.png");
		stop = false;
		this.player = player;
	}

	public void update(float delta) {
		if (getClicked() && !stop) {
			if (this.player.getMoney() >= 10) {
				nCat += 1;
				this.player.removeMoney(catPrice);
				stop = true;
				System.out.println(nCat);
			}
		}
	}

	public void render(float delta, SpriteBatch batch) {
		if (getHover(Gdx.input.getX(), Gdx.input.getY())) {
			batch.draw(hoverIcon, hitbox.x, hitbox.y);
		} else {
			batch.draw(notHoverIcon, hitbox.x, hitbox.y);
		}
	}

	
	public void bought() {
		nCat--;
	}

	public int spawnNCat() {
		return nCat;
	}

	private boolean getHover(int x, int y) {
		if (hitbox.x <= x && hitbox.y <= 720 - y && hitbox.x + hitbox.width >= x) {
			return true;
		} else {
			return false;
		}
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
