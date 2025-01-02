package objects;

import positioning.Direction;
import positioning.MovementHandler;
import util.Load;

public class Bishop extends Piece {

    public EPieces type = EPieces.BISHOP;

    public Bishop(int x, int y, Team team, ObjectManager objmanager) {
        super(x, y, team, objmanager);
        sprite = Load.loadSprite(Piece.EPieces.BISHOP, team);
    }

    @Override
    public void defineMovableIndexes() {
        MovementHandler.defineStraightMove(this, Direction.NORTHEAST);
        MovementHandler.defineStraightMove(this, Direction.NORTHWEST);
        MovementHandler.defineStraightMove(this, Direction.SOUTHEAST);
        MovementHandler.defineStraightMove(this, Direction.SOUTHWEST);
    }

}
