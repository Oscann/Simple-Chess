package main;

import rendering.Window;

public class Main implements Runnable {

	private static Window gameWindow;
	private static long FPS = 60;
	private static long WITHIN_FRAMES = 1000000000/FPS;

	public Main() {

		gameWindow = new Window();
		Thread thread = new Thread(this);
		thread.start();

	}

	public static void main(String[] args) {
		
		new Main();

	}

	@Override
	public void run() {

		long lastFrame = System.nanoTime();
		
		while (true) {

			long now = System.nanoTime();

			if (now - lastFrame >= Main.WITHIN_FRAMES){

				gameWindow.panel.repaint();
				lastFrame = now;

			}

		}
		
	}

}
