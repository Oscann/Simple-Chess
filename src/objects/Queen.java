package objects;

import positioning.Direction;
import positioning.MovementHandler;
import util.Load;

public class Queen extends Piece {

    public EPieces type = EPieces.QUEEN;

    public Queen(int x, int y, Team team, ObjectManager objmanager) {
        super(x, y, team, objmanager);
        sprite = Load.loadSprite(Piece.EPieces.QUEEN, team);
    }

    @Override
    public void defineMovableIndexes() {
        MovementHandler.defineStraightMove(this, Direction.NORTH);
        MovementHandler.defineStraightMove(this, Direction.SOUTH);
        MovementHandler.defineStraightMove(this, Direction.WEST);
        MovementHandler.defineStraightMove(this, Direction.EAST);
        MovementHandler.defineStraightMove(this, Direction.NORTHEAST);
        MovementHandler.defineStraightMove(this, Direction.NORTHWEST);
        MovementHandler.defineStraightMove(this, Direction.SOUTHEAST);
        MovementHandler.defineStraightMove(this, Direction.SOUTHWEST);
    }

}
