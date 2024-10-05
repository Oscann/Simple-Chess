package objects;

import positioning.Direction;
import util.Load;

public class Rook extends Piece {

    public EPieces type = EPieces.ROOK;

    public Rook(int x, int y, Team team, ObjectManager objmanager) {
        super(x, y, team, objmanager);
        sprite = Load.loadSprite(EPieces.ROOK, team);
    }

    @Override
    public void defineMovableIndexes() {
        defineStraightMove(Direction.NORTH);
        defineStraightMove(Direction.SOUTH);
        defineStraightMove(Direction.WEST);
        defineStraightMove(Direction.EAST);
    }

}
