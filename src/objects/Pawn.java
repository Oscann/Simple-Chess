package objects;

import java.awt.Point;

import objects.ObjectManager.Pieces;
import rendering.Panel;
import util.Load;
import util.ObjectUtilities;

public class Pawn extends Piece {

	private boolean firstMove = true;

	public Pawn(int x, int y, Team team, Panel panel) {
		super(x, y, team, panel);
		id = 0;
		sprite = Load.loadSprite(Pieces.PAWN, team);
	}

	@Override
	public boolean set() {

		int previousIndex = manager.indexOf(this);

		int _y = (int) center.y / Panel.squareSize;
		int _x = (int) center.x / Panel.squareSize;
		int index = ObjectUtilities.indexFromCoord(_x, _y);

		defineMovableIndexes();

		if (index - previousIndex == 0) {
			ObjectUtilities.correctPosition(this);
			return true;
		}

		else if (movableSpaces.contains(index)) {
			updateArray(index, previousIndex);
			manager.getManager().alternateTeamToPlay();
			movableSpaces.clear();

			if (firstMove)
				firstMove = false;
		} else {
			setPosition(currentPosition);
		}

		return false;

	}

	@Override
	public void moveTo(int x, int y) {

		int index = ObjectUtilities.indexFromCoord(x, y);
		int previousIndex = manager.indexOf(this);

		setPosition(new Point(x * Panel.squareSize, y * Panel.squareSize));
		updateArray(index, previousIndex);
		movableSpaces.clear();

		if (firstMove)
			firstMove = false;

	}

	public boolean canMove(int index) {
		if (index < 0 || index > 63)
			return false;

		return manager.objects[index] == null;
	}

	public boolean canCapture(int index) {
		if (index < 0 || index > 63)
			return false;
		if (manager.objects[index] == null)
			return false;

		return manager.objects[index].team != this.team;
	}

	@Override
	public void defineMovableIndexes() {

		int range = (firstMove) ? 2 : 1;
		int index;

		if (team == Team.BLACK) {

			for (int i = 1; i <= range; i++) {

				index = i * 8 + manager.indexOf(this);

				if (!canMove(index))
					break;

				movableSpaces.add(index);

			}

			index = manager.indexOf(this) + 7;

			if (canCapture(index))
				movableSpaces.add(index);

			index = manager.indexOf(this) + 9;

			if (canCapture(index))
				movableSpaces.add(index);

		} else {

			for (int i = 1; i <= range; i++) {

				index = -i * 8 + manager.indexOf(this);

				if (index < 0)
					break;

				if (manager.objects[index] != null)
					break;

				movableSpaces.add(index);

			}

			index = manager.indexOf(this) - 7;

			if (canCapture(index))
				movableSpaces.add(index);

			index = manager.indexOf(this) - 9;

			if (canCapture(index))
				movableSpaces.add(index);

		}

	}

}
