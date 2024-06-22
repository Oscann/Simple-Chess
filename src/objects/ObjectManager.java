package objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import main.GameManager;
import positioning.Coordinates;
import rendering.Panel;

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
				// objects[index] = new King(coord.getX() * Panel.squareSize, coord.getY() *
				// Panel.squareSize, team,
				// this);
				break;
			case BISHOP:
				// objects[index] = new Bishop(coord.getX() * Panel.squareSize, coord.getY() *
				// Panel.squareSize, team,
				// this);
				// break;
			case KNIGHT:
				objects[x][y] = new Knight(coord.getX() * Panel.squareSize, coord.getY() *
						Panel.squareSize, team,
						this);
				// break;
			case PAWN:
				// objects[index] = new Pawn(coord.getX() * Panel.squareSize, coord.getY() *
				// Panel.squareSize, team,
				// this);
				break;
			case QUEEN:
				objects[x][y] = new Queen(coord.getX() * Panel.squareSize, coord.getY() *
						Panel.squareSize, team,
						this);
				break;
			case ROOK:
				objects[x][y] = new Rook(coord.getX() * Panel.squareSize, coord.getY() *
						Panel.squareSize, team,
						this);
				break;
		}

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
