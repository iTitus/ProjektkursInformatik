package projektkurs.thread;

import projektkurs.util.Logger;

/**
 * Abstrakter Loopthread
 * 
 * @author Miles
 * 
 */
public abstract class LoopThread extends Thread {

	// private final long loopTime;
	private final double nsPerLoop;
	private boolean running, pausing;
	// private long time;
	private double delta;
	private long lastTime;
	private long lastTimer;
	private int loops;
	private boolean isLooping;
	private int lps;

	/**
	 * Konstruktor fuer einen LoopThread
	 * 
	 * @param name
	 *            Name des Threads
	 * @param loopTime
	 *            Ticktime in ms
	 */
	public LoopThread(String name, int lps) {
		super(name);
		// this.loopTime = lps;
		nsPerLoop = 1000000000D / lps;
		// time = 0L;
		delta = 0D;
		lastTime = 0L;
		lastTimer = 0L;
		loops = 0;
		this.lps = lps;
		isLooping = false;
	}

	/**
	 * Verändert den Pausenstatus
	 * 
	 * @param b
	 *            true, wenn er pausieren soll; false, wenn er laufen soll
	 */
	public synchronized void pause(boolean b) {
		pausing = b;
	}

	/**
	 * Wird nach dem Starten einmal ausgeführt
	 */
	@Override
	public void run() {

		lastTime = System.nanoTime();
		lastTimer = System.nanoTime();

		while (running) {
			long time = System.nanoTime();
			delta += (time - lastTime) / nsPerLoop;
			lastTime = time;

			while (!pausing && delta >= 1) {
				isLooping = true;
				loops++;
				runLoop();
				delta--;
			}
			isLooping = false;

			if (System.nanoTime() - lastTimer >= 1000000000) {
				lastTimer += 1000000000;
				lps = loops;
				loops = 0;
			}

		}
	}

	/**
	 * Startet den Thread
	 */
	@Override
	public synchronized void start() {
		super.start();
		running = true;
		pausing = false;
		Logger.info("Successfully started Thread: "
				+ getClass().getSimpleName());
	}

	/**
	 * Beendet den Thread
	 */
	public synchronized void terminate() {
		running = false;
	}

	/**
	 * Loops pro Sekunde
	 * 
	 * @return
	 */
	public int getLPS() {
		return lps;
	}

	/**
	 * isLooping
	 * 
	 * @return
	 */
	public boolean isLooping() {
		return isLooping;
	}

	/**
	 * Wird von der Schleife aufgerufen
	 */
	protected abstract void runLoop();

}
