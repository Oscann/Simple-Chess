package objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import main.GameManager;
import positioning.Coordinates;

public class ObjectManager {

	Piece[][] objects;
	public boolean[][] blackKingCantMove;
	public boolean[][] whiteKingCantMove;
	public ArrayList<Piece> blackTeam;
	public ArrayList<Piece> whiteTeam;
	private GameManager manager;

	public ObjectManager(GameManager manager) {
		blackKingCantMove = new boolean[8][8];
		whiteKingCantMove = new boolean[8][8];
		blackTeam = new ArrayList<>();
		whiteTeam = new ArrayList<>();

		this.manager = manager;
		objects = new Piece[8][8];
	}

	public void renderObjects(Graphics g) {

		for (int i = 0; i < objects.length; i++) {
			for (int j = 0; j < objects[i].length; j++) {
				if (objects[i][j] == null)
					continue;

				objects[i][j].render(g);
			}
		}
	}

	public void update(Coordinates newPiecePosition, Coordinates previousPiecePosition, Piece p) {
		updateArray(previousPiecePosition, newPiecePosition, p);
	}

	public void updateArray(Coordinates origin, Coordinates destination, Piece p) {
		objects[destination.getX()][destination.getY()] = p;
		objects[origin.getX()][origin.getY()] = null;
	}

	public void setBoard() {

		createObject(Piece.EPieces.ROOK, 0, 0, Team.BLACK);
		createObject(Piece.EPieces.QUEEN, 0, 2, Team.BLACK);
		createObject(Piece.EPieces.BISHOP, 0, 1, Team.BLACK);
		createObject(Piece.EPieces.KNIGHT, 4, 6, Team.BLACK);
		createObject(Piece.EPieces.KNIGHT, 5, 6, Team.WHITE);
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
		switch (p) {
			case KING:
				// objects[index] = new King(x, y, team,
				// this);
				break;
			case BISHOP:
				// objects[index] = new Bishop(x, y, team,
				// this);
				// break;
			case KNIGHT:
				objects[x][y] = new Knight(x, y, team, this);
				// break;
			case PAWN:
				// objects[index] = new Pawn(x, y, team,
				// this);
				break;
			case QUEEN:
				objects[x][y] = new Queen(x, y, team, this);
				break;
			case ROOK:
				objects[x][y] = new Rook(x, y, team, this);
				break;
		}

		// Test purposes
		objects[x][y].defineMovableIndexes();

		return objects[x][y];

	}

	public Piece getPieceByCoordinate(Coordinates c) {
		int x = c.getX();
		int y = c.getY();

		return objects[x][y];
	}

	public Dimension getBounds() {
		return manager.getPanel().getDimension();
	}

	public GameManager getManager() {
		return manager;
	}
}
