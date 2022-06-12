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

	public Pieces[] pawnEvolution = {Pieces.BISHOP, Pieces.KNIGHT, Pieces.QUEEN, Pieces.ROOK};
	
	public King blackKing, whiteKing;
	
	public ObjectManager(Panel panel) {
		startVariables();
		
		this.panel = panel;
		this.bounds = panel.getDimension();
	}



	public void startVariables() {
		objects = new Piece[64];
		blackKingCantMove = new HashSet<>();
		whiteKingCantMove = new HashSet<>();
		blackTeam = new ArrayList<>();
		whiteTeam = new ArrayList<>();
		manager = new Manager(this);
	}


	
	public void render(Graphics g) {
		for (int i = 0; i < objects.length; i++) {
			if(objects[i] == null)
				continue;
			
			objects[i].render(g);
		}
	}


	
	public void update() {
		updateIndexes();
		updateKings();
		manager.checkCheck();
		System.out.println(panel.transforming);
	}


	
	private void updateKings() {
		blackKing.defineMovableIndexes();
		whiteKing.defineMovableIndexes();
	}
	


	private void updateIndexes() {
		
		whiteKingCantMove.clear();
		blackKingCantMove.clear();
		
		for (int i = 0; i < blackTeam.size(); i++) {

			blackTeam.get(i).defineMovableIndexes();
			if (blackTeam.get(i).id == 0) {

				Pawn p = (Pawn) blackTeam.get(i);
				whiteKingCantMove.addAll(p.capturableSpaces);
				
			} else if (blackTeam.get(i).id == 3){

				King p = (King) blackTeam.get(i);
				whiteKingCantMove.addAll(p.capturableSpaces);

			} else 
				whiteKingCantMove.addAll(blackTeam.get(i).movableSpaces);
		}
		
		for (int i = 0; i < whiteTeam.size(); i++) {

			whiteTeam.get(i).defineMovableIndexes();
			if (whiteTeam.get(i).id == 0) {

				Pawn p = (Pawn) whiteTeam.get(i);
				blackKingCantMove.addAll(p.capturableSpaces);

			} else if (whiteTeam.get(i).id == 3){

				King p = (King) whiteTeam.get(i);
				blackKingCantMove.addAll(p.capturableSpaces);

			} else 
				blackKingCantMove.addAll(whiteTeam.get(i).movableSpaces);
		}
	}


	
	public void setBoard() {
		setTeam(Team.BLACK);
		setTeam(Team.WHITE);
	}


	
	private void setTeam(Team team) {
		if (team == Team.BLACK) {
			createObject(Pieces.ROOK, 0, team);
			createObject(Pieces.ROOK, 7, team);
			createObject(Pieces.KNIGHT, 1, team);
			createObject(Pieces.KNIGHT, 6, team);
			createObject(Pieces.BISHOP, 2, team);
			createObject(Pieces.BISHOP, 5, team);
			blackKing = (King) createObject(Pieces.KING, 4, team);
			createObject(Pieces.QUEEN, 3, team);
			for (int i = 8; i < 16; i++) {
				createObject(Pieces.PAWN, i, team);
			}
		} else {
			createObject(Pieces.ROOK, 56, team);
			createObject(Pieces.ROOK, 63, team);
			createObject(Pieces.KNIGHT, 57, team);
			createObject(Pieces.KNIGHT, 62, team);
			createObject(Pieces.BISHOP, 58, team);
			createObject(Pieces.BISHOP, 61, team);
			whiteKing = (King) createObject(Pieces.KING, 60, team);
			createObject(Pieces.QUEEN, 59, team);
			for (int i = 48; i < 56; i++) {
				createObject(Pieces.PAWN, i, team);
			}
		}
	}


	
	public Piece clickedObject(Point click) {

		if (panel.transforming){
			if (click.y > panel.spriteMenuSize*3/2 && click.y < panel.spriteMenuSize * 5/2){
				for (int i = 0; i < 4; i++)
					if (click.x > i*panel.spriteMenuSize && click.x < (i + 1) * panel.spriteMenuSize) {
						createObject(pawnEvolution[i], panel.transIndex, panel.transTeam); 
						panel.transforming = false;}
			}
			return null;
		}

		for (int i = 0; i < objects.length; i++) {
			if (objects[i] == null)
				continue;
			if (objects[i].team != Manager.teamToPlay)
				continue;

			if (click.x > objects[i].getPosition().x && click.x < objects[i].getPosition().x + Panel.squareSize && 
				click.y > objects[i].getPosition().y && click.y < objects[i].getPosition().y + Panel.squareSize)
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
		}
		return objects[index];
	}


	
	public Dimension getBounds() {
		return bounds;
	}


	
	public Manager getManager() {
		return manager;
	}



	public Panel getPanel(){
		return panel;
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
