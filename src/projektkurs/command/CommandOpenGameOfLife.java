package projektkurs.command;

import projektkurs.Main;
import projektkurs.simulation.life.gui.GuiGameOfLife;

/**
 * Das Kommando, um die Logik-Simulation zu oeffnen.
 */
public class CommandOpenGameOfLife extends Command {

    @Override
    public EnumCommandResult execute(String[] args) {
        Main.openGui(new GuiGameOfLife());
        return EnumCommandResult.SUCCESS;
    }

    @Override
    public String getCommand() {
        return "logic";
    }

}
