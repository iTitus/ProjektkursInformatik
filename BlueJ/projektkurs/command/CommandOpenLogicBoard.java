package projektkurs.command;

import projektkurs.Main;
import projektkurs.simulation.logic.gui.GuiLogicBoard;

/**
 * Das Kommando, um die Logik-Simulation zu oeffnen.
 */
public class CommandOpenLogicBoard extends Command {

    @Override
    public EnumCommandResult execute(String[] args) {
        Main.openGui(new GuiLogicBoard());
        return EnumCommandResult.SUCCESS;
    }

    @Override
    public String getCommand() {
        return "logic";
    }

}
