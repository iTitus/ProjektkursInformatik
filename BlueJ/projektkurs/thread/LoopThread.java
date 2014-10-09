package projektkurs.thread;

/**
 * Abstrakter Loopthread
 * 
 * @author Miles
 *
 */
public abstract class LoopThread extends Thread {

	private final long loopTime;
	private boolean running, pausing;

	/**
	 * Konstruktor fuer einen LoopThread
	 * 
	 * @param name
	 *            Name des Threads
	 * @param loopTime
	 *            Ticktime in ms
	 */
	public LoopThread(String name, long loopTime) {
		super(name);
		this.loopTime = loopTime;
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
		while (running) {
			if (!pausing)
				runLoop();
			try {
				Thread.sleep(loopTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
	}

	/**
	 * Beendet den Thread
	 */
	public synchronized void terminate() {
		running = false;
	}

	/**
	 * Wird von der Schleife aufgerufen
	 */
	protected abstract void runLoop();

}
