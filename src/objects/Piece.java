package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import rendering.Panel;
import util.ObjectUtilities;

public abstract class Piece {

	protected Point currentPosition;
	protected Point visualPosition;
	protected Point center;

	public short id;
	protected boolean firstMove = true;
	public int size = Panel.squareSize;
	protected Team team;
	protected ArrayList<Integer> movableSpaces;

	protected BufferedImage sprite;
	protected Panel panel;
	protected ObjectManager manager;

	public Piece(int x, int y, Team team, Panel panel) {

		this.panel = panel;
		this.manager = panel.getObjectManager();

		joinTeam(team);

		visualPosition = new Point(x, y);
		currentPosition = (Point) visualPosition.clone();
		center = new Point(x + Panel.squareSize / 2, y + Panel.squareSize / 2);

		ObjectUtilities.correctPosition(this);
		movableSpaces = new ArrayList<>();

		defineMovableIndexes();

	}

	public abstract void defineMovableIndexes();

	public void render(Graphics g) {
		g.drawImage(sprite, visualPosition.x, visualPosition.y, Panel.squareSize, Panel.squareSize, null);
	}

	public boolean touch() {

		int previousIndex = manager.indexOf(this);

		int _y = (int) center.y / Panel.squareSize;
		int _x = (int) center.x / Panel.squareSize;
		int index = ObjectUtilities.indexFromCoord(_x, _y);

		// Move to update
		defineMovableIndexes();

		if (movableSpaces.contains(index)) {

			ObjectUtilities.correctPosition(this);
			sit(index, previousIndex);

			if (firstMove)
				firstMove = false;
		}

		setVisualPosition(currentPosition);
		return (index - previousIndex == 0);
	}

	public void moveTo(int x, int y) {

		int index = ObjectUtilities.indexFromCoord(x, y);
		int previousIndex = manager.indexOf(this);

		setVisualPosition(new Point(x * Panel.squareSize, y * Panel.squareSize));
		sit(index, previousIndex);

		if (firstMove)
			firstMove = false;
	}

	public void sit(int index, int prevIndex) {
		currentPosition = (Point) visualPosition.clone();
		movableSpaces.clear();
		manager.update(index, prevIndex, this);
	}

	public void destroy() {
		if (team == Team.BLACK) {
			manager.blackTeam.remove(this);
		} else {
			manager.whiteTeam.remove(this);
		}
	}

	public void joinTeam(Team team) {
		this.team = team;

		if (team == Team.BLACK)
			manager.blackTeam.add(this);
		else
			manager.whiteTeam.add(this);

	}

	public boolean isOutOfBounds() {
		if (center.x > manager.getBounds().width || center.x < 0 ||
				center.y > manager.getBounds().height || center.y < 0)
			return true;
		return false;
	}

	public void drawMovable(Graphics g) {
		Point coord;

		g.setColor(Color.gray);

		for (int i = 0; i < movableSpaces.size(); i++) {
			coord = ObjectUtilities.coordFromIndex(movableSpaces.get(i));

			g.drawOval(coord.x * Panel.squareSize + Panel.squareSize / 4,
					coord.y * Panel.squareSize + Panel.squareSize / 4,
					Panel.squareSize / 2, Panel.squareSize / 2);
		}
	}

	protected boolean canMoveOrCapture(int index) {
		if (index < 0 || index > 63)
			return false;
		if (manager.objects[index] == null)
			return true;
		else
			return manager.objects[index].team != this.team;
	}

	public Point getVisualPosition() {
		return visualPosition;
	}

	public void setVisualPosition(Point position) {
		this.visualPosition = position;
		updateCenterPosition();
	}

	public Point getCenterPosition() {
		return center;
	}

	public void updateCenterPosition() {
		this.center = new Point(visualPosition.x + Panel.squareSize / 2, visualPosition.y + Panel.squareSize / 2);
	}

	public ArrayList<Integer> getMovable() {
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