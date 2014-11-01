package projektkurs.thread;

import projektkurs.Main;
import projektkurs.lib.Integers;

/**
 * Zuständig für die Renderupdates
 * 
 * @author Miles
 *
 */
public class RenderThread extends LoopThread {

	/**
	 * Konstruktor
	 */
	public RenderThread() {
		super("Render", Integers.TICK_TIME);
	}

	@Override
	protected void runLoop() {
		Main.getRender().update();
	}

}
