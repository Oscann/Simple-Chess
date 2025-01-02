package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import positioning.Coordinates;
import rendering.Panel;

public abstract class Piece {

	protected boolean firstMove = true;

	protected Point center;
	protected ObjectManager objmanager;

	public EPieces type;
	protected Team team;
	protected ArrayList<Coordinates> movableSpaces;
	protected BufferedImage sprite;

	public Piece(int x, int y, Team team, ObjectManager objmanager) {
		this.objmanager = objmanager;

		joinTeam(team);

		center = new Point(x * Panel.SQUARE_SIZE + Panel.SQUARE_SIZE / 2,
				y * Panel.SQUARE_SIZE + Panel.SQUARE_SIZE / 2);

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
		g.drawImage(sprite, getVisualPosition().x, getVisualPosition().y, Panel.SQUARE_SIZE, Panel.SQUARE_SIZE, null);
	}

	public void moveTo(int x, int y) {
		Coordinates newPosition = new Coordinates(x, y);

		setVisualPosition(new Point(x * Panel.SQUARE_SIZE, y * Panel.SQUARE_SIZE));

		if (firstMove)
			firstMove = false;

		objmanager.getManager().handleMove(this, newPosition);
	}

	public boolean handleRelease(int x, int y) {
		Coordinates coords = getCoordinates();
		Coordinates destiny = new Coordinates(x, y);

		if ((coords.getX() != x || coords.getY() != y) && movableSpaces.contains(destiny)) {
			moveTo(x, y);
			return true;
		}

		returnToOriginalPosition();

		return false;
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
			g.drawOval(
					coord.getX() * Panel.SQUARE_SIZE + Panel.SQUARE_SIZE / 4,
					coord.getY() * Panel.SQUARE_SIZE + Panel.SQUARE_SIZE / 4,
					Panel.SQUARE_SIZE / 2, Panel.SQUARE_SIZE / 2);
		}
	}

	public void returnToOriginalPosition() {
		Coordinates currentCoords = getCoordinates();
		Point currentPosition = new Point(currentCoords.getX() * Panel.SQUARE_SIZE,
				currentCoords.getY() * Panel.SQUARE_SIZE);

		setVisualPosition(currentPosition);
	}

	public Point getVisualPosition() {
		return new Point((int) this.center.getX() - Panel.SQUARE_SIZE / 2,
				(int) this.center.getY() - Panel.SQUARE_SIZE / 2);
	}

	public void setVisualPosition(Point position) {
		position = (Point) position.clone();
		position.translate(Panel.SQUARE_SIZE / 2, Panel.SQUARE_SIZE / 2);
		setCenter(position);
	}

	public Point getCenterPosition() {
		return center;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setCenter(Point position) {
		this.center = position;
	}

	public Coordinates getCoordinates() {
		return objmanager.coordsOf(this);
	}

	public ArrayList<Coordinates> getMovableSpaces() {
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