package objects;

import util.Load;

public class Rook extends Piece {

    public Rook(int x, int y, Team team, ObjectManager objmanager) {
        super(x, y, team, objmanager);
        id = 2;
        sprite = Load.loadSprite(Piece.EPieces.ROOK, team);
    }

    @Override
    public void defineMovableIndexes() {
    }

}
