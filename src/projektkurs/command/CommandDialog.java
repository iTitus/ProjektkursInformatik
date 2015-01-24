package projektkurs.command;

import projektkurs.Main;
import projektkurs.dialog.Dialog;
import projektkurs.dialog.DialogManager;
import projektkurs.lib.Dialoge;

/**
 * Das Dialog-Start-Kommando.
 */
public class CommandDialog extends Command {

    @Override
    public EnumCommandResult execute(String[] args) {

        if (args.length != 1) {
            return EnumCommandResult.WRONG_USAGE;
        }

        Dialog dialog = Dialoge.MAPPINGS.get(args[0]);
        if (dialog == null) {
            return EnumCommandResult.OBJECT_NOT_FOUND;
        }

        DialogManager.startDialog(dialog, Main.getPlayer());

        return EnumCommandResult.SUCCESS;
    }

    @Override
    public String getCommand() {
        return "dialog";
    }

}
