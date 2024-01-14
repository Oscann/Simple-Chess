package rendering;

import javax.swing.JFrame;

import main.GameManager;

public class Window extends JFrame {

	Panel panel;

	public Window(GameManager manager) {

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("CHESS");
		this.setResizable(false);

		this.panel = new Panel(manager);

		this.add(this.panel);

		this.pack();
		this.setVisible(true);

	}

	public Panel getPanel() {
		return panel;
	}

}
