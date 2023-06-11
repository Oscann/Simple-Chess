package util;

import java.awt.Point;

import objects.Piece;
import rendering.Panel;

public class ObjectUtilities {

	public static void correctPosition(Piece p) {

		int rightX = (int) (p.getCenterPosition().x / Panel.squareSize) * Panel.squareSize;
		int rightY = (int) (p.getCenterPosition().y / Panel.squareSize) * Panel.squareSize;

		p.setPosition(new Point(rightX, rightY));

	}

	public static Point coordFromIndex(int index) {

		int y = (int) (index / 8);
		int x = index % 8;

		return new Point(x, y);

	}

	public static int indexFromCoord(int x, int y) {

		return y * 8 + x;

	}

}
