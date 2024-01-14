package objects;

import util.Load;

public class Bishop extends Piece {

    public Bishop(int x, int y, Team team, ObjectManager objmanager) {
        super(x, y, team, objmanager);
        id = 5;
        sprite = Load.loadSprite(Piece.EPieces.BISHOP, team);
    }

    @Override
    public void defineMovableIndexes() {
    }

}
