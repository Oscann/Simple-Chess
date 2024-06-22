package inputs;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GameManager;
import objects.ObjectManager;
import objects.Piece;
import positioning.Coordinates;
import rendering.Panel;

public class MouseInputs implements MouseListener, MouseMotionListener {

	private ObjectManager objmng;

	private Point currentClick;
	private Piece p;
	private Integer x, y;

	public MouseInputs(GameManager manager) {
		this.objmng = manager.getObjectManager();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (p == null)
			return;

		currentClick = new Point(e.getX(), e.getY());

		p.setCenter(currentClick);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

		currentClick = new Point(e.getX(), e.getY());

		Coordinates pressCoords = Coordinates.coordsFromMouseEvent(e.getX(), e.getY());

		p = objmng.getPieceByCoordinate(pressCoords);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (p == null) {
			return;
		}

		x = (int) e.getX() / Panel.SQUARE_SIZE;
		y = (int) e.getY() / Panel.SQUARE_SIZE;
		Coordinates releaseCoords = new Coordinates(x, y);

		p.handleRelease(x, y);

		if (!releaseCoords.equals(objmng.coordsOf(p)))
			p = null;
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

	public Piece getPiece() {
		return p;
	}

}
