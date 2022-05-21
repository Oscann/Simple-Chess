package rendering;

import javax.swing.JFrame;

public class Window extends JFrame {

	public Window() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("CHESS");
		this.setResizable(false);
		this.add(new Panel());
		
		this.pack();
		this.setVisible(true);
		
	}
	
}
