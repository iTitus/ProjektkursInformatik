package projektkurs.command;

import java.util.Locale;

import projektkurs.Main;
import projektkurs.gui.Gui;
import projektkurs.simulation.life.gui.GuiGameOfLife;
import projektkurs.simulation.logic.gui.GuiLogicBoard;
import projektkurs.simulation.pacman.gui.GuiPacMan;
import projektkurs.simulation.ulam.gui.GuiUlam;

public class CommandOpenSimulation extends Command {

    @Override
    public EnumCommandResult execute(String[] args) {
        if (args.length != 1 || args[0] == null || args[0].trim().isEmpty()) {
            return EnumCommandResult.WRONG_USAGE;
        }
        Gui gui = getGui(args[0].trim().toLowerCase(Locale.ENGLISH));
        if (gui == null) {
            return EnumCommandResult.OBJECT_NOT_FOUND;
        }
        Main.openGui(gui);
        return EnumCommandResult.SUCCESS;
    }

    @Override
    public String getCommand() {
        return "simulation";
    }

    private Gui getGui(String subCommand) {
        switch (subCommand) {
            case "logic":
                return new GuiLogicBoard();
            case "life":
                return new GuiGameOfLife();
            case "ulam":
                return new GuiUlam();
            case "pacman":
                return new GuiPacMan();
            default:
                return null;
        }
    }
}
