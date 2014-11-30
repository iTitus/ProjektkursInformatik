package projektkurs.command;

/**
 * Kommandoergebnisse.
 */
public enum CommandResult {
    /**
     * Kommando nicht erfolgreich.
     */
    GENERAL_FAILURE,
    /**
     * Kommando erfolgreich.
     */
    SUCCESS,
    /**
     * Kommando nicht erfolgreich: Falsche Benutzung.
     */
    WRONG_USAGE;
}
