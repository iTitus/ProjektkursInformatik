package projektkurs.thread;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import projektkurs.Main;
import projektkurs.io.InputManager;
import projektkurs.lib.Integers;
import projektkurs.util.Logger;

/**
 *
 */
public class GameThread extends Thread {

    /**
     * Delta - welcher Anteil eines Updates ist schon vergangen.
     */
    private double delta;
    /**
     * Frames pro Sekunde.
     */
    private int fps;
    /**
     * Nanosekunden pro Schleifendurchlauf.
     */
    private final double nsPerTick;
    /**
     * Pausiert der thread.
     */
    private boolean pausing;
    /**
     * Laeuft der Thread.
     */
    private boolean running;
    /**
     * Updates pro Sekunde.
     */
    private int ups;

    /**
     * Konstruktor.
     */
    public GameThread() {
        super("Game-Thread");
        nsPerTick = Integers.NS_PER_SECOND / (double) Integers.UPS;
        delta = 0D;
        fps = 0;
        ups = Integers.UPS;
    }

    /**
     * Delta dieses Threads.
     * @return Delta
     */
    public double getDelta() {
        return delta;
    }

    /**
     * Frames pro Sekunde dieses Threads.
     * @return FPS
     */
    public int getFPS() {
        return fps;
    }

    /**
     * Updates pro Sekunde dieses Threads.
     * @return UPS
     */
    public int getUPS() {
        return ups;
    }

    /**
     * Veraendert den Pausenstatus.
     * @param pause
     * true, wenn er pausieren soll; false, wenn er laufen soll
     */
    public void pause(boolean pause) {
        pausing = pause;
    }

    @Override
    public void run() {

        int updates = 0;
        int frames = 0;

        long now = 0L;
        long lastTime = System.nanoTime();
        long timer = System.nanoTime();

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            while (delta >= 1) {
                updates++;
                if (Main.getLevel() != null && Main.getLevel().canUpdate()) {
                    try {
                        Main.getLevel().update();
                    } catch (Throwable t) {
                        Logger.logThrowable("Unable to update the game", t);
                        Main.exit();
                    }
                }
                try {
                    KeyEvent kE;
                    while (Main.getInputManager().hasKeyEvents()) {
                        kE = Main.getInputManager().getNextKeyEvent();
                        Main.getGui().onKeyTyped(kE.getKeyChar(), kE);
                    }

                    MouseEvent mE;
                    while (Main.getInputManager().hasMouseEvents()) {
                        mE = Main.getInputManager().getNextMouseEvent();
                        if (mE.getButton() == InputManager.LEFT_MOUSE_BUTTON) {
                            Main.getGui().onLeftClick(mE.getX(), mE.getY(), mE);
                        }
                        if (mE.getButton() == InputManager.RIGHT_MOUSE_BUTTON) {
                            Main.getGui().onRightClick(mE.getX(), mE.getY(), mE);
                        }
                    }

                    MouseWheelEvent wE;
                    while (Main.getInputManager().hasMouseWheelEvents()) {
                        wE = Main.getInputManager().getNextMouseWheelEvent();
                        Main.getGui().onMouseWheelMoved(wE.getWheelRotation(), wE);
                    }
                } catch (Throwable t) {
                    Logger.logThrowable("Unable to update the inputs", t);
                    Main.exit();
                }
                try {
                    if (Main.getGui().canUpdate()) {
                        Main.getGui().update();
                    }
                } catch (Throwable t) {
                    Logger.logThrowable("Unable to update the gui", t);
                    Main.exit();
                }
                Main.addTick();
                delta--;
            }
            frames++;
            if (Main.getRender().canUpdate()) {
                try {
                    Main.getRender().update();
                } catch (Throwable t) {
                    Logger.logThrowable("Unable to render the game", t);
                    Main.exit();
                }
            }
            if (System.nanoTime() - timer >= Integers.NS_PER_SECOND) {
                timer += Integers.NS_PER_SECOND;
                ups = updates;
                fps = frames;
                updates = 0;
                frames = 0;
            }

            do {
                try {
                    Thread.sleep(1);
                } catch (Throwable t) {
                    Logger.logThrowable("Unable to pause the game", t);
                    Main.exit();
                }
            } while (pausing);

        }

    }

    /**
     * Startet den Thread.
     */
    @Override
    public synchronized void start() {
        super.start();
        running = true;
        pausing = false;
        Logger.info("Successfully started the Game-Thread");
    }

    /**
     * Beendet den Thread.
     */
    public void terminate() {
        running = false;
    }

}
