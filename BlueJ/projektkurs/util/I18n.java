package projektkurs.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import projektkurs.util.Init.State;

/**
 * Internationalisierung.
 */
public final class I18n {

    /**
     * Unterstützte Sprachen.
     */
    public static enum SupportedLocales {

        /**
         * Amerikanisches Englisch.
         */
        ENGLISH(new Locale("en", "US"), "lang.en_US"),
        /**
         * Deutsch.
         */
        GERMAN(new Locale("de", "DE"), "lang.de_DE");

        /**
         * Die Standardsprache.
         */
        public static final SupportedLocales DEFAULT = ENGLISH;

        /**
         * Die Java-Sprache.
         */
        private final Locale locale;
        /**
         * Der Name der Sprache.
         */
        private final String name;

        /**
         * Konstruktor.
         *
         * @param locale
         *            die Java-Sprache
         * @param name
         *            der Name
         */
        private SupportedLocales(Locale locale, String name) {
            this.locale = locale;
            this.name = name;
        }

        /**
         * Die Standard-Java-Sprache.
         *
         * @return die Standard-Java-Sprache
         */
        public Locale getLocale() {
            return locale;
        }

        /**
         * Der Name der Sprache.
         *
         * @return der Name
         */
        public String getName() {
            return getString(name);
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    /**
     * Die aktuelle Sprache.
     */
    private static SupportedLocales currentLocale = SupportedLocales.ENGLISH;

    /**
     * Die Standard-Lokalisierung.
     */
    private static ResourceBundle fallback;
    /**
     * Aktuelle Lokalisation.
     */
    private static ResourceBundle resource;

    /**
     * Ändert die Sprache.
     *
     * @param locale
     *            neue Sprache
     */
    public static void changeLocale(SupportedLocales locale) {
        if (locale != null) {
            currentLocale = locale;
        } else {
            currentLocale = SupportedLocales.DEFAULT;
        }
        init();
    }

    /**
     * Gibt den übersetzten String zurück.
     *
     * @param key
     *            Der Schlüssel für den String (zB. "item.nuke.name")
     * @return der passende formatierte String in der aktuellen Sprache oder !key! falls es keine oder nur eine falsche Übersetzung gibt
     */
    public static String getString(String key) {

        try {
            return resource.getString(key);
        } catch (Throwable t) {
            try {
                return fallback.getString(key);
            } catch (Throwable t1) {
                return '!' + key + '!';
            }
        }

    }

    /**
     * Gibt den formatierten übersetzten String zurück.
     *
     * @param key
     *            Der Schlüssel für den String (zB. "item.nuke.name")
     * @param args
     *            Formatierungsargumente
     * @return der passende String in der aktuellen Sprache oder !key! falls es keine Übersetzung gibt
     */
    public static String getStringFormatted(String key, Object... args) {

        try {
            return String.format(resource.getString(key), args);
        } catch (Throwable t) {
            try {
                return String.format(fallback.getString(key), args);
            } catch (Throwable t1) {
                return '!' + key + '!';
            }
        }

    }

    /**
     * Alle unterstützten Sprachen.
     *
     * @return alle unterstützten Sprachen
     */
    public static SupportedLocales[] getSupportedLocales() {
        return SupportedLocales.values();
    }

    /**
     * Initialisiert die gesetze Sprache und lädt die Lokalisierung.
     */
    @Init(state = State.RESOURCES)
    public static void init() {

        ResourceBundle.clearCache();

        try {
            fallback = ResourceBundle.getBundle("projektkurs.resources.lang.lang", SupportedLocales.DEFAULT.getLocale());
        } catch (Throwable t) {
            Logger.logThrowable("Unable to load fallback resources", t);
        }
        try {
            resource = ResourceBundle.getBundle("projektkurs.resources.lang.lang", currentLocale.getLocale());
            Logger.info("Successfully loaded resources for locale '" + currentLocale.getLocale() + "'");
        } catch (Throwable t) {
            Logger.logThrowable("Unable to load resources for locale " + currentLocale, t);
        }

        ArrayList<String> missingResources = null;
        Enumeration<String> fallbackKeys = fallback.getKeys();
        String currKey;
        while (fallbackKeys.hasMoreElements()) {
            currKey = fallbackKeys.nextElement();
            try {
                resource.getString(currKey);
            } catch (Throwable t) {
                if (missingResources == null) {
                    missingResources = new ArrayList<String>();
                }
                missingResources.add(currKey);
            }
        }
        if (missingResources != null) {
            Logger.warn("Resources for Locale '" + currentLocale.getLocale() + "' are incomplete. Missing keys are:", missingResources);
        }

    }

    /**
     * Nicht instanziierbar.
     */
    private I18n() {
    }

}
