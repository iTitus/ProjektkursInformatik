package projektkurs.command;

import java.util.Locale;

import projektkurs.Main;
import projektkurs.simulation.life.gui.GuiGameOfLife;
import projektkurs.simulation.logic.gui.GuiLogicBoard;
import projektkurs.simulation.mandelbrot.gui.GuiMandelbrotSet;
import projektkurs.simulation.pacman.gui.GuiPacman;
import projektkurs.simulation.piano.gui.GuiPiano;
import projektkurs.simulation.tictactoe.gui.GuiTicTacToe;
import projektkurs.simulation.tower.gui.GuiTowerDefense;
import projektkurs.simulation.ulam.gui.GuiUlam;
import projektkurs.util.StringUtil;

public class CommandOpenSimulation extends Command {

    @Override
    public EnumCommandResult execute(String[] args) {
        if (args.length < 1 || args[0] == null || args[0].trim().isEmpty()) {
            return EnumCommandResult.WRONG_USAGE;
        }
        String[] subArgs = new String[args.length > 1 ? args.length - 1 : 0];
        for (int i = 0; i < subArgs.length; i++) {
            subArgs[i] = StringUtil.trimToNull(args[i + 1]);
        }
        return executeSubCommand(args[0].trim().toLowerCase(Locale.ENGLISH), subArgs);
    }

    @Override
    public String[] getAliases() {
        return new String[] { "startsimulation", "startsm", "starts", "opensimulation", "opensm", "opens", "sm", "s" };
    }

    @Override
    public String getCommand() {
        return "simulation";
    }

    private EnumCommandResult executeSubCommand(String subCommand, String[] subArgs) {
        switch (subCommand) {
            case "logic":
                Main.openGui(new GuiLogicBoard());
                break;
            case "life":
                Main.openGui(new GuiGameOfLife());
                break;
            case "ulam":
                Main.openGui(new GuiUlam());
                break;
            case "mandelbrot":
            case "mb":
            case "brot":
                Main.openGui(new GuiMandelbrotSet(0, 0, true));
                break;
            case "j":
            case "julia":
                if (subArgs.length != 2 || subArgs[0] == null || subArgs[1] == null) {
                    return EnumCommandResult.WRONG_USAGE;
                }
                String s1 = subArgs[0].trim();
                String s2 = subArgs[1].trim();
                if (s1.isEmpty() || s2.isEmpty()) {
                    return EnumCommandResult.WRONG_USAGE;
                }
                double d1;
                double d2;
                try {
                    d1 = Double.valueOf(s1);
                    d2 = Double.valueOf(s2);
                } catch (NumberFormatException e) {
                    return EnumCommandResult.NUMBER_PARSING;
                }
                Main.openGui(new GuiMandelbrotSet(d1, d2, false));
                break;
            case "pacman":
            case "pm":
                Main.openGui(new GuiPacman());
                break;
            case "p":
            case "piano":
                Main.openGui(new GuiPiano());
                break;
            case "tictactoe":
            case "ttt":
                Main.openGui(new GuiTicTacToe());
                break;
            case "td":
            case "tower":
            case "towerdef":
            case "towerdefense":
                Main.openGui(new GuiTowerDefense());
                break;
            default:
                return EnumCommandResult.OBJECT_NOT_FOUND;
        }
        return EnumCommandResult.SUCCESS;
    }
}
