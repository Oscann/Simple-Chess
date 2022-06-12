package objects;

import java.awt.Point;
import java.util.ArrayList;

import objects.ObjectManager.Pieces;
import rendering.Panel;
import util.Load;
import util.ObjectUtilities;

public class Pawn extends Piece {
	
	public ArrayList<Integer> capturableSpaces;
	


	public Pawn(int x, int y, Team team, Panel panel) {
		super(x, y, team, panel);
		id = 0;
		sprite = Load.loadSprite(Pieces.PAWN, team);
	}

	protected void start(){
		capturableSpaces = new ArrayList<>();
		defineMovableIndexes();
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
	public boolean set() {
		boolean clicked = super.set();

		int index = manager.indexOf(this);

		if (team == Team.BLACK && index > 55 && index < 64){

			panel.pawnTransformation(this);

		} else if (index >= 0 && index < 8){

			panel.pawnTransformation(this);
			
		}

		return clicked;
	}



	@Override
	protected boolean canCapture(int index) {
		if (index < 0 || index > 63)
			return false;
		else if (manager.objects[index] != null)
			if (manager.objects[index].team != this.team)
				return true;
			else return false;
		else return false;
	}


	
	@Override
	public void defineMovableIndexes() {
		
		movableSpaces.clear();
		capturableSpaces.clear();

		ArrayList<Integer> canCap = new ArrayList<>();
		
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

			capturableSpaces.add(index);
			
			coord = ObjectUtilities.coordFromIndex(index);
			
			if (canCapture(index) && !(Math.abs(coord.x - pawnCoord.x) > 1))
				canCap.add(index);
			
			index = manager.indexOf(this) + 9;

			capturableSpaces.add(index);
			
			coord = ObjectUtilities.coordFromIndex(index);
			
			if (canCapture(index) && !(Math.abs(coord.x - pawnCoord.x) > 1))
				canCap.add(index);
			
		} else {
			
			for (int i = 1; i <= range; i++) {
				
				index = -i * 8 + manager.indexOf(this);
				
				if (!canMove(index))
					break;
					
				movableSpaces.add(index);
				
			}
			
			index = manager.indexOf(this) - 7;

			capturableSpaces.add(index);
			
			coord = ObjectUtilities.coordFromIndex(index);
			
			if (canCapture(index) && !(Math.abs(coord.x - pawnCoord.x) > 1))
				canCap.add(index);
			
			index = manager.indexOf(this) - 9;

			capturableSpaces.add(index);
			
			coord = ObjectUtilities.coordFromIndex(index);
			
			if (canCapture(index) && !(Math.abs(coord.x - pawnCoord.x) > 1))
				canCap.add(index);
			
		}
		
		movableSpaces.addAll(canCap);
		
	}
	
}
