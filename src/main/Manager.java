package main;

import objects.ObjectManager;
import objects.Team;
import rendering.Panel;

public class Manager {

	public static Team teamToPlay = Team.WHITE;
	private ObjectManager manager;
	private Panel panel;

	public Manager(Panel panel) {

		this.panel = panel;
		this.manager = new ObjectManager(panel);

	}

	public void startGame() {
		manager.setBoard();
	}

	public void alternateTeamToPlay() {

		if (teamToPlay == Team.WHITE)
			teamToPlay = Team.BLACK;
		else
			teamToPlay = Team.WHITE;

	}

	public void checkCheck() {

	}

	public ObjectManager getObjectManager() {
		return manager;
	}

}
