package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import positioning.Coordinates;
import positioning.Direction;
import rendering.Panel;

public abstract class Piece {

	protected boolean firstMove = true;
	public int size = Panel.squareSize;

	protected Point currentPosition;
	protected Point center;

	public EPieces type;
	protected Team team;
	protected ArrayList<Coordinates> movableSpaces;

	protected BufferedImage sprite;
	protected ObjectManager objmanager;

	public Piece(int x, int y, Team team, ObjectManager objmanager) {
		this.objmanager = objmanager;

		joinTeam(team);

		currentPosition = new Point(x, y);
		center = new Point(x + Panel.squareSize / 2, y + Panel.squareSize / 2);

		movableSpaces = new ArrayList<>();
	}

	public abstract void defineMovableIndexes();

	public void resetMovableIndexes() {
		this.movableSpaces.clear();
	}

	public void update() {
		resetMovableIndexes();
		defineMovableIndexes();
	}

	public void render(Graphics g) {
		g.drawImage(sprite, getVisualPosition().x, getVisualPosition().y, Panel.squareSize, Panel.squareSize, null);
	}

	public void moveTo(int x, int y) {
		Coordinates newPosition = new Coordinates(x, y);
		Coordinates currentPosition = objmanager.coordsOf(this);

		setVisualPosition(new Point(x * Panel.squareSize, y * Panel.squareSize));

		objmanager.update(newPosition, currentPosition, this);

		if (firstMove)
			firstMove = false;
	}

	public void handleRelease(int x, int y) {
		Coordinates coords = objmanager.coordsOf(this);
		Coordinates destiny = new Coordinates(x, y);

		if ((coords.getX() != x || coords.getY() != y) && movableSpaces.contains(destiny)) {
			moveTo(x, y);
			// Test purposes
			update();
		} else
			returnToOriginalPosition();

	}

	public void destroy() {
		if (team == Team.BLACK) {
			objmanager.blackTeam.remove(this);
		} else {
			objmanager.whiteTeam.remove(this);
		}
	}

	public void joinTeam(Team team) {
		this.team = team;

		if (team == Team.BLACK)
			objmanager.blackTeam.add(this);
		else
			objmanager.whiteTeam.add(this);

	}

	public void drawMovable(Graphics g) {

		g.setColor(Color.gray);

		for (int i = 0; i < movableSpaces.size(); i++) {
			Coordinates coord = movableSpaces.get(i);
			g
					.drawOval(
							coord.getX() * Panel.squareSize + Panel.squareSize / 4,
							coord.getY() * Panel.squareSize + Panel.squareSize / 4,
							Panel.squareSize / 2, Panel.squareSize / 2);
		}
	}

	protected void defineStraightMove(Direction dir) {
		Coordinates originalCoords = objmanager.coordsOf(this);

		Coordinates c = new Coordinates(originalCoords).translate(dir);

		for (; c.isValid(); c.translate(dir)) {
			Piece pieceInDestiny = objmanager.getPieceByCoordinate(c);

			if (pieceInDestiny == null || pieceInDestiny.team != this.team)
				this.movableSpaces.add(new Coordinates(c));

			if (pieceInDestiny != null)
				break;
		}
	}

	public void returnToOriginalPosition() {
		setVisualPosition(currentPosition);
	}

	public Point getVisualPosition() {
		return new Point((int) this.center.getX() - Panel.squareSize / 2,
				(int) this.center.getY() - Panel.squareSize / 2);
	}

	public void setVisualPosition(Point position) {
		position = (Point) position.clone();
		position.translate(Panel.squareSize / 2, Panel.squareSize / 2);
		setCenter(position);
	}

	public Point getCenterPosition() {
		return center;
	}

	public void setCenter(Point position) {
		this.center = position;
	}

	public Coordinates getCoordinates() {
		return objmanager.coordsOf(this);
	}

	public ArrayList<Coordinates> getMovable() {
		return movableSpaces;
	}

	public static enum EPieces {
		PAWN,
		ROOK,
		KNIGHT,
		QUEEN,
		BISHOP,
		KING;
	}
}