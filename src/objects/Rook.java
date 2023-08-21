package objects;

import rendering.Panel;
import util.Load;

public class Rook extends Piece {

    public Rook(int x, int y, Team team, Panel panel) {
        super(x, y, team, panel);
        id = 2;
        sprite = Load.loadSprite(Piece.EPieces.ROOK, team);
    }

    @Override
    public void defineMovableIndexes() {
        this.movableSpaces.add(1);
        this.movableSpaces.add(2);
        this.movableSpaces.add(3);
        this.movableSpaces.add(4);
    }

}
