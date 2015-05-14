package projektkurs.command;

import projektkurs.Main;

/**
 * Switch-Level-Kommando.
 */
public class CommandSwitch extends Command {

	@Override
	public EnumCommandResult execute(String[] args) {

		if (args.length != 1) {
			return EnumCommandResult.WRONG_USAGE;
		}

		int map = -1;

		try {
			map = Integer.valueOf(args[0]);
		} catch (NumberFormatException e) {
			return EnumCommandResult.NUMBER_PARSING;
		}

		if (map < 0 || map >= Main.getLevel().getMapCount()) {
			return EnumCommandResult.OUT_OF_BOUNDS;
		}

		Main.getLevel().setMap(map);

		return EnumCommandResult.SUCCESS;
	}

	@Override
	public String[] getAliases() {
		return new String[]{"switchlevel", "switchl", "sw"};
	}

	@Override
	public String getCommand() {
		return "switch";
	}

}
