package projektkurs.lib;

import java.util.HashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import projektkurs.Main;
import projektkurs.util.Init;
import projektkurs.util.Init.State;
import projektkurs.util.Logger;

/**
 * Zuständig für Sounds
 */
public final class Sounds {

    /**
     * Die Klasse für ein Sound-Objekt
     */
    public static class Sound {

        private Clip    clip;
        private boolean pausing;

        /**
         * Kostruktor für einen Sound
         *
         * @param fileName
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
         * Befreit die Resourcen
         */
        public void close() {
            stop();
            if (clip != null) {
                clip.close();
            }
        }

        public boolean isPausing() {
            return pausing;
        }

        public void loop(int count) {
            if (clip != null && !isMuted()) {
                clip.loop(count);
            }
        }

        public void loopContinuosly() {
            if (clip != null && !isMuted()) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }

        /**
         * Pausiert den Sound
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
         * Spielt den Sound ab
         */
        public void play() {
            if (clip != null && !isMuted()) {
                clip.start();
            }
        }

        public void play(int frames) {
            if (clip != null && !isMuted()) {
                clip.setFramePosition(frames);
            }
            play();
        }

        /**
         * Spielt einen Sound von Vorne
         */
        public void playFromStart() {
            reset();
            play();
        }

        /**
         * Resettet den Sound
         */
        public void reset() {
            if (clip != null) {
                clip.setFramePosition(0);
            }
            pausing = false;
        }

        /**
         * Stoppt und resettet den Sound
         */
        public void stop() {
            pause();
            reset();
        }

    }

    public static final HashMap<Sound, String> BACK_MAPPINGS = new HashMap<Sound, String>();
    public static Sound                        boom;

    public static final HashMap<String, Sound> MAPPINGS      = new HashMap<String, Sound>();
    private static boolean                     mute          = false;

    /**
     * Befreit alle Sounds
     */
    public static void closeAll() {
        for (Sound s : MAPPINGS.values()) {
            s.close();
        }
    }

    /**
     * Initialisiert alle Sounds
     */
    @Init(state = State.RESOURCES)
    public static void init() {

        boom = new Sound("boom.wav");
        registerSound("boom", boom);

    }

    /**
     * @return is muted
     */
    public static boolean isMuted() {
        return mute;
    }

    /**
     * @param mute
     */
    public static void mute(boolean mute) {
        Sounds.mute = mute;
        pause(Sounds.mute);
    }

    /**
     * Setzt den Pausenstatus jedes Sounds
     *
     * @param b
     */
    public static void pause(boolean b) {
        for (Sound s : MAPPINGS.values()) {
            if (b) {
                s.pause();
            } else if (s.isPausing()) {
                s.play();
            }
        }
    }

    private static void registerSound(String name, Sound s) {
        MAPPINGS.put(name, s);
        BACK_MAPPINGS.put(s, name);
    }

    private Sounds() {
    }

}
