package projektkurs.command;

import projektkurs.Main;
import projektkurs.level.Level;
import projektkurs.lib.Levels;

/**
 * Switch-Level-Kommando.
 */
public class CommandSwitch implements ICommand {

    @Override
    public CommandResult execute(String[] args) {

        if (args.length < 1 || args.length > 2) {
            return CommandResult.WRONG_USAGE;
        }

        int map = -1;

        try {
            map = Integer.valueOf(args.length == 1 ? args[0] : args[1]);
        } catch (NumberFormatException e) {
            return CommandResult.NUMBER_PARSING;
        }

        Level l = null;
        if (args.length == 2) {
            l = Levels.MAPPINGS.get(args[0]);
        } else {
            l = Main.getLevel();
        }

        if (l == null || map < 0 || map >= l.getMapCount()) {
            return CommandResult.OBJECT_NOT_FOUND;
        }

        l.setMap(map);
        Main.setLevel(l);

        return CommandResult.SUCCESS;
    }

    @Override
    public String getCommand() {
        return "switch";
    }

}
