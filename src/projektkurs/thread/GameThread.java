package projektkurs.thread;

import projektkurs.Main;
import projektkurs.lib.Integers;
import projektkurs.util.Logger;

/**
 *
 */
public class GameThread extends Thread {

	private double delta;
	private final double nsPerLoop;
	private boolean running, pausing;
	private int ups, fps;

	public GameThread() {
		super("Game-Thread");
		nsPerLoop = 1000000000D / Integers.UPS;
		delta = 0D;
		fps = 0;
		ups = Integers.UPS;
	}

	/**
	 * 
	 * @return
	 */
	public double getDelta() {
		return delta;
	}

	/**
	 * 
	 * @return
	 */
	public int getFPS() {
		return fps;
	}

	/**
	 * 
	 * @return
	 */
	public int getUPS() {
		return ups;
	}

	/**
	 * 
	 * @param pause
	 */
	public void pause(boolean pause) {
		pausing = pause;
	}

	@Override
	public void run() {

		int loops = 0;
		int frames = 0;
		long lastTime = System.nanoTime();
		long lastTimer = System.nanoTime();

		while (running) {
			long time = System.nanoTime();
			delta += (time - lastTime) / nsPerLoop;
			lastTime = time;

			while (!pausing && delta >= 1) {
				loops++;
				try {
					Main.getSpielfeld().update();
				} catch (Throwable t) {
					Logger.logThrowable("Unable to update the game", t);
					Main.exit();
				}
				delta--;
			}

			if (!pausing) {
				frames++;
				try {
					Main.getRender().update();
				} catch (Throwable t) {
					Logger.logThrowable("Unable to render the game", t);
					Main.exit();
				}
			}

			if (System.nanoTime() - lastTimer >= 1000000000) {
				lastTimer += 1000000000;
				ups = loops;
				fps = frames;
				loops = 0;
				frames = 0;
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

}
