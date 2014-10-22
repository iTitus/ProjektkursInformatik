package projektkurs.lib;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logger
 *
 */
public final class ProjektLogger {

	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(Strings.NAME);

	static {
		log.setLevel(Level.ALL);
	}

	/**
	 * 
	 * @param msg
	 */
	public static void config(String msg) {
		log(Level.CONFIG, msg);
	}

	/**
	 * 
	 * @param msg
	 */
	public static void fine(String msg) {
		log(Level.FINE, msg);
	}

	/**
	 * 
	 * @param msg
	 */
	public static void finer(String msg) {
		log(Level.FINER, msg);
	}

	/**
	 * 
	 * @param msg
	 */
	public static void finest(String msg) {
		log(Level.FINEST, msg);
	}

	/**
	 * 
	 * @param msg
	 */
	public static void info(String msg) {
		log(Level.INFO, msg);
	}

	/**
	 * 
	 * @param level
	 * @param msg
	 */
	public static void log(Level level, String msg) {
		log.log(level, msg);
	}

	/**
	 * 
	 * @param level
	 * @param msg
	 * @param o
	 */
	public static void logObjects(Level level, String msg, Object... o) {
		log.log(level, msg, o);
	}

	/**
	 * 
	 * @param msg
	 * @param t
	 */
	public static void logThrowable(String msg, Throwable t) {
		log.log(Level.WARNING, msg, t);
	}

	/**
	 * 
	 * @param msg
	 */
	public static void severe(String msg) {
		log(Level.SEVERE, msg);
	}

	/**
	 * 
	 * @param msg
	 */
	public static void warning(String msg) {
		log(Level.WARNING, msg);
	}

	/**
	 * 
	 */
	private ProjektLogger() {
	}

}
