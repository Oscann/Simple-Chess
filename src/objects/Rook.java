package objects;

import positioning.Direction;
import positioning.MovementHandler;
import util.Load;

public class Rook extends Piece {

    public EPieces type = EPieces.ROOK;

    public Rook(int x, int y, Team team, ObjectManager objmanager) {
        super(x, y, team, objmanager);
        sprite = Load.loadSprite(EPieces.ROOK, team);
    }

    @Override
    public void defineMovableIndexes() {
        MovementHandler.defineStraightMove(this, Direction.NORTH);
        MovementHandler.defineStraightMove(this, Direction.SOUTH);
        MovementHandler.defineStraightMove(this, Direction.WEST);
        MovementHandler.defineStraightMove(this, Direction.EAST);
    }

}
