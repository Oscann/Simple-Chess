package objects;

import positioning.Coordinates;
import positioning.Direction;
import util.Load;

public class Knight extends Piece {

    public EPieces type = EPieces.KNIGHT;
    private static final Direction[] movingSchema = {
            new Direction(2, 1),
            new Direction(2, -1),
            new Direction(1, -2),
            new Direction(1, 2),
            new Direction(-1, 2),
            new Direction(-1, -2),
            new Direction(-2, 1),
            new Direction(-2, -1)
    };

    public Knight(int x, int y, Team team, ObjectManager objmanager) {
        super(x, y, team, objmanager);
        sprite = Load.loadSprite(Piece.EPieces.KNIGHT, team);
    }

    @Override
    public void defineMovableIndexes() {
        Coordinates originalCoords = this.getCoordinates();

        for (short i = 0; i < movingSchema.length; i++) {
            Coordinates candidateCoordinate = new Coordinates(originalCoords).translate(movingSchema[i]);

            if (!candidateCoordinate.isValid())
                continue;

            Piece pieceAtDestiny = objmanager.getPieceByCoordinate(candidateCoordinate);

            if (pieceAtDestiny == null || pieceAtDestiny.getTeam() != this.getTeam())
                getMovable().add(candidateCoordinate);
        }
    }

}
