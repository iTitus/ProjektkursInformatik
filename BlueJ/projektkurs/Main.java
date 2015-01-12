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
import projektkurs.gui.GuiMainMenu;
import projektkurs.io.InputManager;
import projektkurs.level.Level;
import projektkurs.lib.Images;
import projektkurs.lib.Sounds;
import projektkurs.lib.Strings;
import projektkurs.render.Render;
import projektkurs.render.RenderHelper;
import projektkurs.thread.GameThread;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;
import projektkurs.util.ReflectionUtil;

/**
 * Die Hauptklasse.
 */
public final class Main {

    /**
     * Der GameThread.
     */
    private static GameThread gameThread;
    /**
     * Das aktuell geöffnete GUI.
     */
    private static Gui gui;
    /**
     * Das Ingame-GUI (das normale Spiel).
     */
    private static GuiIngame ingameGui;
    /**
     * Eine Liste mit allen Methoden, die eine @Init-Annotation haben.
     */
    private static ArrayList<Method> initMethods;
    /**
     * Der InputManager.
     */
    private static InputManager inputManager;
    /**
     * Das aktuelle Level.
     */
    private static Level level;
    /**
     * Das Hauptfenster.
     */
    private static JFrame mainFrame;
    /**
     * Der Spieler.
     */
    private static EntityPlayer player;
    /**
     * Der Render..
     */
    private static Render render;
    /**
     * Der RenderHelper.
     */
    private static RenderHelper renderHelper;
    /**
     * Die Ticks.
     */
    private static int ticks;

    /**
     * Fügt einen Tick hinzu.
     */
    public static void addTick() {
        ticks++;
    }

    /**
     * Schließt das gerade geöffnete GUI und öffnet das Ingame-GUI.
     */
    public static void closeGui() {
        if (level != null && player != null) {
            Main.gui = ingameGui;
            Main.gui.initGui();
        }
    }

    /**
     * Beendet das aktuelle Level.
     */
    public static void closeLevel() {
        level.getMap().deSpawn(player);
        level = null;
        player = null;
    }

    /**
     * Verlässt das Spiel.
     */
    public static void exit() {
        System.exit(0);
    }

    /**
     * Den aktuellen Delta-Wert.
     *
     * @return Delta
     */
    public static double getDelta() {
        return gameThread != null ? gameThread.getDelta() : 0;
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
        return inputManager;
    }

    /**
     * Das aktuelle Level.
     *
     * @return Level
     */
    public static Level getLevel() {
        return level;
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
     * Die aktuellen Ticks.
     *
     * @return Ticks
     */
    public static int getTicks() {
        return ticks;
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
        inputManager = new InputManager();
        level = null;
        player = null;
        render = new Render();
        renderHelper = new RenderHelper();
        ingameGui = new GuiIngame();
        openGui(new GuiMainMenu());
    }

    /**
     * Interne Methode, um die Threads (Timer für die Ticks) zu starten.
     */
    @Init(state = State.PRE)
    public static void initThreads() {
        gameThread = new GameThread();
        gameThread.start();
    }

    /**
     * Einstiegspunkt in das Spiel.
     *
     * @param args
     *            Konsolenargumente
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
     *            the gui to open
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
        if (gameThread != null) {
            gameThread.pause(true);
        }
        Sounds.pause(true);
    }

    /**
     * Lässt das Spiel weiter laufen.
     */
    public static void resume() {
        if (gameThread != null) {
            gameThread.pause(false);
        }
        Sounds.pause(false);
    }

    /**
     *
     */
    public static void show() {
        mainFrame.setVisible(true);
    }

    /**
     * Startet das gegebene Level und resettet es dabei.
     *
     * @param Level
     */
    public static void startLevel(Level l) {
        if (l != null) {
            level = l;
            player = new EntityPlayer(level.getMap(), level.getMap().getSpawnX(), level.getMap().getSpawnY(), Images.lordvO_EW, Images.lordvO_SN, Images.lordvO_WE, Images.lordvO_NS);
            l.generateAndPopulateAll();
            level.getMap().spawn(player);
            closeGui();
        }
    }

    /**
     * Führt alle init-Methoden aus, die im gegebenen State ausgeführt werden sollen.
     *
     * @param state
     *            der State
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
                try {
                    m.invoke(null);
                } catch (Throwable t) {
                    Logger.logThrowable("Unable to start the game. Exception @ " + state, t);
                    Main.exit();
                }
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
                // Save to disk
                Sounds.closeAll();
                Images.flushAll();
                Logger.info("Bye bye");
                Logger.saveLog();
            }
        }, "Shutdown-Hook"));

        // Resources
        init(State.RESOURCES);

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

                mainFrame.setIconImage(Images.item42);
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
