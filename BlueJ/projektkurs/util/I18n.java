package projektkurs.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import projektkurs.util.Init.State;

/**
 * Internationalisierung
 */
public final class I18n {

    public static enum SupportedLocales {

        ENGLISH(new Locale("en", "US"), "lang.en_US"), GERMAN(new Locale("de", "DE"), "lang.de_DE");

        public static final SupportedLocales DEFAULT = ENGLISH;

        private final Locale                 locale;
        private final String                 name;

        private SupportedLocales(Locale locale, String name) {
            this.locale = locale;
            this.name = name;
        }

        public Locale getLocale() {
            return locale;
        }

        public String getName() {
            return getString(name);
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    private static SupportedLocales currentLocale = SupportedLocales.ENGLISH;

    private static ResourceBundle   resource, fallback;

    public static void changeLocale(SupportedLocales l) {
        if (l != null) {
            currentLocale = l;
        } else {
            currentLocale = SupportedLocales.ENGLISH;
        }
        init();
    }

    /**
     * Gibt den übersetzten String zurueck
     *
     * @param key
     *            Der Schlüssel fuer den String (zB. "item.nuke.name")
     * @return der passende String in der aktuellen Sprache oder den key falls es keine Übersetzung gibt
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
     * @return
     */
    public static SupportedLocales[] getSupportedLocales() {
        return SupportedLocales.values();
    }

    /**
     * Initialisiert die gesetze Sprache und lädt die Lokalisierung
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

}
