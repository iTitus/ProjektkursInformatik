package projektkurs.command;

/**
 * Kommandoergebnisse.
 */
public enum EnumCommandResult {
	/**
	 * Kommando nicht erfolgreich.
	 */
	GENERAL_FAILURE,
	/**
	 * Kommando nicht erfolgreich, aber trotzdem abgeschlossen.
	 */
	NO_SUCCESS,
	/**
	 * Kommando nicht erfolgreich: Fehler waehrend des Konvertierens zu einer Zahl.
	 */
	NUMBER_PARSING,
	/**
	 * Kommando nicht erfolgreich: Objekt nicht gefunden.
	 */
	OBJECT_NOT_FOUND,
	/**
	 * Kommando nicht erfolgreich: Nummer oder Text ausserhalb des gueltigen Bereiches.
	 */
	OUT_OF_BOUNDS,
	/**
	 * Kommando erfolgreich.
	 */
	SUCCESS,
	/**
	 * Kommando nicht erfolgreich: Falsche Benutzung.
	 */
	WRONG_USAGE
}
