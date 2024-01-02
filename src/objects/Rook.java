package objects;

import rendering.Panel;
import util.Load;
import util.ObjectUtilities;

public class Rook extends Piece {

    public Rook(int x, int y, Team team, Panel panel) {
        super(x, y, team, panel);
        id = 2;
        sprite = Load.loadSprite(Piece.EPieces.ROOK, team);
    }

    @Override
    public void defineMovableIndexes() {
    }

}
