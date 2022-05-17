package objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

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
	
	public ObjectManager(Panel panel) {
		
		blackKingCantMove = new HashSet<>();
		whiteKingCantMove = new HashSet<>();
		blackTeam = new ArrayList<>();
		whiteTeam = new ArrayList<>();
		
		this.panel = panel;
		this.bounds = panel.getDimension();
		objects = new Piece[64];
		
	}
	
	public void render(Graphics g) {
		
		for (int i = 0; i < objects.length; i++) {
			
			if(objects[i] == null)
				continue;
			
			objects[i].render(g);
			
		}
		
	}
	
	public void update() {
		
		
		
	}
	
	public void setBoard() {
		
		createObject(Pieces.KNIGHT, 20, Team.BLACK);
		createObject(Pieces.ROOK, 32, Team.BLACK);
		createObject(Pieces.QUEEN, 21, Team.BLACK);
		createObject(Pieces.PAWN, 10, Team.BLACK);
		createObject(Pieces.PAWN, 59, Team.WHITE);
		
	}
	
	public Piece clickedObject(Point click) {
		
		for (int i = 0; i < objects.length; i++) {
			
			if (objects[i] == null)
				continue;
			
			boolean checkX = click.x > objects[i].getPosition().x && click.x < objects[i].getPosition().x + Panel.squareSize;
			boolean checkY = click.y > objects[i].getPosition().y && click.y < objects[i].getPosition().y + Panel.squareSize;
			
			if (checkX && checkY)
				return objects[i];
			
		}
		return null;
	}
	
	public int indexOf(Piece p) {
		
		for (int i = 0; i < objects.length; i++) {
			
			if(objects[i] == p)
				return i;
			
		}
		
		return -1;
	}
	
	public Piece createObject(Pieces p, int index, Team team) {
		
		Point coord = ObjectUtilities.coordFromIndex(index);
		
		switch(p) {
		case KING:
			
			objects[index] = new King(coord.x * Panel.squareSize, coord.y * Panel.squareSize, team, panel);
			
			break;
		case BISHOP:
			
			objects[index] = new Bishop(coord.x * Panel.squareSize, coord.y * Panel.squareSize, team, panel);
			
			break;
		case KNIGHT:
			
			objects[index] = new Knight(coord.x * Panel.squareSize, coord.y * Panel.squareSize, team, panel);
			
			break;
		case PAWN:
			
			objects[index] = new Pawn(coord.x * Panel.squareSize, coord.y * Panel.squareSize, team, panel);
			
			break;
		case QUEEN:
			
			objects[index] = new Queen(coord.x * Panel.squareSize, coord.y * Panel.squareSize, team, panel);
			
			break;
		case ROOK:
			
			objects[index] = new Rook(coord.x * Panel.squareSize, coord.y * Panel.squareSize, team, panel);
			
			break;
		default:
			break;
		
		}
		
		return objects[index];
		
	}
	
	public Dimension getBounds() {
		
		return bounds;
		
	}
	
	public enum Pieces{
		
		PAWN,
		ROOK,
		KNIGHT,
		QUEEN,
		BISHOP,
		KING;
		
	}
}
