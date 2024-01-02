package objects;

import rendering.Panel;
import util.Load;
import util.ObjectUtilities;

public class Bishop extends Piece {

    public Bishop(int x, int y, Team team, Panel panel) {
        super(x, y, team, panel);
        id = 5;
        sprite = Load.loadSprite(Piece.EPieces.BISHOP, team);
    }

    @Override
    public void defineMovableIndexes() {
    }

}
