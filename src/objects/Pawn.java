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
        Coordinates destinyCoords = originalCoords.copy().translate(pawnDir);

        boolean positionWasAdded = handleMovement(destinyCoords, false);

        if (firstMove && positionWasAdded) {
            destinyCoords.translate(pawnDir);

            handleMovement(destinyCoords, false);
        }

        destinyCoords = originalCoords.copy().translate(pawnDir).translate(Direction.EAST);
        handleMovement(destinyCoords, true);

        destinyCoords = originalCoords.copy().translate(pawnDir).translate(Direction.WEST);
        handleMovement(destinyCoords, true);
    }

    private boolean handleMovement(Coordinates destinyCoords, boolean isCapture) {

        boolean moveIsValid = isCapture ? canCapture(destinyCoords) : canMove(destinyCoords);

        if (moveIsValid) {
            this.movableSpaces.add(destinyCoords.copy());

            return true;
        }

        return false;
    }

    private boolean canMove(Coordinates destinyCoords) {
        Piece pieceInDestiny = this.objmanager.getPieceByCoordinate(destinyCoords);

        return destinyCoords.isValid() && pieceInDestiny == null;
    }

    private boolean canCapture(Coordinates destinyCoords) {
        Piece pieceInDestiny = this.objmanager.getPieceByCoordinate(destinyCoords);

        return destinyCoords.isValid() && pieceInDestiny != null && pieceInDestiny.getTeam() != this.getTeam();
    }
}
