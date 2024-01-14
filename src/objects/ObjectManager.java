package objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import main.GameManager;
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
				// this);
				break;
			case BISHOP:
				// objects[index] = new Bishop(coord.x * Panel.squareSize, coord.y *
				// Panel.squareSize, team,
				// this);
				// break;
			case KNIGHT:
				objects[x][y] = new Knight(coord.x * Panel.squareSize, coord.y *
						Panel.squareSize, team,
						this);
				// break;
			case PAWN:
				// objects[index] = new Pawn(coord.x * Panel.squareSize, coord.y *
				// Panel.squareSize, team,
				// this);
				break;
			case QUEEN:
				objects[x][y] = new Queen(coord.x * Panel.squareSize, coord.y *
						Panel.squareSize, team,
						this);
				break;
			case ROOK:
				objects[x][y] = new Rook(coord.x * Panel.squareSize, coord.y *
						Panel.squareSize, team,
						this);
				break;
		}

		return objects[x][y];

	}

	public Dimension getBounds() {
		return manager.getPanel().getDimension();
	}

	public GameManager getManager() {
		return manager;
	}
}
