package inputs;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import objects.Piece;
import rendering.Panel;
import util.ObjectUtilities;

public class MouseInputs implements MouseListener, MouseMotionListener {

	Panel panel;

	private Point currentClick;
	private Piece p;
	private boolean clicked = false, dragging = false;
	private Integer x, y, index;

	public MouseInputs(Panel panel) {
		this.panel = panel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		if (p == null)
			return;

		dragging = true;

		currentClick = new Point(e.getX() - Panel.squareSize / 2, e.getY() - Panel.squareSize / 2);

		p.setPosition(currentClick);
		p.updateCenterPosition();

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		x = (int) e.getX() / Panel.squareSize;
		y = (int) e.getY() / Panel.squareSize;
		index = ObjectUtilities.indexFromCoord(x, y);

		if (p == null && panel.getObjectManager().clickedObject(new Point(e.getX(), e.getY())) == null) {

			clicked = false;
			return;

		}

		clicked = p.set();

		if (clicked && p.getMovable().contains(index)) {

			p.moveTo(x, y);
			clicked = false;

		} else if (index != panel.getObjectManager().indexOf(p)) {

			p = null;
			clicked = false;

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

		currentClick = new Point(e.getX(), e.getY());

		if (!clicked) {

			p = panel.getObjectManager().clickedObject(currentClick);

		}

		if (p == null)
			return;

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (p == null)
			return;

		if (dragging) {

			boolean moved = p.set();
			if (moved)
				moveMade();
			else
				resetMove();

		}

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

	private void moveMade() {

		resetMove();

	}

	private void resetMove() {

		p = null;
		clicked = false;
		dragging = false;

	}

	public Piece getPiece() {
		return p;
	}

	public boolean getClicked() {
		return clicked;
	}

}
