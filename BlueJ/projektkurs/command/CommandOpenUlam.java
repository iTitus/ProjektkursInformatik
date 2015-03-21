package projektkurs.command;

import projektkurs.Main;
import projektkurs.simulation.ulam.gui.GuiUlam;

public class CommandOpenUlam extends Command {

    @Override
    public EnumCommandResult execute(String[] args) {
        Main.openGui(new GuiUlam());
        return EnumCommandResult.SUCCESS;
    }

    @Override
    public String getCommand() {
        return "ulam";
    }

}
