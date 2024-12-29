package objects;

import positioning.Coordinates;
import positioning.Direction;
import util.Load;

public class Pawn extends Piece {
    public EPieces type = EPieces.PAWN;

    public Pawn(int x, int y, Team team, ObjectManager objmanager) {
        super(x, y, team, objmanager);
        sprite = Load.loadSprite(Piece.EPieces.PAWN, team);
    }

    @Override
    public void defineMovableIndexes() {
        Direction pawnDir = team == Team.WHITE ? Direction.NORTH : Direction.SOUTH;
        Coordinates originalCoords = getCoordinates();
        Coordinates destinyCoords = originalCoords.translate(pawnDir);

        Piece pieceInDestiny = this.objmanager.getPieceByCoordinate(destinyCoords);

        if (destinyCoords.isValid() && pieceInDestiny == null)
            this.movableSpaces.add(new Coordinates(destinyCoords));

    }
}
