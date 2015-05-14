package projektkurs.command;

import projektkurs.Main;
import projektkurs.lib.Raster;
import projektkurs.raster.AbstractRaster;

/**
 * Das Raster-Setz-Kommando.
 */
public class CommandSetRaster extends Command {

	@Override
	public EnumCommandResult execute(String[] args) {

		if (args.length != 3) {
			return EnumCommandResult.WRONG_USAGE;
		}

		int x = -1;
		int y = -1;
		try {
			x = Integer.valueOf(args[0]);
			y = Integer.valueOf(args[1]);
		} catch (NumberFormatException e) {
			return EnumCommandResult.NUMBER_PARSING;
		}
		if (!Main.getLevel().getMap().isInMap(x, y)) {
			return EnumCommandResult.OUT_OF_BOUNDS;
		}

		AbstractRaster r = Raster.MAPPINGS.get(args[2]);
		if (r == null) {
			return EnumCommandResult.OBJECT_NOT_FOUND;
		}

		Main.getLevel().getMap().setRasterAt(x, y, r);

		return EnumCommandResult.SUCCESS;
	}

	@Override
	public String[] getAliases() {
		return new String[]{"setr", "set"};
	}

	@Override
	public String getCommand() {
		return "setraster";
	}

}
