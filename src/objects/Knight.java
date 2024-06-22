package objects;

import util.Load;

public class Knight extends Piece {

    public EPieces type = EPieces.KNIGHT;

    public Knight(int x, int y, Team team, ObjectManager objmanager) {
        super(x, y, team, objmanager);
        sprite = Load.loadSprite(Piece.EPieces.KNIGHT, team);
    }

    @Override
    public void defineMovableIndexes() {
    }

}
