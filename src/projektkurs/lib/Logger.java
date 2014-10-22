package projektkurs.lib;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logger
 *
 */
public final class Logger {

	public static enum LogLevel {
		DEBUG(System.out), INFO(System.out), WARN(System.err);

		private PrintStream stream;

		private LogLevel(PrintStream stream) {
			this.stream = stream;
		}

		public PrintStream getPrintStream() {
			return stream;
		}
	}

	// private static final DateFormat extendedDate = new SimpleDateFormat(
	// "dd.MM.yyyy_HH:mm:ss");

	private static final DateFormat simpleDate = new SimpleDateFormat(
			"HH:mm:ss");

	/**
	 * 
	 * @param msg
	 */
	public static void info(String msg) {
		log(LogLevel.INFO, msg);
	}

	/**
	 * 
	 * @param msg
	 * @param objs
	 */
	public static void debug(String msg, Object... objs) {
		log(LogLevel.DEBUG, msg, objs);
	}

	/**
	 * 
	 * @param msg
	 */
	public static void debug(String msg) {
		log(LogLevel.DEBUG, msg);
	}

	/**
	 * 
	 * @param msg
	 * @param objs
	 */
	public static void info(String msg, Object... objs) {
		log(LogLevel.INFO, msg, objs);
	}

	/**
	 * 
	 * @param level
	 * @param msg
	 */
	public static void log(LogLevel level, String msg, Object... objs) {
		if (level != null && msg != null && !msg.equalsIgnoreCase("")) {
			synchronized (simpleDate) {
				level.getPrintStream().println(
						String.format("[%s] [%s]: %s",
								simpleDate.format(new Date()),
								level.toString(), msg));
				if (objs != null && objs.length > 0) {
					int i = 1;
					for (Object o : objs) {
						if (o != null) {
							level.getPrintStream().println(
									String.format("[%s] [%s]: %d) %s",
											simpleDate.format(new Date()),
											level.toString(), i++, o));
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param msg
	 * @param t
	 */
	public static void logThrowable(String msg, Throwable t) {
		Object[] str = new String[t.getStackTrace().length];
		log(LogLevel.WARN, msg + t.getMessage(), str);
	}

	/**
	 * 
	 * @param msg
	 */
	public static void warning(String msg) {
		log(LogLevel.WARN, msg);
	}

	/**
	 * 
	 * @param msg
	 * @param objs
	 */
	public static void warning(String msg, Object... objs) {
		log(LogLevel.WARN, msg, objs);
	}

	/**
	 * 
	 */
	private Logger() {
	}

}
