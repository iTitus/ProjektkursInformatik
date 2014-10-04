package projektkurs.render;

import projektkurs.Main;

/**
 * Renderklasse
 * 
 */
public class Render {

	/**
	 * Das Spiel-JPanel
	 */
	private final GameWindow gameWindow;

	/**
	 * Konstruktor
	 * 
	 * @param gameWindow
	 *            Das Spiel-JPanel
	 */
	public Render(GameWindow gameWindow) {
		this.gameWindow = gameWindow;

	}

	/**
	 * Gibt das aktuelle Spiel-JPanel
	 * 
	 * @return GameWindow
	 */
	public GameWindow getGameWindow() {
		return gameWindow;
	}

	/**
	 * Updated den Bildschirm
	 */
	public void update() {
		if (Main.getRenderHelper().shouldUpdate()) {
			gameWindow.repaint();
			Main.getRenderHelper().setShouldUpdateNextTick(false);
		}
	}
}
