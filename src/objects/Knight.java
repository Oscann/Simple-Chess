package objects;

import util.Load;

public class Knight extends Piece {

    public Knight(int x, int y, Team team, ObjectManager objmanager) {
        super(x, y, team, objmanager);
        id = 3;
        sprite = Load.loadSprite(Piece.EPieces.KNIGHT, team);
    }

    @Override
    public void defineMovableIndexes() {
    }

}
