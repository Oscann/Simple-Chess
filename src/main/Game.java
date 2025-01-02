package main;

import objects.ObjectManager;
import rendering.Panel;

public class Game {

	private static GameManager gameManager;

	public static void main(String[] args) {
		gameManager = new GameManager();
		gameManager.startGame();
	}

	public static GameManager getGameManager() {
		return gameManager;
	}

	public static ObjectManager getObjManager() {
		return gameManager.getObjectManager();
	}

	public static Panel getPanel() {
		return gameManager.getPanel();
	}
}
