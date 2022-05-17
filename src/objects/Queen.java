package objects;

import java.awt.Point;
import java.util.ArrayList;

import rendering.Panel;
import util.ObjectUtilities;

public class Queen extends Piece {

	public Queen(int x, int y, Team team, Panel panel) {
		super(x, y, team, panel);
	}

	@Override
	public void defineMovableIndexes() {
		
		Point queenCoord = ObjectUtilities.coordFromIndex(manager.indexOf(this));
		
		Point coord;
		
		for (int diagUpLeft = manager.indexOf(this) - 9; ; diagUpLeft -= 9) {
			
			coord = ObjectUtilities.coordFromIndex(diagUpLeft);		
			
			if (coord.x > queenCoord.x || !canMoveOrCapture(diagUpLeft))
				break;
			
			movableSpaces.add(diagUpLeft);
			
		}
		
		for (int diagUpRight = manager.indexOf(this) - 7; ; diagUpRight -= 7) {
			
			coord = ObjectUtilities.coordFromIndex(diagUpRight);
			
			if (coord.x < queenCoord.x || !canMoveOrCapture(diagUpRight))
				break;
			
			movableSpaces.add(diagUpRight);
			
		}

		for (int diagDownLeft = manager.indexOf(this) + 7; ; diagDownLeft += 7) {

			coord = ObjectUtilities.coordFromIndex(diagDownLeft);
	
			if (coord.x > queenCoord.x || !canMoveOrCapture(diagDownLeft))
				break;
	
			movableSpaces.add(diagDownLeft);
	
		}

		for (int diagDownRight = manager.indexOf(this) + 9; ; diagDownRight += 9) {
			
			coord = ObjectUtilities.coordFromIndex(diagDownRight);
		
			if (coord.x < queenCoord.x || !canMoveOrCapture(diagDownRight))
				break;
			
			movableSpaces.add(diagDownRight);
		
		}
		
		for (int left = manager.indexOf(this) - 1; ; left--) {
			
			coord = ObjectUtilities.coordFromIndex(left);
			
			if (coord.x >= queenCoord.x || !canMoveOrCapture(left))
				break;
			
			movableSpaces.add(left);
			
		}
		
		for (int right = manager.indexOf(this) + 1; ; right++) {
			
			coord = ObjectUtilities.coordFromIndex(right);
			
			if (coord.x <= queenCoord.x || !canMoveOrCapture(right))
				break;
			
			movableSpaces.add(right);
			
		}
		
		for (int up = manager.indexOf(this) - 8; ; up -= 8) {
			
			if (!canMoveOrCapture(up))
				break;
			
			movableSpaces.add(up);
			
		}
		
		for (int down = manager.indexOf(this) + 8; ; down += 8) {
			
			if (!canMoveOrCapture(down))
				break;
			
			movableSpaces.add(down);
			
		}
		
	}

}
