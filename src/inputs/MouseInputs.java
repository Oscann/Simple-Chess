package inputs;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GameManager;
import objects.ObjectManager;
import objects.Piece;
import positioning.Coordinates;

public class MouseInputs implements MouseListener, MouseMotionListener {

	private ObjectManager objmng;
	private GameManager manager;

	private Point currentClick;
	private Piece selectedPiece;

	public MouseInputs(GameManager manager) {
		this.manager = manager;
		this.objmng = manager.getObjectManager();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (selectedPiece == null)
			return;

		currentClick = new Point(e.getX(), e.getY());

		selectedPiece.setCenter(currentClick);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouse clicked");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mouse pressed");
		Coordinates pressCoords = Coordinates.coordsFromMouseEvent(e.getX(),
				e.getY());

		Piece p = objmng.getPieceByCoordinate(pressCoords);

		boolean isNotMovingSpace = selectedPiece == null
				|| !selectedPiece.getMovableSpaces().contains(pressCoords);
		boolean isValidChange = p == null || manager.teamToPlay == p.getTeam();

		if (isNotMovingSpace && isValidChange)
			selectedPiece = p;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouse released");

		if (selectedPiece == null) {
			return;
		}

		Coordinates releasedCoords = Coordinates.coordsFromMouseEvent(e.getX(), e.getY());

		boolean moved = selectedPiece.handleRelease(releasedCoords.getX(), releasedCoords.getY());

		if (moved)
			selectedPiece = null;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	public Piece getSelectedPiece() {
		return selectedPiece;
	}

}
