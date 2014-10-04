package projektkurs;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import projektkurs.entity.Figur;
import projektkurs.io.InputManager;
import projektkurs.io.Option;
import projektkurs.lib.I18n;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.Sounds;
import projektkurs.lib.Strings;
import projektkurs.render.GameWindow;
import projektkurs.render.Render;
import projektkurs.render.RenderHelper;
import projektkurs.thread.LoopThread;
import projektkurs.thread.MoveThread;
import projektkurs.thread.RenderThread;
import projektkurs.thread.SimulationThread;
import projektkurs.world.TempMapBuilder;

/**
 * Die Hauptklasse
 * 
 */
public final class Main {

	/**
	 * Das Spielefenster
	 * 
	 */
	public static class MainFrame extends JFrame {

		private static final long serialVersionUID = 1L;

		/**
		 * Hauptkonstruktor
		 */
		public MainFrame() {
			super(Strings.NAME);

			addKeyListener(imgr);
			addMouseListener(imgr);
			addMouseMotionListener(imgr);
			addMouseWheelListener(imgr);

			add(render.getGameWindow());

			setBounds(10, 10, Integers.WINDOW_X, Integers.WINDOW_Y);
			setResizable(false);
			setUndecorated(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);

		}

	}

	public static Figur figur;
	private static InputManager imgr;

	// private static Spielfeld map;
	private static TempMapBuilder map;
	private static Render render;

	private static RenderHelper renderHelper;

	private static LoopThread renderThread, simulationThread, moveThread;

	/**
	 * Verlaesst das Spiel
	 */
	public static void exit() {
		if (moveThread != null)
			moveThread.terminate();
		if (renderThread != null)
			renderThread.terminate();
		if (simulationThread != null)
			simulationThread.terminate();
		System.exit(0);
	}

	/**
	 * Gibt die aktuelle Figur aus
	 * 
	 * @return Figur
	 */
	public static Figur getFigur() {
		return figur;
	}

	/**
	 * Gibt den aktuellen InputManager aus
	 * 
	 * @return InputManager
	 */
	public static InputManager getInputManager() {
		return imgr;
	}

	/**
	 * Gibt den aktuellen Render aus
	 * 
	 * @return Render
	 */
	public static Render getRender() {
		return render;
	}

	/**
	 * Gibt den aktuellen Renderhelper aus
	 * 
	 * @return Renderhelper
	 */
	public static RenderHelper getRenderHelper() {
		return renderHelper;
	}

	/**
	 * Gibt das aktuelle Spielfeld aus
	 * 
	 * @return Spielfeld
	 */
	// public static Spielfeld getSpielfeld() {
	public static TempMapBuilder getSpielfeld() {
		return map;
	}

	/**
	 * Einstiegspunkt in das Spiel
	 * 
	 * @param args
	 *            Konsolenargumente
	 */
	public static void main(String[] args) {

		startGame();

	}

	/**
	 * Interne Methode um alle Felder(Variablen) zu initialisieren
	 */
	private static void initFields() {

		figur = new Figur();
		imgr = new InputManager();
		// map = new Spielfeld();
		map = new TempMapBuilder();
		render = new Render(new GameWindow());
		renderHelper = new RenderHelper();

	}

	/**
	 * Interne Methode, um die Threads (Timer für die Ticks) zu starten
	 */
	private static void initThreads() {

		simulationThread = new SimulationThread();
		renderThread = new RenderThread();
		moveThread = new MoveThread();

		simulationThread.start();
		renderThread.start();
		moveThread.start();

	}

	/**
	 * Interne Methode um das Spiel zu starten
	 */
	private static void startGame() {

		Images.init();
		Sounds.init();
		I18n.init();
		// TODO: OPTIONS!
		Option.createAndShowGUI();

		while (!Option.isFinished()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.err
						.println("[ERROR] Couldn't wait for the options window!");
			}
		}

		initFields();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainFrame();
			}

		});

		initThreads();

	}

}
