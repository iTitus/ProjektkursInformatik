package projektkurs;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import projektkurs.entity.Figur;
import projektkurs.io.InputManager;
import projektkurs.io.Option;
import projektkurs.lib.Images;
import projektkurs.lib.Init;
import projektkurs.lib.Init.State;
import projektkurs.lib.Integers;
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
@SuppressWarnings("all")
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

	private static ArrayList<Method> initMethods;

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
	public static TempMapBuilder getSpielfeld() {
		return map;
	}

	/**
	 * Interne Methode um alle Felder(Variablen) zu initialisieren
	 */
	@Init
	public static void initFields() {
		figur = new Figur(16, 16, Images.charakter);
		imgr = new InputManager();
		// map = new Spielfeld();
		map = new TempMapBuilder();
		render = new Render(new GameWindow());
		renderHelper = new RenderHelper();
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

	private static ArrayList<Class<?>> getClassesInDir(File dir)
			throws Throwable {
		ArrayList<Class<?>> ret = new ArrayList<Class<?>>();
		if (dir.isFile())
			return ret;
		else {
			File[] files = dir.listFiles();

			for (File file : files) {
				if (file.isFile()
						&& file.getName().toLowerCase().endsWith(".class")) {
					String path = Main.class.getPackage().getName()
							+ file.getAbsolutePath().split(
									Main.class.getPackage().getName())[1];
					path = path.replace(File.separator, ".").replace(".class",
							"");
					ret.add(Class.forName(path));
				} else
					ret.addAll(getClassesInDir(file));
			}
		}
		return ret;
	}

	private static void init(State state) {

		if (initMethods == null) {
			try {
				ArrayList<Class<?>> allClasses = new ArrayList<Class<?>>();
				allClasses.addAll(getClassesInDir(new File(Main.class
						.getResource("").toURI())));

				for (Class<?> cls : allClasses) {
					for (Method m : cls.getDeclaredMethods()) {
						if (Modifier.isStatic(m.getModifiers())
								&& m.getAnnotation(Init.class) != null) {
							if (initMethods == null)
								initMethods = new ArrayList<Method>();
							initMethods.add(m);
							if (m.getAnnotation(Init.class).state()
									.equals(state)) {
								boolean accessible = m.isAccessible();
								m.setAccessible(true);
								System.out.println("Invoking @" + state + ": "
										+ m.toString());
								m.invoke(null);
								m.setAccessible(accessible);
							}
						}
					}
				}

			} catch (Throwable t) {
				t.printStackTrace();
			}
		} else {
			try {
				for (Method m : initMethods) {
					if (m.getAnnotation(Init.class).state().equals(state)) {
						boolean accessible = m.isAccessible();
						m.setAccessible(true);
						System.out.println("Invoking @" + state + ": "
								+ m.toString());
						m.invoke(null);
						m.setAccessible(accessible);
					}

				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}

	}

	/**
	 * Interne Methode um das Spiel zu starten
	 */
	private static void startGame() {

		// PreInit
		init(State.PRE);

		Option.createAndShowGUI();

		while (!Option.isFinished()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.err
						.println("[ERROR] Couldn't wait for the options window!");
			}
		}

		// Init
		init(State.INIT);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainFrame();
			}

		});

		// PostInit
		init(State.POST);

	}
}