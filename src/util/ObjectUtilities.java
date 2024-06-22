package util;

import java.awt.Point;

import objects.Piece;
import rendering.Panel;

public class ObjectUtilities {

	public static void correctPosition(Piece p) {

		int rightX = (int) (p.getCenterPosition().x / Panel.SQUARE_SIZE) * Panel.SQUARE_SIZE;
		int rightY = (int) (p.getCenterPosition().y / Panel.SQUARE_SIZE) * Panel.SQUARE_SIZE;

		p.setVisualPosition(new Point(rightX, rightY));

	}
}
