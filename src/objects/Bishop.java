package objects;

import java.awt.Point;

import rendering.Panel;
import util.ObjectUtilities;

public class Bishop extends Piece {

	public Bishop(int x, int y, Team team, Panel panel) {
		super(x, y, team, panel);
		
	}

	@Override
	public void defineMovableIndexes() {

		Point bishopCoord = ObjectUtilities.coordFromIndex(manager.indexOf(this));
		
		Point coord;
		
		for (int diagUpLeft = manager.indexOf(this) - 9; ; diagUpLeft -= 9) {
			
			coord = ObjectUtilities.coordFromIndex(diagUpLeft);		
			
			if (coord.x > bishopCoord.x || !canMoveOrCapture(diagUpLeft))
				break;
			
			movableSpaces.add(diagUpLeft);
			
		}
		
		for (int diagUpRight = manager.indexOf(this) - 7; ; diagUpRight -= 7) {
			
			coord = ObjectUtilities.coordFromIndex(diagUpRight);
			
			if (coord.x < bishopCoord.x || !canMoveOrCapture(diagUpRight))
				break;
			
			movableSpaces.add(diagUpRight);
			
		}

		for (int diagDownLeft = manager.indexOf(this) + 7; ; diagDownLeft += 7) {

			coord = ObjectUtilities.coordFromIndex(diagDownLeft);
	
			if (coord.x > bishopCoord.x || !canMoveOrCapture(diagDownLeft))
				break;
	
			movableSpaces.add(diagDownLeft);
	
		}

		for (int diagDownRight = manager.indexOf(this) + 9; ; diagDownRight += 9) {
			
			coord = ObjectUtilities.coordFromIndex(diagDownRight);
		
			if (coord.x < bishopCoord.x || !canMoveOrCapture(diagDownRight))
				break;
			
			movableSpaces.add(diagDownRight);
		
		}
		
	}

}
