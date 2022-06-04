package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;

import objects.ObjectManager.Pieces;
import rendering.Panel;
import util.Load;
import util.ObjectUtilities;

public class King extends Piece {
	
	private static final int[] KING_MOVEMENT = {-9, -8, -7, -1, 1, 7, 8, 9};
	
	private boolean noMovement = false;
	private boolean check = false;

	public King(int x, int y, Team team, Panel panel) {
		super(x, y, team, panel);
		id = 3;
		sprite = Load.loadSprite(Pieces.KING, team);
	}

	@Override
	public void render(Graphics g) {
		
		if (check){

			g.setColor(Color.red);
			g.fillOval(visualPosition.x, visualPosition.y, Panel.squareSize, Panel.squareSize);

		}
		g.drawImage(sprite, visualPosition.x, visualPosition.y, Panel.squareSize, Panel.squareSize, null);
		
	}

	@Override
	public void defineMovableIndexes() {
		
		movableSpaces.clear();
		
		Point kingCoord = ObjectUtilities.coordFromIndex(manager.indexOf(this));
		
		Point coord;
		
		int index;
		
		for (int i = 0; i < King.KING_MOVEMENT.length; i++) {
			
			index = manager.indexOf(this) + King.KING_MOVEMENT[i];
			
			coord = ObjectUtilities.coordFromIndex(index);
			
			if (Math.abs(coord.x - kingCoord.x) > 1 || getTeamHashSet().contains(index))
				continue;
			
			else if (canMoveOrCapture(index)) {
				
				movableSpaces.add(index);
				
			}
			
		}
		
		noMovement = movableSpaces.size() == 0;
		
	}
	
	private HashSet<Integer> getTeamHashSet(){
		
		if (this.team == Team.BLACK)
			return manager.blackKingCantMove;
		else
			return manager.whiteKingCantMove;
		
	}

	public boolean getCheck() {

		return check;

	}

	public void setCheck(boolean check) {

		this.check = check;

	}

}
