package projektkurs.lib;

import java.util.HashMap;

import projektkurs.command.CommandCutScene;
import projektkurs.command.CommandDialog;
import projektkurs.command.CommandItem;
import projektkurs.command.CommandSetRaster;
import projektkurs.command.CommandSwitch;
import projektkurs.command.ICommand;
import projektkurs.util.Init;

/**
 * Alle Kommandos.
 */
public final class Commands {

    /**
     * Die Zurück-Mappings.
     */
    public static final HashMap<ICommand, String> BACK_MAPPINGS = new HashMap<ICommand, String>();
    /**
     * CutScene-Start-Kommando.
     */
    public static ICommand cutScene;
    /**
     * Dialog-Start-Kommando.
     */
    public static ICommand dialog;
    /**
     * Item-Kommando.
     */
    public static ICommand item;
    /**
     * Die Mappings.
     */
    public static final HashMap<String, ICommand> MAPPINGS = new HashMap<String, ICommand>();
    /**
     * Raster-Setz-Kommando.
     */
    public static ICommand setraster;
    /**
     * Switch-Level-Kommando.
     */
    public static ICommand switchLevel;

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
    private static void registerMapping(ICommand c) {
        MAPPINGS.put(c.getCommand(), c);
        BACK_MAPPINGS.put(c, c.getCommand());
    }

    /**
     * Nicht instanziierbar.
     */
    private Commands() {
    }

}
