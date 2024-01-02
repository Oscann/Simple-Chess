package objects;

import rendering.Panel;
import util.Load;
import util.ObjectUtilities;

public class Queen extends Piece {

    public Queen(int x, int y, Team team, Panel panel) {
        super(x, y, team, panel);
        id = 4;
        sprite = Load.loadSprite(Piece.EPieces.QUEEN, team);

    }

    @Override
    public void defineMovableIndexes() {
    }

}
