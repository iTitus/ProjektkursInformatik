package projektkurs.lib;

import java.util.Locale;
import java.util.ResourceBundle;

import projektkurs.lib.Init.State;

/**
 * Internationalisierung
 * 
 */
public final class I18n {

	public static enum SupportedLocales {

		ENGLISH(new Locale("en", "US"), "lang.en_US"), GERMAN(new Locale("de",
				"DE"), "lang.de_DE");

		private Locale locale;
		private String name;

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

	private static ResourceBundle RESOURCE_BUNDLE;

	public static void changeLocale(SupportedLocales l) {
		if (l != null)
			currentLocale = l;
		else
			currentLocale = SupportedLocales.ENGLISH;
		init();
	}

	/**
	 * Gibt den übersetzten String zurueck
	 * 
	 * @param key
	 *            Der Schlüssel fuer den String (zB. "item.nuke.name")
	 * @return der passende String in der aktuellen Sprache oder den key falls
	 *         es keine Übersetzung gibt
	 */
	public static String getString(String key) {

		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (Exception e) {
			return '!' + key + '!';
		}

	}

	/**
	 * 
	 * @return
	 */
	public static SupportedLocales[] getSupportedLocales() {
		return SupportedLocales.values();
	}

	/**
	 * Initialisiert die gesetze Sprache und lädt die Lokalisierung
	 */
	@Init(state = State.PRE)
	public static void init() {

		try {
			ResourceBundle.clearCache();
			RESOURCE_BUNDLE = ResourceBundle.getBundle(
					"projektkurs.resources.lang.lang",
					currentLocale.getLocale());
		} catch (Exception e) {
			Logger.logThrowable("Unable to load resources for Locale "
					+ currentLocale + ": ", e);
		}

	}

}
