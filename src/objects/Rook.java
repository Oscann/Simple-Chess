package objects;

import rendering.Panel;
import util.Load;

public class Rook extends Piece {

    public Rook(int x, int y, Team team, Panel panel) {
        super(x, y, team, panel);
        id = 2;
        sprite = Load.loadSprite(EPieces.ROOK, team);
    }

    @Override
    public void defineMovableIndexes() {
        for (int i = 0; i < 64; i++)
            if (i != 0)
                movableSpaces.add(i);
    }

}
