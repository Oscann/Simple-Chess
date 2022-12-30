package objects;

import objects.ObjectManager.Pieces;
import rendering.Panel;
import util.Load;

public class King extends Piece {

	public King(int x, int y, Team team, Panel panel) {
		super(x, y, team, panel);
		id = 5;
		sprite = Load.loadSprite(Pieces.KING, team);
	}

	@Override
	public void defineMovableIndexes() {

	}

}
