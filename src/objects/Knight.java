package objects;

import java.awt.Point;

import rendering.Panel;
import util.ObjectUtilities;

public class Knight extends Piece {
	
	private static final int[] KNIGHT_MOVABLE_GUIDE_1 = {10, 17};
	private static final int[] KNIGHT_MOVABLE_GUIDE_2 = {15, 6};

	public Knight(int x, int y, Team team, Panel panel) {
		super(x, y, team, panel);
	}

	
	//KNIGHT BUGGED: LINE 39 ---- canMoveOrCapture ------ index out of bounds
	
	
	@Override
	public void defineMovableIndexes() {
		
		//KNIGHT'S MOVABLE SPACES = {10, 17, 15, 6}
		
		int index = manager.indexOf(this);
		int testIndex;
		
		Point knightCoord = ObjectUtilities.coordFromIndex(index);
		
		Point coord;
		
		for (int i = 0; i < Knight.KNIGHT_MOVABLE_GUIDE_1.length; i++) {
			
			testIndex = index + Knight.KNIGHT_MOVABLE_GUIDE_1[i];
			
			coord = ObjectUtilities.coordFromIndex(testIndex);
			
			if (!(coord.x < knightCoord.x) && canMoveOrCapture(testIndex))
				movableSpaces.add(testIndex);
					
					
			
			testIndex = index - Knight.KNIGHT_MOVABLE_GUIDE_1[i];
			
			coord = ObjectUtilities.coordFromIndex(testIndex);
			
			if (!(coord.x > knightCoord.x) && canMoveOrCapture(testIndex))
				movableSpaces.add(testIndex);
			
		}
		
		for (int i = 0; i < Knight.KNIGHT_MOVABLE_GUIDE_2.length; i++) {
			
			testIndex = index + Knight.KNIGHT_MOVABLE_GUIDE_2[i];
			
			coord = ObjectUtilities.coordFromIndex(testIndex);
			
			if (!(coord.x > knightCoord.x) && canMoveOrCapture(testIndex))
				movableSpaces.add(testIndex);
			
			testIndex = index - Knight.KNIGHT_MOVABLE_GUIDE_2[i];
			
			coord = ObjectUtilities.coordFromIndex(testIndex);
			
			if (!(coord.x < knightCoord.x) && canMoveOrCapture(testIndex))
				movableSpaces.add(testIndex);
			
		}
		
	}

}
