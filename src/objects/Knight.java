package objects;

import rendering.Panel;
import util.Load;

public class Knight extends Piece {

    public Knight(int x, int y, Team team, Panel panel) {
        super(x, y, team, panel);
        id = 3;
        sprite = Load.loadSprite(Piece.EPieces.KNIGHT, team);
    }

    @Override
    public void defineMovableIndexes() {
    }

}
