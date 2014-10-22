package projektkurs.lib;

import java.util.Locale;
import java.util.ResourceBundle;

import projektkurs.lib.Init.State;

/**
 * Internationalisierung
 * 
 */
public final class I18n {

	private static Locale currentLocale = Locale.ENGLISH;

	private static ResourceBundle RESOURCE_BUNDLE;

	public static void changeLocale(Locale l) {
		currentLocale = l;
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
	 * Initialisiert die gesetze Sprache und lädt die Lokalisierung
	 */
	@Init(state = State.PRE)
	public static void init() {

		try {
			RESOURCE_BUNDLE = ResourceBundle.getBundle(
					"projektkurs.resources.lang.lang", currentLocale);
		} catch (Exception e) {
			Logger.logThrowable("Unable to load resources for Locale "
					+ currentLocale + "!", e);
		}

	}

}
