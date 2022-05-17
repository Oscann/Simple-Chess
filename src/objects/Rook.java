package objects;

import java.awt.Point;

import rendering.Panel;
import util.ObjectUtilities;

public class Rook extends Piece {

	public Rook(int x, int y, Team team, Panel panel) {
		super(x, y, team, panel);
	}

	@Override
	public void defineMovableIndexes() {
		
		Point coord;
		Point rookCoord = ObjectUtilities.coordFromIndex(manager.indexOf(this));

		for (int left = manager.indexOf(this) - 1; ; left--) {
			
			coord = ObjectUtilities.coordFromIndex(left);
			
			if (coord.x >= rookCoord.x || !canMoveOrCapture(left))
				break;
			
			movableSpaces.add(left);
			
		}
		
		for (int right = manager.indexOf(this) + 1; ; right++) {
			
			coord = ObjectUtilities.coordFromIndex(right);
			
			if (coord.x <= rookCoord.x || !canMoveOrCapture(right))
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
