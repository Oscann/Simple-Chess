package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import rendering.Panel;
import util.ObjectUtilities;

public abstract class Piece {

	protected Point visualPosition;
	public short id;
	public int size = Panel.squareSize;
	protected boolean firstMove = true;

	protected BufferedImage sprite;
	protected Point currentPosition;
	protected Point center;
	protected Panel panel;
	protected ObjectManager manager;
	public Team team;
	protected ArrayList<Integer> movableSpaces;
	
	public Piece(int x, int y, Team team, Panel panel) {
		
		this.panel = panel;
		this.manager = panel.getManager();
		this.team = team;
		
		enterTeam(team);
		
		visualPosition = new Point(x, y);
		currentPosition = (Point) visualPosition.clone();
		center = new Point(x + Panel.squareSize/2, y + Panel.squareSize/2);
		
		ObjectUtilities.correctPosition(this);
		movableSpaces = new ArrayList<>();

		start();
		
	}
	
	public void render(Graphics g) {
		
		g.drawImage(sprite, visualPosition.x, visualPosition.y, Panel.squareSize, Panel.squareSize, null);
		
	}

	protected void start(){

		defineMovableIndexes();

	}


	public boolean set() {
		int previousIndex = manager.indexOf(this);
		int _y = (int) center.y/Panel.squareSize;
		int _x = (int) center.x/Panel.squareSize;
		int index = ObjectUtilities.indexFromCoord(_x, _y);
		manager.update();

		if (index - previousIndex == 0) {
			ObjectUtilities.correctPosition(this);
			return true;
		} else if (movableSpaces.contains(index)) {
			updateArray(index, previousIndex);
			manager.update();
			panel.inputs.moveMade();

			if (firstMove)
				firstMove = false;
		} else {
			setPosition(currentPosition);
		}
		return false;
	}


	
	public void moveTo(int x, int y) {
		int index = ObjectUtilities.indexFromCoord(x, y);
		int previousIndex = manager.indexOf(this);
		setPosition(new Point(x * Panel.squareSize, y * Panel.squareSize));
		updateArray(index, previousIndex);
		manager.update();
		if(firstMove)
			firstMove = false;
	}


	
	public void destroy() {
		if (team == Team.BLACK) {
			manager.blackTeam.remove(this);
		} else {
			manager.whiteTeam.remove(this);
		}
	}


	
	public void enterTeam(Team team) {
		if (team == Team.BLACK)
			manager.blackTeam.add(this);
		else
			manager.whiteTeam.add(this);
	}


	
	public abstract void defineMovableIndexes();


	
	public void updateArray(int index, int previousIndex) {
		
		if (manager.objects[index] != null)
			manager.objects[index].destroy();
		
		manager.objects[index] = this;
		manager.objects[previousIndex] = null;
			
		ObjectUtilities.correctPosition(this);
		currentPosition = (Point) visualPosition.clone();
		
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
			g.drawOval(coord.x * Panel.squareSize + Panel.squareSize/4, coord.y * Panel.squareSize + Panel.squareSize/4,
					Panel.squareSize/2, Panel.squareSize/2);
		}
	}
	


	protected boolean canMoveOrCapture(int index) {
		if (index < 0 || index > 63)
			return false;
		if (manager.objects[index] == null)
			return true;
		else if (manager.objects[index].team == this.team)
			return false;
		else
			return true;
	}


	
	protected boolean canCapture(int index) {
		if (manager.objects[index] == null)
			return false;
		else if (manager.objects[index].team != team)
			return true;
		else
			return false;
	}
	


	public Point getPosition() {
		return visualPosition;
	}


	
	public void setPosition(Point position) {
		this.visualPosition = position;
		updateCenterPosition();
	}


	
	public Point getCenterPosition() {
		return center;
	}
	


	public void updateCenterPosition() {
		this.center = new Point(visualPosition.x + Panel.squareSize/2, visualPosition.y + Panel.squareSize/2);
	}
	


	public ArrayList<Integer> getMovable() {
		
		return movableSpaces;
		
	}
}