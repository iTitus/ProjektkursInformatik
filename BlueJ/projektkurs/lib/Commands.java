package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import projektkurs.command.Command;
import projektkurs.command.CommandCutScene;
import projektkurs.command.CommandDialog;
import projektkurs.command.CommandItem;
import projektkurs.command.CommandOpenSimulation;
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
	 * Die Mappings.
	 */
	public static final HashMap<String, Command> MAPPINGS = new HashMap<String, Command>();
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
	 * Reload-Kommando.
	 */
	public static Command reload;
	/**
	 * Raster-Setz-Kommando.
	 */
	public static Command setraster;
	/**
	 * Simulations-Kommando.
	 */
	public static Command simulation;
	/**
	 * Switch-Level-Kommando.
	 */
	public static Command switchLevel;

	/**
	 * Nicht instanziierbar.
	 */
	private Commands() {
	}

	/**
	 * Das Pair, das alle Kommandos enthaelt.
	 *
	 * @return Pair
	 */
	public static Pair<String, List<String>> getPair() {
		return new Pair<String, List<String>>("info.commands", new ArrayList<String>(MAPPINGS.keySet()));
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

		simulation = new CommandOpenSimulation();
		registerMapping(simulation);

		reload = new CommandReload();
		registerMapping(reload);

	}

	/**
	 * Registriert ein Mapping.
	 *
	 * @param c Command
	 */
	private static void registerMapping(Command c) {
		if (c != null && !MAPPINGS.containsKey(c.getCommand())) {
			MAPPINGS.put(c.getCommand(), c);
		} else {
			Logger.warn("Unable to register command", c);
			throw new IllegalArgumentException("Unable to register command " + c);
		}
	}
}
