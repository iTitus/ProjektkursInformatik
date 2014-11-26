package projektkurs.thread;

import projektkurs.lib.Integers;
import projektkurs.util.Logger;

/**
 * Abstrakter Loopthread.
 */
public abstract class LoopThread extends Thread {

    /**
     * Delta.
     */
    private double delta;
    /**
     * Schleifendurchläufe pro Sekunde.
     */
    private int lps;
    /**
     * Nanosekunden pro Schleifendurchlauf.
     */
    private final double nsPerLoop;
    /**
     * Pausiert der Thread.
     */
    private boolean pausing;
    /**
     * Läuft der Thread.
     */
    private boolean running;

    /**
     * Konstruktor fuer einen LoopThread.
     *
     * @param name
     *            Name des Threads
     * @param lps
     *            Ticks pro Sekunde
     */
    public LoopThread(String name, int lps) {
        super(name);
        nsPerLoop = Integers.NS_PER_SECOND / (double) lps;
        delta = 0D;
        this.lps = lps;
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
     * Schleifendurläufe pro Sekunde.
     *
     * @return LPS
     */
    public int getLPS() {
        return lps;
    }

    /**
     * Verändert den Pausenstatus.
     *
     * @param b
     *            true, wenn er pausieren soll; false, wenn er laufen soll
     */
    public void pause(boolean b) {
        pausing = b;
    }

    /**
     * Wird nach dem Starten einmal ausgeführt.
     */
    @Override
    public void run() {

        int loops = 0;
        long lastTime = System.nanoTime();
        long lastTimer = System.nanoTime();

        while (running) {
            long time = System.nanoTime();
            delta += (time - lastTime) / nsPerLoop;
            lastTime = time;

            while (!pausing && delta >= 1) {
                loops++;
                runLoop();
                delta--;
            }

            if (System.nanoTime() - lastTimer >= 1000000000) {
                lastTimer += 1000000000;
                lps = loops;
                loops = 0;
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
        Logger.info("Successfully started Thread: " + getClass().getSimpleName());
    }

    /**
     * Beendet den Thread.
     */
    public void terminate() {
        running = false;
    }

    /**
     * Wird von der Schleife aufgerufen.
     */
    protected abstract void runLoop();

}
