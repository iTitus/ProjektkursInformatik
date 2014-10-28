package projektkurs.lib;

import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import projektkurs.Main;
import projektkurs.lib.Init.State;

/**
 * Zuständig für Sounds
 * 
 */
public final class Sounds {

	/**
	 * Die Klasse für ein Sound-Objekt
	 * 
	 */
	public static class Sound {

		private Clip clip;

		/**
		 * Kostruktor für einen Sound
		 * 
		 * @param fileName
		 */
		public Sound(String fileName) {

			try {
				clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(Main.class
						.getResource("resources" + File.separator + "sounds"
								+ File.separator + fileName)));
				Logger.info("Successfully loaded sound: " + fileName);
			} catch (Exception e) {
				Logger.logThrowable("Error while loading sound '" + fileName
						+ "': ", e);
			}

		}

		/**
		 * Befreit die Resourcen
		 */
		public void close() {
			stop();
			clip.close();
		}

		public void loop(int count) {
			clip.loop(count);
		}

		public void loopContinuosly() {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}

		/**
		 * Pausiert den Sound
		 */
		public void pause() {
			clip.stop();
		}

		/**
		 * Spielt den Sound ab
		 */
		public void play() {
			clip.start();
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
			clip.setFramePosition(0);
		}

		/**
		 * Stoppt und resettet den Sound
		 */
		public void stop() {
			pause();
			reset();
		}

	}

	public static final HashMap<String, Sound> MAPPINGS = new HashMap<String, Sound>();

	public static Sound test;

	public static void closeAll() {
		for (Sound s : MAPPINGS.values())
			s.close();
	}

	/**
	 * Initialisiert alle Sounds
	 */
	@Init(state = State.PRE)
	public static void init() {

		test = new Sound("Test.wav");
		MAPPINGS.put("test", test);

	}

}
