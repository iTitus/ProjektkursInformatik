package projektkurs.command;

import projektkurs.Main;
import projektkurs.gui.GuiSimulation;

/**
 * Das Kommando, um die Simulation zu öffnen.
 */
public class CommandOpenSimulation extends Command {

    @Override
    public EnumCommandResult execute(String[] args) {
        Main.openGui(new GuiSimulation());
        return EnumCommandResult.SUCCESS;
    }

    @Override
    public String getCommand() {
        return "simulation";
    }

}
