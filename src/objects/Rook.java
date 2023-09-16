package objects;

import rendering.Panel;
import util.Load;
import util.ObjectUtilities;

public class Rook extends Piece {

    public Rook(int x, int y, Team team, Panel panel) {
        super(x, y, team, panel);
        id = 2;
        sprite = Load.loadSprite(Piece.EPieces.ROOK, team);
    }

    @Override
    public void defineMovableIndexes() {

        boolean NMovement = true;
        boolean SMovement = true;
        boolean WMovement = true;
        boolean EMovement = true;

        for (int i = 1; i < Panel.BOARD_SIZE; i++) {
            if (!NMovement && !SMovement && !WMovement && !EMovement)
                break;

            if (NMovement)
                NMovement = MovementTesting.processStreamCandidateMovement(-Panel.BOARD_SIZE * i, this);

            if (SMovement)
                SMovement = MovementTesting.processStreamCandidateMovement(Panel.BOARD_SIZE * i, this);

            if (EMovement) {
                if (ObjectUtilities.coordFromIndex(getIndex() + i).getX() < ObjectUtilities
                        .coordFromIndex(getIndex()).getX())
                    EMovement = false;
                else
                    EMovement = MovementTesting.processStreamCandidateMovement(i, this);
            }

            if (WMovement)
                if (ObjectUtilities.coordFromIndex(getIndex() - i).getX() > ObjectUtilities
                        .coordFromIndex(getIndex()).getX())
                    WMovement = false;
                else
                    WMovement = MovementTesting.processStreamCandidateMovement(-i, this);
        }

    }

}
