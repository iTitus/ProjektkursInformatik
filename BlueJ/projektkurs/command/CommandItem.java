package projektkurs.command;

import projektkurs.Main;
import projektkurs.item.AbstractItem;
import projektkurs.item.ItemStack;
import projektkurs.lib.Items;

/**
 * Das Item-Kommando.
 */
public class CommandItem implements ICommand {

    @Override
    public CommandResult execute(String[] args) {

        if (args.length < 1 || args.length > 3) {
            return CommandResult.WRONG_USAGE;
        }

        AbstractItem item = Items.MAPPINGS.get(args[0]);
        if (item == null) {
            return CommandResult.OBJECT_NOT_FOUND;
        }

        int count = 0;
        int damage = 0;

        if (args.length > 1) {
            try {
                count = Integer.valueOf(args[1]);
            } catch (NumberFormatException e) {
                return CommandResult.NUMBER_PARSING;
            }
            if (count < 1) {
                return CommandResult.OUT_OF_BOUNDS;
            }
            if (args.length == 3) {
                try {
                    damage = Integer.valueOf(args[2]);
                } catch (NumberFormatException e) {
                    return CommandResult.NUMBER_PARSING;
                }
            }
        } else {
            count = 1;
        }

        return Main.getPlayer().getInventory().addItemStack(new ItemStack(item, count, damage)) ? CommandResult.SUCCESS : CommandResult.NO_SUCCESS;
    }

    @Override
    public String getCommand() {
        return "item";
    }

}
