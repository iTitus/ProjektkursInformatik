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
						.getResource("resources/sounds/" + fileName)));
				Logger.info("Successfully loaded sound: " + fileName);
			} catch (Throwable t1) {
				try {
					clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(Main.class
							.getResourceAsStream("resources/sounds/" + fileName)));
					Logger.info("Successfully loaded sound: " + fileName);
				} catch (Throwable t2) {
					Logger.logThrowable("Unable to load sound '" + fileName
							+ "'", t2);
				}
			}

		}

		/**
		 * Befreit die Resourcen
		 */
		public void close() {
			stop();
			if (clip != null)
				clip.close();
		}

		public void loop(int count) {
			if (clip != null)
				clip.loop(count);
		}

		public void loopContinuosly() {
			if (clip != null)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
		}

		/**
		 * Pausiert den Sound
		 */
		public void pause() {
			if (clip != null)
				clip.stop();
		}

		/**
		 * Spielt den Sound ab
		 */
		public void play() {
			if (clip != null)
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
			if (clip != null)
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
	@Init(state = State.RESOURCES)
	public static void init() {

		test = new Sound("Test.wav");
		MAPPINGS.put("test", test);

	}

}
