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

        boolean nMovement = true;
        boolean sMovement = true;
        boolean wMovement = true;
        boolean eMovement = true;

        for (int i = 1; i < Panel.BOARD_SIZE; i++) {
            if (!nMovement && !sMovement && !wMovement && !eMovement)
                break;

            if (nMovement)
                nMovement = MovementTesting.processStreamCandidateMovement(-Panel.BOARD_SIZE * i, this);

            if (sMovement)
                sMovement = MovementTesting.processStreamCandidateMovement(Panel.BOARD_SIZE * i, this);

            if (eMovement) {
                if (ObjectUtilities.coordFromIndex(getIndex() + i).getX() < ObjectUtilities
                        .coordFromIndex(getIndex()).getX())
                    eMovement = false;
                else
                    eMovement = MovementTesting.processStreamCandidateMovement(i, this);
            }

            if (wMovement)
                if (ObjectUtilities.coordFromIndex(getIndex() - i).getX() > ObjectUtilities
                        .coordFromIndex(getIndex()).getX())
                    eMovement = false;
                else
                    wMovement = MovementTesting.processStreamCandidateMovement(-i, this);
        }

    }

}
