package objects;

import rendering.Panel;
import util.Load;

public class Knight extends Piece {

    public Knight(int x, int y, Team team, Panel panel) {
        super(x, y, team, panel);
        id = 3;
        sprite = Load.loadSprite(Piece.EPieces.KNIGHT, team);
    }

    @Override
    public void defineMovableIndexes() {
        int index = getIndex();

        int NWWMovement = index - 10;

        if (MovementTesting.canMoveOrCapture(NWWMovement, this))
            movableSpaces.add(NWWMovement);

        int NNWMovement = index - 17;

        if (MovementTesting.canMoveOrCapture(NNWMovement, this))
            movableSpaces.add(NNWMovement);

        int NNEMovement = index - 15;

        if (MovementTesting.canMoveOrCapture(NNEMovement, this))
            movableSpaces.add(NNEMovement);

        int NEEMovement = index - 6;

        if (MovementTesting.canMoveOrCapture(NEEMovement, this))
            movableSpaces.add(NEEMovement);

        int SEEMovememnt = index + 17;

        if (MovementTesting.canMoveOrCapture(SEEMovememnt, this))
            movableSpaces.add(SEEMovememnt);

        int SSEMovement = index + 10;

        if (MovementTesting.canMoveOrCapture(SSEMovement, this))
            movableSpaces.add(SSEMovement);

        int SSWMovement = index + 15;

        if (MovementTesting.canMoveOrCapture(SSWMovement, this))
            movableSpaces.add(SSWMovement);

        int SWWMovement = index + 6;

        if (MovementTesting.canMoveOrCapture(SWWMovement, this))
            movableSpaces.add(SWWMovement);
    }

}
