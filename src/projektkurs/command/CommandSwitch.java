package projektkurs.command;

/**
 * Switch-Level-Kommando.
 */
public class CommandSwitch implements ICommand {

    @Override
    public CommandResult execute(String[] args) {
        return CommandResult.SUCCESS;
    }

    @Override
    public String getCommand() {
        return "switch";
    }

    @Override
    public String getUsage() {
        return "switch [level] <map>";
    }

}
