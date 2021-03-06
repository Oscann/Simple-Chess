package objects;

import java.awt.Point;

import objects.ObjectManager.Pieces;
import rendering.Panel;
import util.Load;
import util.ObjectUtilities;

public class Bishop extends Piece {

	public Bishop(int x, int y, Team team, Panel panel) {
		super(x, y, team, panel);
		id = 2;
		sprite = Load.loadSprite(Pieces.BISHOP, team);
	}

	@Override
	public void defineMovableIndexes() {
		
		movableSpaces.clear();

		Point bishopCoord = ObjectUtilities.coordFromIndex(manager.indexOf(this));
		
		Point coord;
		
		for (int diagUpLeft = manager.indexOf(this) - 9; ; diagUpLeft -= 9) {
			
			coord = ObjectUtilities.coordFromIndex(diagUpLeft);		
			
			if (coord.x > bishopCoord.x || !canMoveOrCapture(diagUpLeft))
				break;
			
			movableSpaces.add(diagUpLeft);
			
			if (canCapture(diagUpLeft))
				break;
			
		}
		
		for (int diagUpRight = manager.indexOf(this) - 7; ; diagUpRight -= 7) {
			
			coord = ObjectUtilities.coordFromIndex(diagUpRight);
			
			if (coord.x < bishopCoord.x || !canMoveOrCapture(diagUpRight))
				break;
			
			movableSpaces.add(diagUpRight);
			
			if (canCapture(diagUpRight))
				break;
			
		}

		for (int diagDownLeft = manager.indexOf(this) + 7; ; diagDownLeft += 7) {

			coord = ObjectUtilities.coordFromIndex(diagDownLeft);
	
			if (coord.x > bishopCoord.x || !canMoveOrCapture(diagDownLeft))
				break;
	
			movableSpaces.add(diagDownLeft);
			
			if (canCapture(diagDownLeft))
				break;
	
		}

		for (int diagDownRight = manager.indexOf(this) + 9; ; diagDownRight += 9) {
			
			coord = ObjectUtilities.coordFromIndex(diagDownRight);
		
			if (coord.x < bishopCoord.x || !canMoveOrCapture(diagDownRight))
				break;
			
			movableSpaces.add(diagDownRight);
			
			if (canCapture(diagDownRight))
				break;
		
		}
		
	}

}
