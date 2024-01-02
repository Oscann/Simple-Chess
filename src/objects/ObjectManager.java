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

	Piece[][] objects;
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
		objects = new Piece[8][8];
	}

	public void render(Graphics g) {

		for (int i = 0; i < objects.length; i++) {
			for (int j = 0; j < objects[i].length; j++) {
				if (objects[i][j] == null)
					continue;

				objects[i][j].render(g);
			}
		}

	}

	public void update(Coordinates newPiecePosition, Coordinates previousPiecePosition, Piece p) {

		// updateArray(index, previousIndex, p);
		// manager.alternateTeamToPlay();

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

	public void updateArray(int x, int y, int px, int py, Piece p) {

		if (objects[x][y] != null)
			objects[x][y].destroy();

		objects[x][y] = p;
		objects[px][py] = null;

	}

	public void setBoard() {

		createObject(Piece.EPieces.ROOK, 0, 0, Team.BLACK);
		createObject(Piece.EPieces.QUEEN, 0, 2, Team.BLACK);
		createObject(Piece.EPieces.BISHOP, 0, 1, Team.BLACK);
		createObject(Piece.EPieces.KNIGHT, 4, 6, Team.BLACK);
	}

	public Piece clickedObject(Point click) {

		for (int i = 0; i < objects.length; i++) {
			for (int j = 0; j < objects[i].length; j++) {
				if (objects[i][j] == null)
					continue;

				boolean checkX = click.x > objects[i][j].getVisualPosition().x
						&& click.x < objects[i][j].getVisualPosition().x + Panel.squareSize;
				boolean checkY = click.y > objects[i][j].getVisualPosition().y
						&& click.y < objects[i][j].getVisualPosition().y + Panel.squareSize;

				if (checkX && checkY)
					return objects[i][j];
			}
		}
		return null;
	}

	public Coordinates coordsOf(Piece p) {
		for (int i = 0; i < objects.length; i++) {
			for (int j = 0; j < objects[i].length; j++) {
				if (p == objects[i][j])
					return new Coordinates(i, j);
			}
		}

		return null;
	}

	public Piece createObject(Piece.EPieces p, int x, int y, Team team) {

		Coordinates coord = new Coordinates(x, y);

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
				// break;
			case KNIGHT:
				objects[x][y] = new Knight(coord.x * Panel.squareSize, coord.y *
						Panel.squareSize, team,
						panel);
				// break;
			case PAWN:
				// objects[index] = new Pawn(coord.x * Panel.squareSize, coord.y *
				// Panel.squareSize, team,
				// panel);
				break;
			case QUEEN:
				objects[x][y] = new Queen(coord.x * Panel.squareSize, coord.y *
						Panel.squareSize, team,
						panel);
				break;
			case ROOK:
				objects[x][y] = new Rook(coord.x * Panel.squareSize, coord.y *
						Panel.squareSize, team,
						panel);
				break;
		}

		return objects[x][y];

	}

	public Dimension getBounds() {
		return bounds;
	}

	public Manager getManager() {
		return manager;
	}
}
