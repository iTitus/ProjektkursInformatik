package projektkurs.lib;

import projektkurs.io.config.Config;
import projektkurs.io.config.ConfigCategory;
import projektkurs.io.config.property.ConfigPropertyBoolean;
import projektkurs.util.Init;
import projektkurs.util.Init.State;

/**
 * Alle Konfig-Optionen.
 */
public final class Configs {

    public static Config generalConfig;
    public static ConfigCategory sounds;
    public static ConfigPropertyBoolean soundsMuted;

    /**
     * Initialisiert alle Konfig-Optionen.
     */
    @Init(State.RESOURCES)
    public static void init() {
        generalConfig = new Config("general");

        sounds = new ConfigCategory("sounds", "All about sounds");
        generalConfig.addCategory(sounds);

        soundsMuted = new ConfigPropertyBoolean("soundsMuted", "Whether all sounds are muted", false);
        sounds.addProperty(soundsMuted);

        generalConfig.readConfig();
    }

    /**
     * Nicht instanziierbar.
     */
    private Configs() {
        // NO-OP
    }

}
