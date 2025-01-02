package positioning;

import main.Game;
import objects.Piece;

public class MovementHandler {
    public static void defineStraightMove(Piece p, Direction dir) {
        Coordinates originalCoords = p.getCoordinates();

        for (Coordinates c = new Coordinates(originalCoords).translate(dir); c.isValid(); c.translate(dir)) {
            Piece pieceInDestiny = Game.getObjManager().getPieceByCoordinate(c);

            if (pieceInDestiny == null || pieceInDestiny.getTeam() != p.getTeam())
                p.getMovableSpaces().add(new Coordinates(c));

            if (pieceInDestiny != null)
                break;
        }
    }
}
