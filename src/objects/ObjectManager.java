package objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;

import main.Manager;
import rendering.Panel;
import util.ObjectUtilities;

public class ObjectManager {

	Piece[] objects;
	public HashSet<Integer> blackKingCantMove;
	public HashSet<Integer> whiteKingCantMove;
	public ArrayList<Piece> blackTeam;
	public ArrayList<Piece> whiteTeam;
	private Dimension bounds;
	private Panel panel;
	private Manager manager;

	// public King blackKing, whiteKing;

	public ObjectManager(Manager manager) {
		blackKingCantMove = new HashSet<>();
		whiteKingCantMove = new HashSet<>();
		blackTeam = new ArrayList<>();
		whiteTeam = new ArrayList<>();

		this.manager = manager;
		this.panel = manager.getPanel();
		this.bounds = panel.getDimension();
		objects = new Piece[64];
	}

	public void render(Graphics g) {

		for (int i = 0; i < objects.length; i++) {
			if (objects[i] == null)
				continue;

			objects[i].render(g);
		}

	}

	public void update() {

		updateIndexes();
		updateKings();
		manager.checkCheck();
		manager.alternateTeamToPlay();

	}

	private void updateKings() {

		// blackKing.defineMovableIndexes();
		// whiteKing.defineMovableIndexes();

	}

	private void updateIndexes() {

		whiteKingCantMove.clear();
		blackKingCantMove.clear();

		for (int i = 0; i < blackTeam.size(); i++) {

			if (blackTeam.get(i).id == 5)
				continue;

			blackTeam.get(i).movableSpaces.clear();
			blackTeam.get(i).defineMovableIndexes();

			whiteKingCantMove.addAll(blackTeam.get(i).movableSpaces);

		}

		for (int i = 0; i < whiteTeam.size(); i++) {

			whiteTeam.get(i).movableSpaces.clear();
			whiteTeam.get(i).defineMovableIndexes();

			if (whiteTeam.get(i).id == 5)
				continue;

			blackKingCantMove.addAll(whiteTeam.get(i).movableSpaces);

		}

	}

	public void setBoard() {
		// createObject(Pieces.ROOK, 0, Team.BLACK);
		// createObject(Pieces.ROOK, 7, Team.BLACK);
		// createObject(Pieces.KNIGHT, 1, Team.BLACK);
		// createObject(Pieces.KNIGHT, 6, Team.BLACK);
		// createObject(Pieces.BISHOP, 2, Team.BLACK);
		// createObject(Pieces.BISHOP, 5, Team.BLACK);
		// blackKing = (King) createObject(Pieces.KING, 4, Team.BLACK);
		// createObject(Pieces.QUEEN, 3, Team.BLACK);

		// for (int i = 8; i < 16; i++) {

		// createObject(Pieces.PAWN, i, Team.BLACK);

		// }

		// createObject(Pieces.ROOK, 56, Team.WHITE);
		// createObject(Pieces.ROOK, 63, Team.WHITE);
		// createObject(Pieces.KNIGHT, 57, Team.WHITE);
		// createObject(Pieces.KNIGHT, 62, Team.WHITE);
		// createObject(Pieces.BISHOP, 58, Team.WHITE);
		// createObject(Pieces.BISHOP, 61, Team.WHITE);
		// whiteKing = (King) createObject(Pieces.KING, 60, Team.WHITE);
		// createObject(Pieces.QUEEN, 59, Team.WHITE);

		// for (int i = 48; i < 56; i++) {

		// createObject(Pieces.PAWN, i, Team.WHITE);

		// }

	}

	public Piece clickedObject(Point click) {

		for (int i = 0; i < objects.length; i++) {
			if (objects[i] == null)
				continue;

			if (objects[i].team != Manager.teamToPlay)
				continue;

			boolean checkX = click.x > objects[i].getPosition().x
					&& click.x < objects[i].getPosition().x + Panel.squareSize;
			boolean checkY = click.y > objects[i].getPosition().y
					&& click.y < objects[i].getPosition().y + Panel.squareSize;

			if (checkX && checkY)
				return objects[i];
		}
		return null;
	}

	public int indexOf(Piece p) {

		for (int i = 0; i < objects.length; i++) {
			if (objects[i] == p)
				return i;
		}

		return -1;
	}

	public Piece createObject(EPieces p, int index, Team team) {

		Point coord = ObjectUtilities.coordFromIndex(index);

		switch (p) {
			case KING:
				// objects[index] = new King(coord.x * Panel.squareSize, coord.y *
				// Panel.squareSize, team,
				// panel);
				break;
			case BISHOP:
				// objects[index] = new Bishop(coord.x * Panel.squareSize, coord.y *
				// Panel.squareSize, team,
				// panel);
				break;
			case KNIGHT:
				// objects[index] = new Knight(coord.x * Panel.squareSize, coord.y *
				// Panel.squareSize, team,
				// panel);
				break;
			case PAWN:
				// objects[index] = new Pawn(coord.x * Panel.squareSize, coord.y *
				// Panel.squareSize, team,
				// panel);
				break;
			case QUEEN:
				// objects[index] = new Queen(coord.x * Panel.squareSize, coord.y *
				// Panel.squareSize, team,
				// panel);
				break;
			case ROOK:
				// objects[index] = new Rook(coord.x * Panel.squareSize, coord.y *
				// Panel.squareSize, team,
				// panel);
				break;
		}

		return objects[index];

	}

	public Dimension getBounds() {
		return bounds;
	}

	public Manager getManager() {
		return manager;
	}
}
