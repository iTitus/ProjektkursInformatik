package projektkurs.lib;

import java.util.HashMap;

import projektkurs.command.Command;
import projektkurs.command.CommandCutScene;
import projektkurs.command.CommandDialog;
import projektkurs.command.CommandItem;
import projektkurs.command.CommandSetRaster;
import projektkurs.command.CommandSwitch;
import projektkurs.util.Init;

/**
 * Alle Kommandos.
 */
public final class Commands {

    /**
     * Die Zurück-Mappings.
     */
    public static final HashMap<Command, String> BACK_MAPPINGS = new HashMap<Command, String>();
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
     * Die Mappings.
     */
    public static final HashMap<String, Command> MAPPINGS = new HashMap<String, Command>();
    /**
     * Raster-Setz-Kommando.
     */
    public static Command setraster;
    /**
     * Switch-Level-Kommando.
     */
    public static Command switchLevel;

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

    }

    /**
     * Registriert ein Mapping.
     *
     * @param c
     *            ICommand
     */
    private static void registerMapping(Command c) {
        MAPPINGS.put(c.getCommand(), c);
        BACK_MAPPINGS.put(c, c.getCommand());
    }

    /**
     * Nicht instanziierbar.
     */
    private Commands() {
    }

}
