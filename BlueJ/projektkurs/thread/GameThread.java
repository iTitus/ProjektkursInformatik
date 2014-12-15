package projektkurs.thread;

import projektkurs.Main;
import projektkurs.lib.Integers;
import projektkurs.util.Logger;

/**
 *
 */
public class GameThread extends Thread {

    /**
     * Delta.
     */
    private double delta;
    /**
     * Frames pro Sekunde.
     */
    private int fps;
    /**
     * Nanosekunden pro Schleifendurchlauf.
     */
    private final double nsPerLoop;
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
        nsPerLoop = 1000000000D / Integers.UPS;
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

        int updates = 0;
        int frames = 0;
        long lastTime = System.nanoTime();
        long lastTimer = System.nanoTime();
        boolean shouldRender = false;

        while (running) {
            long time = System.nanoTime();
            delta += (time - lastTime) / nsPerLoop;
            lastTime = time;

            while (!pausing && delta >= 1) {
                updates++;
                try {
                    if (Main.getLevel().canUpdate()) {
                        Main.getLevel().update();
                    }
                    Main.getRenderHelper().addRenderTick();
                } catch (Throwable t) {
                    Logger.logThrowable("Unable to update the game", t);
                    Main.exit();
                }
                shouldRender = true;
                delta--;
            }

            if (shouldRender) {
                frames++;
                if (Main.getRender().canUpdate()) {
                    try {
                        Main.getRender().update();
                    } catch (Throwable t) {
                        Logger.logThrowable("Unable to render the game", t);
                        Main.exit();
                    }
                    shouldRender = false;
                }
            } else {
                try {
                    Thread.sleep(1);
                } catch (Throwable t) {
                    Logger.logThrowable("Unable to pause the thread", t);
                    Main.exit();
                }
            }

            if (System.nanoTime() - lastTimer >= 1000000000) {
                lastTimer += 1000000000;
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
