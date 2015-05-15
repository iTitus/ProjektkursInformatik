package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import projektkurs.io.config.Config;
import projektkurs.io.config.ConfigCategory;
import projektkurs.io.config.property.ConfigPropertyBoolean;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;
import projektkurs.util.Pair;

/**
 * Alle Konfig-Optionen.
 */
public final class Configs {

    public static Config generalConfig;
    /**
     * Die Mappings.
     */
    public static final HashMap<String, Config> MAPPINGS = new HashMap<String, Config>();
    public static ConfigCategory sounds, debug, ui;
    public static ConfigPropertyBoolean soundsMuted, debugMode, showFPSUPS;

    /**
     * Das Pair, das alle Konfigs enthaelt.
     *
     * @return Pair
     */
    public static Pair<String, List<String>> getPair() {
        return new Pair<String, List<String>>("info.configs", new ArrayList<String>(MAPPINGS.keySet()));
    }

    /**
     * Initialisiert alle Konfig-Optionen.
     */
    @Init(State.RESOURCES)
    public static void init() {
        generalConfig = new Config("general");
        registerMapping(generalConfig);

        sounds = new ConfigCategory("sounds", "All about sounds");
        generalConfig.addCategory(sounds);

        soundsMuted = new ConfigPropertyBoolean("soundsMuted", "Whether all sounds are muted", false);
        sounds.addProperty(soundsMuted);

        debug = new ConfigCategory("debug", "Debug stuff");
        generalConfig.addCategory(debug);

        debugMode = new ConfigPropertyBoolean("debugMode", "Whether debugging mode is enabled", false);
        debug.addProperty(debugMode);

        ui = new ConfigCategory("ui", "All about the user interface");
        generalConfig.addCategory(ui);

        showFPSUPS = new ConfigPropertyBoolean("showFPSUPS", "Whether the FPS and UPS should be shown", false);
        ui.addProperty(showFPSUPS);

        reloadConfigs();
    }

    public static void reloadConfigs() {
        for (Config c : MAPPINGS.values()) {
            if (c != null) {
                c.readConfig();
            }
        }
    }

    /**
     * Registriert ein Mapping.
     *
     * @param c
     *            Config
     */
    private static void registerMapping(Config c) {
        if (c != null && !MAPPINGS.containsKey(c.getConfigName())) {
            MAPPINGS.put(c.getConfigName(), c);
        } else {
            Logger.warn("Unable to register config", c);
            throw new IllegalArgumentException("Unable to register config " + c);
        }
    }

    /**
     * Nicht instanziierbar.
     */
    private Configs() {
        // NO-OP
    }

}
