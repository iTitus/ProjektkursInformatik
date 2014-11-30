package projektkurs.lib;

import java.util.HashMap;

import projektkurs.command.CommandItem;
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
     * Item-Kommando.
     */
    public static ICommand item;
    /**
     * Die Mappings.
     */
    public static final HashMap<String, ICommand> MAPPINGS = new HashMap<String, ICommand>();
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
