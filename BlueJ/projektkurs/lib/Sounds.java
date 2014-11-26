package projektkurs.lib;

import java.util.HashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import projektkurs.Main;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;

/**
 * Alle Sounds.
 */
public final class Sounds {

    /**
     * Die Klasse für ein Sound-Objekt.
     */
    public static class Sound {

        /**
         * Der Audio-Clip.
         */
        private Clip clip;
        /**
         * Pausiert dieser Sound gerade.
         */
        private boolean pausing;

        /**
         * Konstruktor.
         *
         * @param fileName
         *            Dateiname
         */
        public Sound(String fileName) {

            pausing = false;
            try {
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(Main.class.getResource("resources/sounds/" + fileName)));
                Logger.info("Successfully loaded sound: " + fileName);
            } catch (Throwable t1) {
                try {
                    clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(Main.class.getResourceAsStream("resources/sounds/" + fileName)));
                    Logger.info("Successfully loaded sound: " + fileName);
                } catch (Throwable t2) {
                    Logger.logThrowable("Unable to load sound '" + fileName + "'", t2);
                }
            }

        }

        /**
         * Befreit die Resourcen.
         */
        public void close() {
            stop();
            if (clip != null) {
                clip.close();
            }
        }

        /**
         * Ist der Sound am pausieren.
         *
         * @return true, wenn ja; false, wenn nein
         */
        public boolean isPausing() {
            return pausing;
        }

        /**
         * Spielt den Sound sooft ab wie angegeben.
         *
         * @param count
         *            Wiederholungszahl
         */
        public void loop(int count) {
            if (clip != null && !isMuted()) {
                clip.loop(count);
            }
        }

        /**
         * Spielt den Sound in einer Endlosschleife ab.
         */
        public void loopContinuosly() {
            if (clip != null && !isMuted()) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }

        /**
         * Pausiert den Sound.
         */
        public void pause() {
            if (clip != null) {
                if (clip.isRunning()) {
                    pausing = true;
                }
                clip.stop();
            }
        }

        /**
         * Spielt den Sound ab.
         */
        public void play() {
            if (clip != null && !isMuted()) {
                clip.start();
            }
        }

        /**
         * Spielt den Sound von einer gegebenen Frame-Zahl ab.
         *
         * @param frames
         *            Frame-Zahl
         */
        public void play(int frames) {
            if (clip != null && !isMuted()) {
                clip.setFramePosition(frames);
            }
            play();
        }

        /**
         * Spielt einen Sound von vorne.
         */
        public void playFromStart() {
            reset();
            play();
        }

        /**
         * Resettet den Sound.
         */
        public void reset() {
            if (clip != null) {
                clip.setFramePosition(0);
            }
            pausing = false;
        }

        /**
         * Stoppt und resettet den Sound.
         */
        public void stop() {
            pause();
            reset();
        }

    }

    /**
     * Zurück-Mappings.
     */
    public static final HashMap<Sound, String> BACK_MAPPINGS = new HashMap<Sound, String>();
    /**
     * Explosionsgeräusch.
     */
    public static Sound explosion;
    /**
     * Mappings.
     */
    public static final HashMap<String, Sound> MAPPINGS = new HashMap<String, Sound>();
    /**
     * Sind alle Sounds gemutet.
     */
    private static boolean mute = false;

    /**
     * Befreit alle Sounds.
     */
    public static void closeAll() {
        for (Sound s : MAPPINGS.values()) {
            s.close();
        }
    }

    /**
     * Initialisiert alle Sounds.
     */
    @Init(state = State.RESOURCES)
    public static void init() {

        explosion = new Sound("boom.wav");
        registerSound("explosion", explosion);

    }

    /**
     * Sind alle Sounds gemuted.
     *
     * @return true, wenm ja; false, wenn nein
     */
    public static boolean isMuted() {
        return mute;
    }

    /**
     * Muted alle Sounds.
     *
     * @param mute
     *            true, wenn sie gemuted werden sollen; false wenn nicht
     */
    public static void mute(boolean mute) {
        Sounds.mute = mute;
        pause(Sounds.mute);
    }

    /**
     * Setzt den Pausenstatus jedes Sounds.
     *
     * @param pause
     *            true, wenn pausiert werden soll; false, wenn nicht.
     */
    public static void pause(boolean pause) {
        for (Sound s : MAPPINGS.values()) {
            if (pause) {
                s.pause();
            } else if (s.isPausing()) {
                s.play();
            }
        }
    }

    /**
     * Registriert ein Mapping.
     *
     * @param name
     *            Name
     * @param sound
     *            Sound
     */
    private static void registerSound(String name, Sound sound) {
        MAPPINGS.put(name, sound);
        BACK_MAPPINGS.put(sound, name);
    }

    /**
     * Nicht instanziierbar.
     */
    private Sounds() {
    }

}
