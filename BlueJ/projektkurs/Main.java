package projektkurs;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import projektkurs.entity.Figur;
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
import projektkurs.thread.LoopThread;
import projektkurs.thread.MoveThread;
import projektkurs.thread.RenderThread;
import projektkurs.thread.SimulationThread;
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
			setVisible(true);

			render.initBuffers();

		}

	}

	public static Figur figur;
	private static InputManager imgr;
	private static final ArrayList<Method> initMethods = new ArrayList<Method>();
	private static Spielfeld map;
	private static Render render;
	private static RenderHelper renderHelper;
	private static LoopThread renderThread, simulationThread, moveThread;

	private static Storymanager storyManager;

	/**
	 * Verlaesst das Spiel
	 */
	public static void exit() {
		Logger.info("Initialising shutdown routine!");
		if (moveThread != null)
			moveThread.terminate();
		if (renderThread != null)
			renderThread.terminate();
		if (simulationThread != null)
			simulationThread.terminate();
		// TODO: Save to disk
		Sounds.closeAll();
		Images.flushAll();
		Logger.info("Bye bye");
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

	public static int getFPS() {
		return (renderThread != null ? renderThread.getLPS() : 0);
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
	public static Spielfeld getSpielfeld() {
		return map;
	}

	public static Storymanager getStoryManager() {
		return storyManager;
	}

	public static int getUPS() {
		return (simulationThread != null ? simulationThread.getLPS() : 0);
	}

	/**
	 * Interne Methode um alle Felder(Variablen) zu initialisieren
	 */
	@Init
	public static void initFields() {
		storyManager = new Storymanager();
		figur = new Figur(MathUtil.ceilDiv(Integers.SIGHT_X, 2) - 1,
				MathUtil.ceilDiv(Integers.SIGHT_Y, 2) - 1, Images.charakter);
		imgr = new InputManager();

		// Method m = ReflectionUtil.getMethod(Scripts.class,
		// "generateAndPopulateMap_Test1");
		// map = new Spielfeld(m);
		map = new Spielfeld();
		renderHelper = new RenderHelper();
		render = new Render(new GameCanvas());

	}

	/**
	 * Interne Methode, um die Threads (Timer für die Ticks) zu starten
	 */
	@Init(state = State.POST)
	public static void initThreads() {

		simulationThread = new SimulationThread();
		renderThread = new RenderThread();
		moveThread = new MoveThread();

		simulationThread.start();
		renderThread.start();
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
		if (renderThread != null)
			renderThread.pause(true);
		if (simulationThread != null)
			simulationThread.pause(true);
	}

	/**
	 * Lässt das Spiel weiter laufen
	 */
	public static void resume() {
		if (moveThread != null)
			moveThread.pause(false);
		if (renderThread != null)
			renderThread.pause(false);
		if (simulationThread != null)
			simulationThread.pause(false);
	}

	/**
	 * @param dir
	 * @return
	 * @throws Throwable
	 */
	private static ArrayList<Class<?>> getClassesInDir(File dir)
			throws Throwable {
		ArrayList<Class<?>> ret = new ArrayList<Class<?>>();
		if (dir.isFile())
			return ret;

		File[] files = dir.listFiles();

		for (File file : files != null ? files : new File[0]) {
			if (file.isFile()
					&& file.getName().toLowerCase().endsWith(".class")) {
				String path = Main.class.getPackage().getName()
						+ file.getAbsolutePath().split(
								Main.class.getPackage().getName())[1];
				path = path.replace(File.separator, ".").replace(".class", "");
				ret.add(Class.forName(path));
			} else
				ret.addAll(getClassesInDir(file));
		}

		return ret;
	}

	/**
	 * @param state
	 */
	private static void init(State state) {

		if (initMethods.isEmpty()) {
			try {
				ArrayList<Class<?>> allClasses = new ArrayList<Class<?>>();
				allClasses.addAll(getClassesInDir(new File(Main.class
						.getResource("").toURI())));

				for (Class<?> cls : allClasses) {
					for (Method m : cls.getDeclaredMethods()) {
						if (Modifier.isStatic(m.getModifiers())
								&& m.getAnnotation(Init.class) != null) {
							initMethods.add(m);
							invoke(m, state);
						}
					}
				}

			} catch (Throwable t) {
				Logger.logThrowable("Error while invoking init methods @"
						+ state, t);
				exit();
			}
		} else {
			try {
				invokeAllMethods(initMethods, state);
			} catch (Throwable t) {
				Logger.logThrowable("Error while invoking init methods @"
						+ state, t);
				exit();
			}
		}

	}

	/**
	 * @param m
	 * @param state
	 * @throws Throwable
	 */
	private static void invoke(Method m, State state) {
		if (m.getAnnotation(Init.class).state().equals(state)) {
			Logger.info("Invoking @" + state + ": " + m.toString());
			ReflectionUtil.invokeStatic(m);
		}
	}

	/**
	 * @param allMethods
	 * @param state
	 * @throws Throwable
	 */
	private static void invokeAllMethods(ArrayList<Method> allMethods,
			State state) throws Throwable {
		for (Method m : allMethods) {
			invoke(m, state);
		}
	}

	/**
	 * Interne Methode um das Spiel zu starten
	 */
	private static void startGame() {

		Logger.info("Initialising startup routine!");

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

			@SuppressWarnings("unused")
			@Override
			public void run() {
				new MainFrame();
			}

		});

		// PostInit
		init(State.POST);

		Logger.info("Finished loading!");

	}
}