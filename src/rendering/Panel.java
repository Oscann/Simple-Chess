package rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

import javax.swing.JPanel;

import inputs.MouseInputs;
import objects.ObjectManager;
import objects.Piece;
import objects.Team;
import util.Load;

public class Panel extends JPanel {
	
	public static int squareSize;
	public static final int BOARD_SIZE = 8; 
	public static final float SCALE = 1f;
	public static int panelSize;

	public Team transTeam = Team.BLACK;
	public int transIndex;
	public int spriteMenuSize = panelSize/4;

	public boolean transforming = false;
	
	public Color white = new Color(200, 200, 200);
	public Color black = new Color(50, 50, 50);
	
	private Dimension dimension;
	
	private ObjectManager manager;
	
	public MouseInputs inputs;
	
	public Panel() {
		
		squareSize = (int) (64 * SCALE);
		panelSize = (int) (BOARD_SIZE * squareSize);
		
		this.dimension = new Dimension(panelSize, panelSize);
		this.setPreferredSize(dimension);
		manager = new ObjectManager(this);
		manager.setBoard();
		inputs = new MouseInputs(this);
		
		this.addMouseListener(inputs);
		this.addMouseMotionListener(inputs);
		
	}


	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		drawBoard(g);
		
		if (inputs.getClicked()) {
			
			inputs.getPiece().drawMovable(g);
			
		}
		
		manager.render(g);

		if (transforming)
			drawTransformationMenu(g);

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



	public void pawnTransformation(Piece p){

		transforming = true;
		transTeam = p.team;
		transIndex = manager.indexOf(p);

	}



	public void drawTransformationMenu(Graphics g){

		for (int i = 0; i < manager.pawnEvolution.length; i++){

			BufferedImage img = Load.loadSprite(manager.pawnEvolution[i], transTeam);

			g.drawImage(img, i * spriteMenuSize, 3*spriteMenuSize/2, spriteMenuSize, spriteMenuSize, null);
			
		}

	}


	
	public ObjectManager getManager() {
		return manager;
	}
	
	public Dimension getDimension() {
		return dimension;
	}
	
}
