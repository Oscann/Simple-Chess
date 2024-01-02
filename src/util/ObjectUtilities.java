package util;

import java.awt.Point;

import objects.Piece;
import rendering.Panel;

public class ObjectUtilities {

	public static void correctPosition(Piece p) {

		int rightX = (int) (p.getCenterPosition().x / Panel.squareSize) * Panel.squareSize;
		int rightY = (int) (p.getCenterPosition().y / Panel.squareSize) * Panel.squareSize;

		p.setVisualPosition(new Point(rightX, rightY));

	}
}
