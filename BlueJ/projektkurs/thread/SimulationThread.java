package projektkurs.thread;

import projektkurs.Main;
import projektkurs.lib.Integers;

/**
 * Zustaendig fuer die Spielfeldupdates
 *
 * @author Vella
 */
public class SimulationThread extends LoopThread {

	/**
	 * Konstruktor
	 */
	public SimulationThread() {
		super("Simulation", Integers.UPS);
	}

	@Override
	protected void runLoop() {
		Main.getSpielfeld().update();
	}

}
