package projektkurs.thread;

/**
 * Abstrakter Loopthread
 * 
 * @author Miles
 *
 */
public abstract class LoopThread extends Thread {

	private final long loopTime;
	private boolean running;

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
	 * Wird nach dem Starten einmal ausgefuehrt
	 */
	@Override
	public void run() {
		while (running) {
			runLoop();
			try {
				Thread.sleep(loopTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Starten den Thread
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
