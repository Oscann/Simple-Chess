package main;

import java.awt.Graphics;

import inputs.MouseInputs;
import objects.ObjectManager;
import objects.Piece;
import objects.Team;
import positioning.Coordinates;
import rendering.Panel;
import rendering.Window;

public class GameManager {

	public Team teamToPlay = Team.WHITE;
	private ObjectManager objmanager;
	private Panel panel;
	private MouseInputs inputs;

	public GameManager() {
		objmanager = new ObjectManager(this);
		inputs = new MouseInputs(this);

		Window window = new Window(this);

		panel = window.getPanel();
	}

	public void render(Graphics g) {
		objmanager.renderObjects(g);
	}

	public void startGame() {
		objmanager.setBoard();
		objmanager.updatePiecesMoves();
	}

	public void handleMove(Piece p, Coordinates newPosition) {
		objmanager.update(p, newPosition);

		alternateTeamToPlay();
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
		return objmanager;
	}

	public Panel getPanel() {
		return panel;
	}

	public MouseInputs getInputs() {
		return inputs;
	}

}
