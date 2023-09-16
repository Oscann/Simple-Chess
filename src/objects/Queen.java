package objects;

import rendering.Panel;
import util.Load;
import util.ObjectUtilities;

public class Queen extends Piece {

    public Queen(int x, int y, Team team, Panel panel) {
        super(x, y, team, panel);
        id = 4;
        sprite = Load.loadSprite(Piece.EPieces.QUEEN, team);

    }

    @Override
    public void defineMovableIndexes() {
        boolean NEMovement = true;
        boolean NWMovement = true;
        boolean SEMovement = true;
        boolean SWMovement = true;
        boolean NMovement = true;
        boolean SMovement = true;
        boolean WMovement = true;
        boolean EMovement = true;

        for (int i = 1; i < Panel.BOARD_SIZE; i++) {
            if (!NEMovement && !NWMovement && !SEMovement && !SWMovement && !NMovement && !SMovement && !WMovement
                    && !EMovement)
                break;

            if (NEMovement) {
                if (ObjectUtilities.coordFromIndex(getIndex() + -7 * i).getX() < ObjectUtilities
                        .coordFromIndex(getIndex()).getX())
                    NEMovement = false;
                else
                    NEMovement = MovementTesting.processStraightCandidateMovement(-7 * i, this);
            }

            if (NWMovement) {
                if (ObjectUtilities.coordFromIndex(getIndex() - 9 * i).getX() > ObjectUtilities
                        .coordFromIndex(getIndex()).getX())
                    NWMovement = false;
                else
                    NWMovement = MovementTesting.processStraightCandidateMovement(-9 * i, this);
            }

            if (SEMovement) {
                if (ObjectUtilities.coordFromIndex(getIndex() + 7 * i).getX() > ObjectUtilities
                        .coordFromIndex(getIndex()).getX())
                    SEMovement = false;
                else
                    SEMovement = MovementTesting.processStraightCandidateMovement(7 * i, this);
            }

            if (SWMovement) {
                if (ObjectUtilities.coordFromIndex(getIndex() + 9 * i).getX() < ObjectUtilities
                        .coordFromIndex(getIndex()).getX())
                    SWMovement = false;
                else
                    SWMovement = MovementTesting.processStraightCandidateMovement(9 * i, this);
            }

            if (NMovement)
                NMovement = MovementTesting.processStraightCandidateMovement(-Panel.BOARD_SIZE * i, this);

            if (SMovement)
                SMovement = MovementTesting.processStraightCandidateMovement(Panel.BOARD_SIZE * i, this);

            if (EMovement) {
                if (ObjectUtilities.coordFromIndex(getIndex() + i).getX() < ObjectUtilities
                        .coordFromIndex(getIndex()).getX())
                    EMovement = false;
                else
                    EMovement = MovementTesting.processStraightCandidateMovement(i, this);
            }

            if (WMovement)
                if (ObjectUtilities.coordFromIndex(getIndex() - i).getX() > ObjectUtilities
                        .coordFromIndex(getIndex()).getX())
                    WMovement = false;
                else
                    WMovement = MovementTesting.processStraightCandidateMovement(-i, this);
        }
    }

}
