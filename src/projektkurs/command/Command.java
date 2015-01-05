package projektkurs.command;

/**
 * Ein Kommando.
 */
public abstract class Command {

    /**
     * Führt das Kommando aus.
     *
     * @param args
     *            Argumente
     * @return Ergebnis
     */
    public abstract EnumCommandResult execute(String[] args);

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
