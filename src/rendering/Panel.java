package rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.MouseInputs;
import main.Manager;
import objects.ObjectManager;

public class Panel extends JPanel {

	public static int squareSize;
	public static final int BOARD_SIZE = 8;
	public static final float SCALE = 1f;
	public static int panelSize;

	public Color white = new Color(220, 220, 220);
	public Color black = new Color(50, 50, 50);

	private Dimension dimension;

	private Manager manager;
	private ObjectManager objmanager;

	private MouseInputs inputs;

	public Panel() {

		squareSize = (int) (64 * SCALE);
		panelSize = (int) (BOARD_SIZE * squareSize);
		dimension = new Dimension(panelSize, panelSize);
		manager = new Manager(this);
		objmanager = manager.getObjectManager();
		inputs = new MouseInputs(this);

		this.setPreferredSize(dimension);
		this.addMouseListener(inputs);
		this.addMouseMotionListener(inputs);

		manager.startGame();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		drawBoard(g);

		if (inputs.getPiece() != null) {

			inputs.getPiece().drawMovable(g);

		}

		objmanager.render(g);

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

	public ObjectManager getObjectManager() {

		return objmanager;

	}

	public Dimension getDimension() {

		return dimension;

	}

}
