package objects;

import positioning.Direction;
import util.Load;

public class Queen extends Piece {

    public EPieces type = EPieces.QUEEN;

    public Queen(int x, int y, Team team, ObjectManager objmanager) {
        super(x, y, team, objmanager);
        sprite = Load.loadSprite(Piece.EPieces.QUEEN, team);
    }

    @Override
    public void defineMovableIndexes() {
        defineStraightMove(Direction.NORTH);
        defineStraightMove(Direction.SOUTH);
        defineStraightMove(Direction.WEST);
        defineStraightMove(Direction.EAST);
        defineStraightMove(Direction.NORTHEAST);
        defineStraightMove(Direction.NORTHWEST);
        defineStraightMove(Direction.SOUTHEAST);
        defineStraightMove(Direction.SOUTHWEST);
    }

}
