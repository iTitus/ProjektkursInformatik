package projektkurs.command;

import projektkurs.Main;
import projektkurs.item.Item;
import projektkurs.item.ItemStack;
import projektkurs.lib.Items;

/**
 * Das Item-Kommando.
 */
public class CommandItem extends Command {

	@Override
	public EnumCommandResult execute(String[] args) {

		if (args.length < 1 || args.length > 3) {
			return EnumCommandResult.WRONG_USAGE;
		}

		Item item = Items.MAPPINGS.get(args[0]);
		if (item == null) {
			return EnumCommandResult.OBJECT_NOT_FOUND;
		}

		int count = 0;
		int damage = 0;

		if (args.length > 1) {
			try {
				count = Integer.valueOf(args[1]);
			} catch (NumberFormatException e) {
				return EnumCommandResult.NUMBER_PARSING;
			}
			if (count < 1) {
				return EnumCommandResult.OUT_OF_BOUNDS;
			}
			if (args.length == 3) {
				try {
					damage = Integer.valueOf(args[2]);
				} catch (NumberFormatException e) {
					return EnumCommandResult.NUMBER_PARSING;
				}
			}
		} else {
			count = 1;
		}

		return Main.getPlayer().getInventory().addItemStack(new ItemStack(item, count, damage)) ? EnumCommandResult.SUCCESS : EnumCommandResult.NO_SUCCESS;
	}

	@Override
	public String[] getAliases() {
		return new String[]{"giveitem", "givei", "give", "i"};
	}

	@Override
	public String getCommand() {
		return "item";
	}

}
