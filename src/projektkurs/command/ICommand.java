package projektkurs.command;

/**
 * Ein Kommando.
 */
public interface ICommand {

    /**
     * Führt das Kommando aus.
     *
     * @param args
     *            Argumente
     * @return Ergebnis
     */
    EnumCommandResult execute(String[] args);

    /**
     * Kommandoname.
     *
     * @return Kommandoname
     */
    String getCommand();

}
