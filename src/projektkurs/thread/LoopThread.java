package projektkurs.thread;

import projektkurs.util.Logger;

/**
 * Abstrakter Loopthread
 *
 * @author Miles
 */
public abstract class LoopThread extends Thread {

	private double delta;
	private boolean isLooping;
	private int lps;
	private final double nsPerLoop;
	private boolean running, pausing;

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
		nsPerLoop = 1000000000D / lps;
		delta = 0D;
		this.lps = lps;
		isLooping = false;
	}

	/**
	 * Delta dieses Threads
	 * 
	 * @return
	 */
	public double getDelta() {
		return delta;
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
	 * Verändert den Pausenstatus
	 *
	 * @param b
	 *            true, wenn er pausieren soll; false, wenn er laufen soll
	 */
	public void pause(boolean b) {
		pausing = b;
	}

	/**
	 * Wird nach dem Starten einmal ausgeführt
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
	public void terminate() {
		running = false;
	}

	/**
	 * Wird von der Schleife aufgerufen
	 */
	protected abstract void runLoop();

}
