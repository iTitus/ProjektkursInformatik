package projektkurs.thread;

import projektkurs.Main;
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
     * Läuft der Thread.
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
     *
     * @return Delta
     */
    public double getDelta() {
        return delta;
    }

    /**
     * Frames pro Sekunde dieses Threads.
     *
     * @return FPS
     */
    public int getFPS() {
        return fps;
    }

    /**
     * Updates pro Sekunde dieses Threads.
     *
     * @return UPS
     */
    public int getUPS() {
        return ups;
    }

    /**
     * Verändert den Pausenstatus.
     *
     * @param pause
     *            true, wenn er pausieren soll; false, wenn er laufen soll
     */
    public void pause(boolean pause) {
        pausing = pause;
    }

    @Override
    public void run() {

        int loops = 0;
        int updates = 0;
        int frames = 0;

        long nextTime = System.nanoTime();
        long timer = System.nanoTime();

        while (running) {
            loops = 0;
            if (!pausing) {
                while (System.nanoTime() > nextTime && loops < Integers.MAX_FRAME_SKIP) {
                    updates++;
                    if (Main.getLevel().canUpdate()) {
                        try {
                            Main.getLevel().update();
                        } catch (Throwable t) {
                            Logger.logThrowable("Unable to render the game", t);
                            Main.exit();
                        }
                    }
                    Main.getRenderHelper().addRenderTick();
                    nextTime += nsPerTick;
                    loops++;
                }
                delta = (System.nanoTime() + nsPerTick - nextTime) / nsPerTick;

                frames++;
                if (Main.getRender().canUpdate()) {
                    try {
                        Main.getRender().update();
                    } catch (Throwable t) {
                        Logger.logThrowable("Unable to render the game", t);
                        Main.exit();
                    }
                }
            } else {
                delta = 0D;
            }

            if (System.nanoTime() - timer >= 1000000000) {
                timer += 1000000000;
                ups = updates;
                fps = frames;
                updates = 0;
                frames = 0;
            }
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
        Logger.info("Successfully started Game-Thread");
    }

    /**
     * Beendet den Thread.
     */
    public void terminate() {
        running = false;
    }

}
