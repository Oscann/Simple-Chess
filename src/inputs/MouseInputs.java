package inputs;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import objects.ObjectManager;
import objects.Piece;
import rendering.Panel;
import util.ObjectUtilities;

public class MouseInputs implements MouseListener, MouseMotionListener {

	/*
	 * TO DO:
	 * - Understand this stupid code
	 * - Seperate piece selection from touching
	 * - Fix it entirely
	 */

	private Panel panel;
	private ObjectManager objmng;

	private Point currentClick;
	private Piece p;
	private boolean selected = false;
	private Integer x, y, index;

	public MouseInputs(Panel panel) {
		this.panel = panel;
		this.objmng = panel.getObjectManager();
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

		if (p == null && objmng.clickedObject(new Point(e.getX(), e.getY())) == null) {
			selected = false;
			return;
		}

		x = (int) e.getX() / Panel.squareSize;
		y = (int) e.getY() / Panel.squareSize;
		index = ObjectUtilities.indexFromCoord(x, y);

		selected = p.touch();

		if (selected && p.getMovable().contains(index)) {
			p.moveTo(x, y);
			selected = false;
		} else if (index != objmng.indexOf(p)) {
			p = null;
			selected = false;
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

		currentClick = new Point(e.getX(), e.getY());

		if (!selected)
			p = objmng.clickedObject(currentClick);

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (p == null)
			return;

		// resetMove();

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

	private void resetMove() {
		p = null;
		selected = false;
	}

	public Piece getPiece() {
		return p;
	}

	public boolean hasSelected() {
		return selected;
	}

}
