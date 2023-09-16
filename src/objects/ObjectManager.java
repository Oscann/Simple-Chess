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

	public void update(int index, int previousIndex, Piece p) {

		updateArray(index, previousIndex, p);
		manager.alternateTeamToPlay();

		// updateIndexes();
		// updateKings();
		// manager.checkCheck();
		// manager.alternateTeamToPlay();

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

	public void updateArray(int index, int prevIndex, Piece p) {

		if (objects[index] != null)
			objects[index].destroy();

		objects[index] = p;
		objects[prevIndex] = null;

	}

	public void setBoard() {

		createObject(Piece.EPieces.ROOK, 0, Team.BLACK);
		createObject(Piece.EPieces.QUEEN, 2, Team.BLACK);
		createObject(Piece.EPieces.BISHOP, 1, Team.BLACK);
	}

	public Piece clickedObject(Point click) {

		for (int i = 0; i < objects.length; i++) {
			if (objects[i] == null)
				continue;

			boolean checkX = click.x > objects[i].getVisualPosition().x
					&& click.x < objects[i].getVisualPosition().x + Panel.squareSize;
			boolean checkY = click.y > objects[i].getVisualPosition().y
					&& click.y < objects[i].getVisualPosition().y + Panel.squareSize;

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

	public Piece createObject(Piece.EPieces p, int index, Team team) {

		Point coord = ObjectUtilities.coordFromIndex(index);

		switch (p) {
			case KING:
				// objects[index] = new King(coord.x * Panel.squareSize, coord.y *
				// Panel.squareSize, team,
				// panel);
				break;
			case BISHOP:
				objects[index] = new Bishop(coord.x * Panel.squareSize, coord.y *
						Panel.squareSize, team,
						panel);
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
				objects[index] = new Queen(coord.x * Panel.squareSize, coord.y *
						Panel.squareSize, team,
						panel);
				break;
			case ROOK:
				objects[index] = new Rook(coord.x * Panel.squareSize, coord.y *
						Panel.squareSize, team,
						panel);
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
