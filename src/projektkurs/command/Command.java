package projektkurs.command;

/**
 * Ein Kommando.
 */
public abstract class Command {

    /**
     * Fuehrt das Kommando aus.
     *
     * @param args
     *            Argumente
     * @return Ergebnis
     */
    public abstract EnumCommandResult execute(String[] args);

    /**
     * Alle moeglichen Kommandonamen.
     *
     * @return Kommandonamen
     */
    public abstract String[] getAliases();

    /**
     * Kommandoname.
     *
     * @return Kommandoname
     */
    public abstract String getCommand();

    @Override
    public String toString() {
        return "Command[" + getCommand() + "]";
    }

}
