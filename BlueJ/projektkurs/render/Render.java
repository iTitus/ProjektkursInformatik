package projektkurs.render;

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
		gameWindow.repaint();
	}
}
