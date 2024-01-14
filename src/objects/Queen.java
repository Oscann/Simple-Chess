package objects;

import util.Load;

public class Queen extends Piece {

    public Queen(int x, int y, Team team, ObjectManager objmanager) {
        super(x, y, team, objmanager);
        id = 4;
        sprite = Load.loadSprite(Piece.EPieces.QUEEN, team);

    }

    @Override
    public void defineMovableIndexes() {
    }

}
