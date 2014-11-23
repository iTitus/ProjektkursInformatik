package projektkurs;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import projektkurs.entity.EntityPlayer;
import projektkurs.gui.Gui;
import projektkurs.gui.GuiIngame;
import projektkurs.io.InputManager;
import projektkurs.io.Option;
import projektkurs.level.Level;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.Levels;
import projektkurs.lib.Sounds;
import projektkurs.lib.Strings;
import projektkurs.render.Render;
import projektkurs.render.RenderHelper;
import projektkurs.thread.GameThread;
import projektkurs.thread.LoopThread;
import projektkurs.thread.MoveThread;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;
import projektkurs.util.MathUtil;
import projektkurs.util.ReflectionUtil;

/**
 * Die Hauptklasse.
 */
public final class Main {

  /**
   * Das aktuelle Level.
   */
  private static Level             currLevel;

  /**
   * Der GameThread.
   */
  private static GameThread        gameThread;
  /**
   * Das aktuell geöffnete GUI.
   */
  private static Gui               gui;
  /**
   * Der InputManager.
   */
  private static InputManager      imgr;
  /**
   * Das Ingame-GUI (das normale Spiel).
   */
  private static GuiIngame         ingameGui;
  /**
   * Eine Liste mit allen Methoden, die eine @Init-Annotation haben.
   */
  private static ArrayList<Method> initMethods;
  /**
   * Das Hauptfenster.
   */
  private static JFrame            mainFrame;
  /**
   * Der Bewegungs-Thread.
   */
  private static LoopThread        moveThread;
  /**
   * Der Spieler.
   */
  private static EntityPlayer      player;
  /**
   * Der Render..
   */
  private static Render            render;
  /**
   * Der RenderHelper.
   */
  private static RenderHelper      renderHelper;

  /**
   * Schließt das gerade geöffnete GUI und öffnet das Ingame-GUI.
   */
  public static void closeGui() {
    Main.gui = ingameGui;
    Main.gui.initGui();
  }

  /**
   * Verlässt das Spiel.
   */
  public static void exit() {
    System.exit(0);
  }

  /**
   * Die aktuelle FPS (frames per second).
   *
   * @return FPS
   */
  public static int getFPS() {
    return gameThread != null ? gameThread.getFPS() : 0;
  }

  /**
   * @return the gui
   */
  public static Gui getGui() {
    return gui;
  }

  /**
   * Gibt den aktuellen InputManager aus.
   *
   * @return InputManager
   */
  public static InputManager getInputManager() {
    return imgr;
  }

  /**
   * Das aktuelle Level.
   *
   * @return Level
   */
  public static Level getLevel() {
    return currLevel;
  }

  /**
   * Gibt die aktuelle Figur zurück.
   *
   * @return Figur
   */
  public static EntityPlayer getPlayer() {
    return player;
  }

  /**
   * Gibt den aktuellen Render zurück.
   *
   * @return Render
   */
  public static Render getRender() {
    return render;
  }

  /**
   * Gibt den aktuellen Renderhelper zurück.
   *
   * @return Renderhelper
   */
  public static RenderHelper getRenderHelper() {
    return renderHelper;
  }

  /**
   * Die aktuelle UPS (updates per second).
   *
   * @return UPS
   */
  public static int getUPS() {
    return gameThread != null ? gameThread.getUPS() : 0;
  }

  /**
   * Versteckt das Spielfenster.
   */
  public static void hide() {
    mainFrame.setVisible(false);
  }

  /**
   * Interne Methode um alle Felder(Variablen) zu initialisieren.
   */
  @Init(state = State.PRE)
  public static void initFields() {
    imgr = new InputManager();
    player = new EntityPlayer(MathUtil.roundDiv(Integers.sightX, 2) - 1, MathUtil.roundDiv(Integers.sightY, 2) - 1, Images.charakter);
    currLevel = Levels.level0;
    render = new Render();
    renderHelper = new RenderHelper();
    ingameGui = new GuiIngame();
    openGui(null);
  }

