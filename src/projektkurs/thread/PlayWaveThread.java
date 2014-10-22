package projektkurs.thread;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import projektkurs.lib.Logger;
import projektkurs.lib.Sounds.Sound;

/**
 * Zuständig für Audiowiedergaben
 *
 */
public class PlayWaveThread extends Thread {

	private static final int BUFFER_SIZE = 2097152; // 2MB

	private static int NUM_THREADS = 0;
	private final Sound sound;

	public PlayWaveThread(Sound _sound) {
		super("Sound - " + ++NUM_THREADS);
		sound = _sound;
	}

	@Override
	public void run() {

		SourceDataLine line = null;
		AudioInputStream stream = null;

		try {
			stream = AudioSystem.getAudioInputStream(sound.getSoundFileURL());
			AudioFormat format = stream.getFormat();
			line = (SourceDataLine) AudioSystem.getLine(new DataLine.Info(
					SourceDataLine.class, format));
			line.open(format);
			line.start();
			int bytesRead = 0;
			byte[] data = new byte[BUFFER_SIZE];
			while (bytesRead != -1) {
				bytesRead = stream.read(data, 0, data.length);
				if (bytesRead >= 0)
					line.write(data, 0, bytesRead);
			}
		} catch (Exception e) {
			Logger.logThrowable(
					"Error while playing sound file '" + sound.getSoundFileURL() + "'", e);
		} finally {
			try {
				line.drain();
				line.close();
				stream.close();
			} catch (Exception e) {
			}
		}

	}
}