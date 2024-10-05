package objects;

import positioning.Direction;
import util.Load;

public class Bishop extends Piece {

    public EPieces type = EPieces.BISHOP;

    public Bishop(int x, int y, Team team, ObjectManager objmanager) {
        super(x, y, team, objmanager);
        sprite = Load.loadSprite(Piece.EPieces.BISHOP, team);
    }

    @Override
    public void defineMovableIndexes() {
        defineStraightMove(Direction.NORTHEAST);
        defineStraightMove(Direction.NORTHWEST);
        defineStraightMove(Direction.SOUTHEAST);
        defineStraightMove(Direction.SOUTHWEST);
    }

}
