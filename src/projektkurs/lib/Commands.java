package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import projektkurs.command.Command;
import projektkurs.command.CommandCutScene;
import projektkurs.command.CommandDialog;
import projektkurs.command.CommandItem;
import projektkurs.command.CommandOpenGameOfLife;
import projektkurs.command.CommandOpenLogicBoard;
import projektkurs.command.CommandOpenUlam;
import projektkurs.command.CommandReload;
import projektkurs.command.CommandSetRaster;
import projektkurs.command.CommandSwitch;
import projektkurs.util.Init;
import projektkurs.util.Logger;
import projektkurs.util.Pair;

/**
 * Alle Kommandos.
 */
public final class Commands {

    /**
     * CutScene-Start-Kommando.
     */
    public static Command cutScene;
    /**
     * Dialog-Start-Kommando.
     */
    public static Command dialog;
    /**
     * Item-Kommando.
     */
    public static Command item;
    /**
     * GoL-Simulations-Kommando.
     */
    public static Command life;
    /**
     * Logik-Simulations-Kommando.
     */
    public static Command logic;
    /**
     * Die Mappings.
     */
    public static final HashMap<String, Command> MAPPINGS = new HashMap<String, Command>();
    /**
     * Reload-Kommando.
     */
    public static Command reload;
    /**
     * Raster-Setz-Kommando.
     */
    public static Command setraster;
    /**
     * Switch-Level-Kommando.
     */
    public static Command switchLevel;
    /**
     * Ulam-Simulations-Kommando.
     */
    public static Command ulam;

    /**
     * Das Pair, das alle Kommandos enthaelt.
     *
     * @return Pair
     */
    public static Pair<String, ArrayList<String>> getPair() {
        return new Pair<String, ArrayList<String>>("info.commands", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle Dialoge.
     */
    @Init
    public static void init() {

        item = new CommandItem();
        registerMapping(item);

        dialog = new CommandDialog();
        registerMapping(dialog);

        cutScene = new CommandCutScene();
        registerMapping(cutScene);

        setraster = new CommandSetRaster();
        registerMapping(setraster);

        switchLevel = new CommandSwitch();
        registerMapping(switchLevel);

        logic = new CommandOpenLogicBoard();
        registerMapping(logic);

        life = new CommandOpenGameOfLife();
        registerMapping(life);

        ulam = new CommandOpenUlam();
        registerMapping(ulam);

        reload = new CommandReload();
        registerMapping(reload);

    }

    /**
     * Registriert ein Mapping.
     *
     * @param c
     *            ICommand
     */
    private static void registerMapping(Command c) {
        if (c != null && !MAPPINGS.containsKey(c.getCommand())) {
            MAPPINGS.put(c.getCommand(), c);
        } else {
            Logger.warn("Unable to register command", c);
            throw new IllegalArgumentException("Unable to register command " + c);
        }
    }

    /**
     * Nicht instanziierbar.
     */
    private Commands() {
    }
}
