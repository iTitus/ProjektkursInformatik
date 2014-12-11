package projektkurs.command;

import projektkurs.Main;
import projektkurs.level.Level;
import projektkurs.lib.Levels;
import projektkurs.util.MathUtil;

/**
 * Switch-Level-Kommando.
 */
public class CommandSwitch extends Command {

    @Override
    public EnumCommandResult execute(String[] args) {

        if (args.length < 1 || args.length > 2) {
            return EnumCommandResult.WRONG_USAGE;
        }

        int map = -1;

        try {
            map = Integer.valueOf(args.length == 1 ? args[0] : args[1]);
        } catch (NumberFormatException e) {
            return EnumCommandResult.NUMBER_PARSING;
        }

        Level l = null;
        if (args.length == 2) {
            l = Levels.MAPPINGS.get(args[0]);
        } else {
            l = Main.getLevel();
        }

        if (l == null || !MathUtil.isInArray(map, l.getMapCount())) {
            return EnumCommandResult.OBJECT_NOT_FOUND;
        }

        l.setMap(map);
        Main.setLevel(l);

        return EnumCommandResult.SUCCESS;
    }

    @Override
    public String getCommand() {
        return "switch";
    }

}
