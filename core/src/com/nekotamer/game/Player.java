package com.nekotamer.game;

public class Player {
	private int money;

	public Player() {
		money = 10;
	}

	public void addMoney(int x) {
		money += x;
	}

	public void removeMoney(int x) {
		money -= x;
	}
	
	public int getMoney() {
		return money;
	}
}