  /**
   * Interne Methode, um die Threads (Timer für die Ticks) zu starten.
   */
  @Init(state = State.POST)
  public static void initThreads() {
    gameThread = new GameThread();
    moveThread = new MoveThread();

    gameThread.start();
    moveThread.start();

  }

  /**
   * Einstiegspunkt in das Spiel.
   *
   * @param args
   *          Konsolenargumente
   */
  public static void main(String[] args) {

    try {
      startGame();
    } catch (Throwable t) {
      Logger.logThrowable("Unable to start the game", t);
      exit();
    }

  }

  /**
   * @param gui
   *          the gui to open
   */
  public static void openGui(Gui gui) {
    if (gui != null) {
      Main.gui = gui;
      Main.gui.initGui();
    } else {
      closeGui();
    }
  }

  /**
   * Pausiert das Spiel.
   */
  public static void pause() {
    if (moveThread != null) {
      moveThread.pause(true);
    }
    if (gameThread != null) {
      gameThread.pause(true);
    }
    Sounds.pause(true);
  }

  /**
   * Lässt das Spiel weiter laufen.
   */
  public static void resume() {
    if (moveThread != null) {
      moveThread.pause(false);
    }
    if (gameThread != null) {
      gameThread.pause(false);
    }
    Sounds.pause(false);
  }

  /**
   * Setzt das aktuelle Level.
   *
   * @param l
   *          Level
   */
  public static void setLevel(Level l) {
    if (l != null) {
      currLevel = l;
    }
  }

  /**
     *
     */
  public static void show() {
    mainFrame.setVisible(true);
  }

  /**
   * Führt alle init-Methoden aus, die im gegebenen State ausgeführt werden sollen.
   *
   * @param state
   *          der State
   */
  private static void init(State state) {

    if (initMethods == null) {
      initMethods = new ArrayList<Method>();
    }

    if (initMethods.isEmpty()) {
      initMethods.addAll(ReflectionUtil.getMethodsInClassesWithAnnotation(ReflectionUtil.getClasses(Main.class.getPackage().getName()), Init.class, Modifier.PUBLIC, Modifier.STATIC));
    }
    for (Method m : initMethods) {
      if (m.getAnnotation(Init.class).state().equals(state)) {
        Logger.info("Invoking @" + state + ": " + m.toString());
        ReflectionUtil.invokeStatic(m);
      }
    }

  }

  /**
   * Interne Methode um das Spiel zu starten.
   */
  private static void startGame() {

    Logger.info("Starting " + Strings.NAME + " " + Strings.VERSION);
    Logger.info("Initialising startup routine!");

    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      @Override
      public void run() {
        Logger.info("Initialising shutdown routine!");
        if (gameThread != null) {
          gameThread.terminate();
        }
        if (moveThread != null) {
          moveThread.terminate();
        }
        // Save to disk
        Sounds.closeAll();
        Images.flushAll();
        Logger.info("Bye bye");
        Logger.saveLog();
      }
    }, "Shutdown-Hook"));

    // Resources
    init(State.RESOURCES);

    Option.createAndShowGUI();

    while (!Option.isFinished()) {
      try {
        Thread.sleep(1);
      } catch (Throwable t) {
        Logger.logThrowable("Unable to wait for the options window", t);
      }
    }

    // PreInit
    // Load from disk
    init(State.PRE);

    // Init
    init(State.INIT);

    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        mainFrame = new JFrame(Strings.NAME + " " + Strings.VERSION);

        JPanel panel = (JPanel) mainFrame.getContentPane();
        panel.setLayout(null);
        panel.setPreferredSize(render.getGameCanvas().getPreferredSize());
        panel.add(render.getGameCanvas());

        mainFrame.setUndecorated(true);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();

        mainFrame.setVisible(true);
        render.initBuffers();
      }

    });

    // PostInit
    init(State.POST);

    Logger.info("Finished loading!");

  }

  /**
   * Nicht instanziierbar.
   */
  private Main() {
  }
}
