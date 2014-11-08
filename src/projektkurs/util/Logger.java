package projektkurs.util;

import java.io.File;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import projektkurs.lib.Strings;

/**
 * Logger
 */
public final class Logger {

	public static enum LogLevel {
		DEBUG(System.out), INFO(System.out), WARN(System.err);

		private final PrintStream stream;

		private LogLevel(PrintStream stream) {
			this.stream = stream;
		}

		public PrintStream getPrintStream() {
			return stream;
		}
	}

	private static final DateFormat extendedDate = new SimpleDateFormat(
			"dd.MM.yyyy_HH.mm.ss");

	private static final ArrayList<String> log = new ArrayList<String>();

	private static final DateFormat simpleDate = new SimpleDateFormat(
			"HH:mm:ss");

	static {
		log.add("Log of " + Strings.NAME + " v" + Strings.VERSION);
	}

	/**
	 * @param msg
	 */
	public static void debug(String msg) {
		log(LogLevel.DEBUG, msg);
	}

	/**
	 * @param msg
	 * @param objs
	 */
	public static void debug(String msg, Object... objs) {
		log(LogLevel.DEBUG, msg, objs);
	}

	/**
	 * @param msg
	 */
	public static void info(String msg) {
		log(LogLevel.INFO, msg);
	}

	/**
	 * @param msg
	 * @param objs
	 */
	public static void info(String msg, Object... objs) {
		log(LogLevel.INFO, msg, objs);
	}

	/**
	 * @param level
	 * @param msg
	 */
	public static void log(LogLevel level, String msg, Object... objs) {
		if (level != null && msg != null && !msg.equalsIgnoreCase("")) {
			synchronized (simpleDate) {
				String out1 = String.format("[%s] [%s]: %s",
						simpleDate.format(new Date()), level.toString(), msg);
				log.add(out1);
				level.getPrintStream().println(out1);
				level.getPrintStream().flush();
				if (objs != null && objs.length > 0) {
					int i = 1;
					for (Object o : objs) {
						if (o != null) {
							String out2 = String.format("[%s] [%s]: %d) %s",
									simpleDate.format(new Date()),
									level.toString(), i++, o);
							log.add(out2);
							level.getPrintStream().println(out2);
							level.getPrintStream().flush();
						}
					}
				}
			}
		}
	}

	/**
	 * @param msg
	 * @param t
	 */
	public static void logThrowable(String msg, Throwable t) {
		if (t != null) {

			ArrayList<String> stackTrace = new ArrayList<String>();

			stackTrace.add(t.toString());
			StackTraceElement[] trace = t.getStackTrace();
			for (StackTraceElement traceElement : trace)
				stackTrace.add("\tat " + traceElement);

			for (Throwable se : t.getSuppressed())
				if (se != null)
					logThrowable("Supressed", se);

			Throwable cause = t.getCause();
			if (cause != null)
				logThrowable("Caused by", cause);

			warn(msg + ": " + t.toString(), stackTrace.toArray());
		} else {
			warn(msg + ": " + t);
		}
	}

	public static void saveLog() {
		synchronized (extendedDate) {
			try {
				Files.write(new File("log_" + extendedDate.format(new Date())
						+ ".txt").toPath(), log, Charset.defaultCharset());
			} catch (Throwable t) {
				Logger.logThrowable("Unable to save log", t);
			}
		}
	}

	/**
	 * @param msg
	 */
	public static void warn(String msg) {
		log(LogLevel.WARN, msg);
	}

	/**
	 * @param msg
	 * @param objs
	 */
	public static void warn(String msg, Object... objs) {
		log(LogLevel.WARN, msg, objs);
	}

	/**
     *
     */
	private Logger() {
	}

}
