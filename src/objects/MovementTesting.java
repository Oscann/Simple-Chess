package objects;

import positioning.Coordinates;

public class MovementTesting {
    public static boolean processStraightCandidateMovement(int addToIndex, Piece p) {
        // int testIndex = p.getIndex() + addToIndex;

        // boolean isValid = canMoveOrCapture(testIndex, p);
        // boolean shouldContinue = isValid;

        // if (isValid) {
        // p.movableSpaces.add(testIndex);

        // shouldContinue = isValid && p.objmanager.objects[testIndex] == null;
        // }

        return true;
    }

    public static boolean canMoveOrCapture(int index, Piece p) {
        // if (index < 0 || index > 63)
        // return false;

        // if (p.manager.objects[index] == null)
        // return true;
        // else
        // return p.manager.objects[index].team != p.team;

        return true;
    }

    public static boolean properlyPositionedIndex(Coordinates piecePosition, int movementIndex) {
        return false;
    }
}
