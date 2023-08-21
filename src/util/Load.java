package util;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import objects.Piece;
import objects.Team;

public class Load {

	public static final String atlasPath = "res\\Chess.png";
	public static int spriteSize = 32;

	public static BufferedImage loadImg(String path) {

		try {

			InputStream stream = new FileInputStream(path);
			BufferedImage img = ImageIO.read(stream);
			return img;

		} catch (Exception e) {
			return null;
		}

	}

	public static BufferedImage loadSprite(Piece.EPieces p, Team team) {

		BufferedImage atlas = loadImg(atlasPath);

		int line = (team == Team.WHITE) ? 0 : 1;

		int row;

		switch (p) {
			case BISHOP:
				row = 2;
				break;
			case KING:
				row = 3;
				break;
			case KNIGHT:
				row = 5;
				break;
			case PAWN:
				row = 0;
				break;
			case QUEEN:
				row = 4;
				break;
			case ROOK:
				row = 1;
				break;
			default:
				row = 0;
		}

		return atlas.getSubimage(row * Load.spriteSize, line * Load.spriteSize, Load.spriteSize, Load.spriteSize);

	}

}
