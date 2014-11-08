package projektkurs;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import projektkurs.entity.EntityPlayer;
import projektkurs.io.InputManager;
import projektkurs.io.Option;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.Sounds;
import projektkurs.lib.Strings;
import projektkurs.render.GameCanvas;
import projektkurs.render.Render;
import projektkurs.render.RenderHelper;
import projektkurs.story.Storymanager;
import projektkurs.thread.GameThread;
import projektkurs.thread.LoopThread;
import projektkurs.thread.MoveThread;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;
import projektkurs.util.MathUtil;
import projektkurs.util.ReflectionUtil;
import projektkurs.world.Spielfeld;

/**
 * Die Hauptklasse
 */
public final class Main {

	/**
	 * Das Spielefenster
	 */
	public static class MainFrame extends JFrame {

		private static final long serialVersionUID = 1L;

		/**
		 * Hauptkonstruktor
		 */
		public MainFrame() {
			super(Strings.NAME);

			JPanel panel = (JPanel) getContentPane();
			panel.setLayout(null);
			panel.setPreferredSize(render.getGameCanvas().getPreferredSize());
			panel.add(render.getGameCanvas());

			// requestFocus();
			// setBounds(10, 10, Integers.WINDOW_X, Integers.WINDOW_Y);
			setUndecorated(true);
			setResizable(false);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			pack();

		}

	}

	public static EntityPlayer player;
	private static GameThread gameThread;
	private static InputManager imgr;
	private static final ArrayList<Method> initMethods = new ArrayList<Method>();
	private static Spielfeld map;
	private static LoopThread /* renderThread, simulationThread, */moveThread;
	private static Render render;
	private static RenderHelper renderHelper;

	private static Storymanager storyManager;

	/**
	 * Verlaesst das Spiel
	 */
	public static void exit() {
		System.exit(0);
	}

	public static int getFPS() {
		return (gameThread != null ? gameThread.getFPS() : 0);
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
	 * Gibt die aktuelle Figur aus
	 *
	 * @return Figur
	 */
	public static EntityPlayer getPlayer() {
		return player;
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
	public static Spielfeld getSpielfeld() {
		return map;
	}

	public static Storymanager getStoryManager() {
		return storyManager;
	}

	public static int getUPS() {
		return (gameThread != null ? gameThread.getUPS() : 0);
	}

	/**
	 * Interne Methode um alle Felder(Variablen) zu initialisieren
	 */
	@Init
	public static void initFields() {
		storyManager = new Storymanager();
		player = new EntityPlayer(MathUtil.ceilDiv(Integers.SIGHT_X, 2) - 1,
				MathUtil.ceilDiv(Integers.SIGHT_Y, 2) - 1, Images.charakter);
		imgr = new InputManager();
		map = new Spielfeld();
		renderHelper = new RenderHelper();
		render = new Render(new GameCanvas());

	}

	/**
	 * Interne Methode, um die Threads (Timer für die Ticks) zu starten
	 */
	@Init(state = State.POST)
	public static void initThreads() {
		gameThread = new GameThread();
		moveThread = new MoveThread();

		gameThread.start();
		moveThread.start();

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
	 * Pausiert das Spiel
	 */
	public static void pause() {
		if (moveThread != null)
			moveThread.pause(true);
		if (gameThread != null)
			gameThread.pause(true);
	}

	/**
	 * Lässt das Spiel weiter laufen
	 */
	public static void resume() {
		if (moveThread != null)
			moveThread.pause(false);
		if (gameThread != null)
			gameThread.pause(true);
	}

	/**
	 * @param state
	 */
	private static void init(State state) {

		if (initMethods.isEmpty()) {
			initMethods.addAll(ReflectionUtil
					.getAllMethodsInClassesWithAnnotation(ReflectionUtil
							.getClasses(Main.class.getPackage().getName()),
							Init.class, Modifier.PUBLIC, Modifier.STATIC));
		}
		for (Method m : initMethods) {
			if (m.getAnnotation(Init.class).state().equals(state)) {
				Logger.info("Invoking @" + state + ": " + m.toString());
				ReflectionUtil.invokeStatic(m);
			}
		}

	}

	/**
	 * Interne Methode um das Spiel zu starten
	 */
	private static void startGame() {

		Logger.info("Initialising startup routine!");

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				Logger.info("Initialising shutdown routine!");
				if (gameThread != null)
					gameThread.terminate();
				if (moveThread != null)
					moveThread.terminate();
				// TODO: Save to disk
				Sounds.closeAll();
				Images.flushAll();
				Logger.info("Bye bye");
				Logger.saveLog();
			}
		}, "Shutdown-Hook"));

		init(State.RESOURCES);
		// PreInit
		// TODO: Load from disk
		init(State.PRE);

		Option.createAndShowGUI();

		while (!Option.isFinished()) {
			try {
				Thread.sleep(100);
			} catch (Throwable t) {
				Logger.logThrowable("Could not wait for the options window", t);
			}
		}

		// Init
		init(State.INIT);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame f = new MainFrame();
				f.setVisible(true);
				render.initBuffers();
			}

		});

		// PostInit
		init(State.POST);

		Logger.info("Finished loading!");

	}
}