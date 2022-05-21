package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import objects.ObjectManager.Pieces;
import rendering.Panel;
import util.Load;
import util.ObjectUtilities;

public class Pawn extends Piece {
	
	private boolean firstMove = true;
	public ArrayList<Integer> capturableSpaces;
	
	public Pawn(int x, int y, Team team, Panel panel) {
		super(x, y, team, panel);
		capturableSpaces = new ArrayList<>();
		id = 0;
		sprite = Load.loadSprite(Pieces.PAWN, team);
	}
	
	@Override
	public boolean set() {

		int previousIndex = manager.indexOf(this);
		
		int _y = (int) center.y/Panel.squareSize;
		int _x = (int) center.x/Panel.squareSize;
		int index = ObjectUtilities.indexFromCoord(_x, _y);
		
		defineMovableIndexes();
		
		if (index - previousIndex == 0) {
			
			ObjectUtilities.correctPosition(this);
			return true;
			
		}
		
		else if (movableSpaces.contains(index)) {
			
			updateArray(index, previousIndex);
			movableSpaces.clear();
			
			manager.getManager().alternateTeamToPlay();
			
			if (firstMove)
				firstMove = false;
			
		} else {
			
			setPosition(currentPosition);
			
		}
		
		return false;
		
	}
	
	@Override
	public void moveTo(int x, int y) {
		
		int index = ObjectUtilities.indexFromCoord(x, y);
		int previousIndex = manager.indexOf(this);
		
		setPosition(new Point(x * Panel.squareSize, y * Panel.squareSize));
		updateArray(index, previousIndex);
		movableSpaces.clear();
		
		if(firstMove)
			firstMove = false;
		
	}
	
	private boolean canMove(int index) {
		
		if (index < 0 || index > 63)
			return false;
		else if (manager.objects[index] == null)
			return true;
		else
			return false;
		
	}
	
	@Override
	protected boolean canCapture(int index) {
		
		if (index < 0 || index > 63)
			return false;
		else if (manager.objects[index] != null)
			if (manager.objects[index].team != this.team)
				return true;
			else
				return false;
		else
			return false;
		
	}

	@Override
	public void defineMovableIndexes() {
		
		movableSpaces.clear();
		capturableSpaces.clear();
		
		int range = (firstMove) ? 2 : 1;
		int index;
		
		Point pawnCoord = ObjectUtilities.coordFromIndex(manager.indexOf(this));
		
		Point coord;
		
		if (team == Team.BLACK) {
			
			for (int i = 1; i <= range; i++) {
				
				index = i * 8 + manager.indexOf(this);
				
				if (!canMove(index))
					break;
					
				movableSpaces.add(index);
				
			}
			
			index = manager.indexOf(this) + 7;
			
			coord = ObjectUtilities.coordFromIndex(index);
			
			if (canCapture(index) && !(Math.abs(coord.x - pawnCoord.x) > 1))
				capturableSpaces.add(index);
			
			index = manager.indexOf(this) + 9;
			
			coord = ObjectUtilities.coordFromIndex(index);
			
			if (canCapture(index) && !(Math.abs(coord.x - pawnCoord.x) > 1))
				capturableSpaces.add(index);
			
		} else {
			
			for (int i = 1; i <= range; i++) {
				
				index = -i * 8 + manager.indexOf(this);
				
				if (!canMove(index))
					break;
					
				movableSpaces.add(index);
				
			}
			
			index = manager.indexOf(this) - 7;
			
			coord = ObjectUtilities.coordFromIndex(index);
			
			if (canCapture(index) && !(Math.abs(coord.x - pawnCoord.x) > 1))
				capturableSpaces.add(index);
			
			index = manager.indexOf(this) - 9;
			
			coord = ObjectUtilities.coordFromIndex(index);
			
			if (canCapture(index) && !(Math.abs(coord.x - pawnCoord.x) > 1))
				capturableSpaces.add(index);
			
		}
		
		movableSpaces.addAll(capturableSpaces);
		
	}
	
}
