package rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.MouseInputs;
import main.GameManager;

public class Panel extends JPanel {

	public static int squareSize;
	public static final int BOARD_SIZE = 8;
	public static final float SCALE = 1f;
	public static int panelSize;

	public Color white = new Color(220, 220, 220);
	public Color black = new Color(50, 50, 50);

	private Dimension dimension;

	private MouseInputs inputs;
	private GameManager manager;

	public Panel(GameManager manager) {

		squareSize = (int) (64 * SCALE);
		panelSize = (int) (BOARD_SIZE * squareSize);
		dimension = new Dimension(panelSize, panelSize);

		this.manager = manager;

		inputs = manager.getInputs();
		addMouseListener(inputs);
		addMouseMotionListener(inputs);

		this.setPreferredSize(dimension);
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		drawBoard(g);

		if (inputs.getPiece() != null) {

			inputs.getPiece().drawMovable(g);

		}

		if (manager != null)
			manager.render(g);

		repaint();
	}

	private void drawBoard(Graphics g) {

		for (int x = 0; x < BOARD_SIZE; x++) {

			if (x % 2 == 0)
				g.setColor(white);
			else
				g.setColor(black);

			for (int y = 0; y < BOARD_SIZE; y++) {

				g.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);
				alternateColor(g);

			}

		}

	}

	private void alternateColor(Graphics g) {

		if (g.getColor().equals(black))
			g.setColor(white);
		else
			g.setColor(black);

	}

	public Dimension getDimension() {

		return dimension;

	}

}
