package objects;

import java.awt.Point;

import objects.ObjectManager.Pieces;
import rendering.Panel;
import util.Load;
import util.ObjectUtilities;

public class Rook extends Piece {

	public Rook(int x, int y, Team team, Panel panel) {
		super(x, y, team, panel);
		id = 5;
		sprite = Load.loadSprite(Pieces.ROOK, team);
	}

	@Override
	public void defineMovableIndexes() {
		
		movableSpaces.clear();
		
		Point coord;
		Point rookCoord = ObjectUtilities.coordFromIndex(manager.indexOf(this));

		for (int left = manager.indexOf(this) - 1; ; left--) {
			
			coord = ObjectUtilities.coordFromIndex(left);
			
			if (coord.x >= rookCoord.x || !canMoveOrCapture(left))
				break;
			
			movableSpaces.add(left);
			
			if (canCapture(left))
				break;
			
		}
		
		for (int right = manager.indexOf(this) + 1; ; right++) {
			
			coord = ObjectUtilities.coordFromIndex(right);
			
			if (coord.x <= rookCoord.x || !canMoveOrCapture(right))
				break;
			
			movableSpaces.add(right);
			
			if (canCapture(right))
				break;
			
		}
		
		for (int up = manager.indexOf(this) - 8; ; up -= 8) {
			
			if (!canMoveOrCapture(up))
				break;
			
			movableSpaces.add(up);
			
			if (canCapture(up))
				break;
			
		}
		
		for (int down = manager.indexOf(this) + 8; ; down += 8) {
			
			if (!canMoveOrCapture(down))
				break;
			
			movableSpaces.add(down);
			
			if (canCapture(down))
				break;
			
		}
	}

}
