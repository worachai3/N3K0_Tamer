package com.nekotamer.game;

public class Player {
	private int money;

	public Player() {
		money = 10;
	}

	public void addMoney() {
		money += 5;
	}

	public int getMoney() {
		return money;
	}

}
