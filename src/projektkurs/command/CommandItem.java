package projektkurs.command;

/**
 * Das Item-Kommando.
 */
public class CommandItem implements ICommand {

    @Override
    public CommandResult execute(String[] args) {
        return CommandResult.SUCCESS;
    }

    @Override
    public String getCommand() {
        return "item";
    }

    @Override
    public String getUsage() {
        return "item <item> [count] [damage]";
    }

}
