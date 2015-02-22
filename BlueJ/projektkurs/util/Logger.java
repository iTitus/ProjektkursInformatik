package projektkurs.util;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Logger.
 */
public final class Logger {

    /**
     * Veschiedene LogLevel - beeinflussen den Praefix.
     */
    public static enum LogLevel {
        /**
         * Debug.
         */
        DEBUG,
        /**
         * Information.
         */
        INFO,
        /**
         * Warnungen.
         */
        WARN
    }

    /**
     * Erweitertes Datumsformat.
     */
    private static final DateFormat EXTENDED_DATE = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss");
    /**
     * Alle Log-Zeilen.
     */
    private static final ArrayList<String> LOG = new ArrayList<String>();
    /**
     * Einfaches Datumsformat.
     */
    private static final DateFormat SIMPLE_DATE = new SimpleDateFormat("HH:mm:ss");

    /**
     * Loggt auf DEBUG.
     *
     * @param msg
     *            Nachricht
     */
    public static void debug(String msg) {
        log(LogLevel.DEBUG, msg);
    }

    /**
     * Loggt auf DEBUG.
     *
     * @param msg
     *            Nachricht
     * @param objs
     *            eventuelle zusaetzliche Objekte
     */
    public static void debug(String msg, Object... objs) {
        log(LogLevel.DEBUG, msg, objs);
    }

    /**
     * Loggt auf INFO.
     *
     * @param msg
     *            Nachricht
     */
    public static void info(String msg) {
        log(LogLevel.INFO, msg);
    }

    /**
     * Loggt auf INFO.
     *
     * @param msg
     *            Nachricht
     * @param objs
     *            eventuelle zusaetzliche Objekte
     */
    public static void info(String msg, Object... objs) {
        log(LogLevel.INFO, msg, objs);
    }

    /**
     * Loggt eine Nachricht auf einem Level.
     *
     * @param level
     *            LogLevel
     * @param msg
     *            Nachricht
     * @param objs
     *            eventuelle zusaetzliche Objekte
     */
    public static void log(LogLevel level, String msg, Object... objs) {
        if (level != null && msg != null && !msg.equalsIgnoreCase("")) {
            synchronized (SIMPLE_DATE) {
                String out1 = String.format("[%s] [%s]: %s", SIMPLE_DATE.format(new Date()), level.toString(), msg);
                LOG.add(out1);
                synchronized (System.out) {
                    System.out.println(out1);
                }
            }
            if (objs != null && objs.length > 0) {
                int i = 1;
                for (Object o : objs) {
                    String oStr = o != null ? o.toString() : "null";
                    if (oStr == null) {
                        oStr = "null";
                    }
                    synchronized (SIMPLE_DATE) {
                        String out2 = String.format("[%s] [%s]: %d) %s", SIMPLE_DATE.format(new Date()), level.toString(), i++, oStr);
                        LOG.add(out2);
                        synchronized (System.out) {
                            System.out.println(out2);
                        }
                    }
                }
            }
        }
    }

    /**
     * Loggt ein Throwable.
     *
     * @param msg
     *            Nachricht
     * @param t
     *            Throwable
     */
    public static void logThrowable(String msg, Throwable t) {
        if (t != null) {

            ArrayList<String> stackTrace = new ArrayList<String>();

            stackTrace.add(t.toString());
            StackTraceElement[] trace = t.getStackTrace();
            for (StackTraceElement traceElement : trace) {
                stackTrace.add("\tat " + traceElement);
            }

            for (Throwable se : t.getSuppressed()) {
                if (se != null) {
                    logThrowable("Supressed", se);
                }
            }

            Throwable cause = t.getCause();
            if (cause != null) {
                logThrowable("Caused by", cause);
            }

            warn(msg + ": " + t.toString(), stackTrace.toArray());
        } else {
            warn(msg + ": " + t);
        }
    }

    /**
     * Speichert den Log in einer Datei.
     */
    public static void saveLog() {
        synchronized (EXTENDED_DATE) {
            try {
                Files.write(new File("log_" + EXTENDED_DATE.format(new Date()) + ".txt").toPath(), LOG, Charset.defaultCharset());
            } catch (Throwable t) {
                Logger.logThrowable("Unable to save log", t);
            }
        }
    }

    /**
     * Loggt auf WARN.
     *
     * @param msg
     *            Nachricht
     */
    public static void warn(String msg) {
        log(LogLevel.WARN, msg);
    }

    /**
     * Loggt auf WARN.
     *
     * @param msg
     *            Nachricht
     * @param objs
     *            eventuelle zusaetzliche Objekte
     */
    public static void warn(String msg, Object... objs) {
        log(LogLevel.WARN, msg, objs);
    }

    /**
     * Nicht instanziierbar.
     */
    private Logger() {
    }

}
