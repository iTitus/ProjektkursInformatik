package projektkurs.lib;

import java.io.File;
import java.net.URL;

import projektkurs.Main;
import projektkurs.thread.PlayWaveThread;

/**
 * Zuständig für Sounds
 *
 */
public final class Sounds {

	public static Sound test;

	/**
	 * Initialisiert alle Sounds
	 */
	public static void init() {

		test = new Sound("Test.wav");

	}

	/**
	 * Die Klasse für ein Sound-Objekt
	 *
	 */
	public static class Sound {

		private URL soundFileURL;

		// private AudioClip clip;

		/**
		 * Kostruktor für einen Sound
		 * 
		 * @param fileName
		 */
		public Sound(String fileName) {

			soundFileURL = Main.class.getResource("resources" + File.separator
					+ "sounds" + File.separator + fileName);

			// clip = Applet.newAudioClip(soundFileURL);

		}

		/**
		 * URL (Pfad) zur Sounddatei
		 * 
		 * @return
		 */
		public URL getSoundFileURL() {
			return soundFileURL;
		}

		/**
		 * Spielt den Sound ab
		 */
		public void play() {
			new PlayWaveThread(this).start();
			// clip.play();
		}

	}

}
