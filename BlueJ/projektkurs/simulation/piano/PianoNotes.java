package projektkurs.simulation.piano;

import java.awt.event.KeyEvent;
import java.util.EnumMap;
import java.util.Map;

import projektkurs.lib.Sounds;
import projektkurs.lib.Sounds.Sound;

public class PianoNotes {

	public static Map<EnumOctave, Map<EnumNote, Integer>> mnemonicMap;
	public static Map<EnumOctave, Map<EnumNote, Sound>> toneMap;

	public static void fillMap() {
		toneMap = new EnumMap<EnumOctave, Map<EnumNote, Sound>>(EnumOctave.class);
		for (EnumOctave octave : EnumOctave.OCTAVES) {
			Map<EnumNote, Sound> noteMap = new EnumMap<EnumNote, Sound>(EnumNote.class);
			for (EnumNote note : EnumNote.ALL_NOTES) {
				String toneName = note.getName() + "_" + octave.getName();
				Sound s = new Sound("PIANO_" + toneName, "piano/" + toneName + ".wav");
				Sounds.registerSound(s);
				noteMap.put(note, s);
			}
			toneMap.put(octave, noteMap);
		}

		mnemonicMap = new EnumMap<EnumOctave, Map<EnumNote, Integer>>(EnumOctave.class);

		Map<EnumNote, Integer> map = new EnumMap<EnumNote, Integer>(EnumNote.class);
		map.put(EnumNote.C, KeyEvent.VK_A);
		map.put(EnumNote.C_SHARP, KeyEvent.VK_W);
		map.put(EnumNote.D, KeyEvent.VK_S);
		map.put(EnumNote.D_SHARP, KeyEvent.VK_E);
		map.put(EnumNote.E, KeyEvent.VK_D);
		map.put(EnumNote.F, KeyEvent.VK_F);
		map.put(EnumNote.F_SHARP, KeyEvent.VK_T);
		map.put(EnumNote.G, KeyEvent.VK_G);
		map.put(EnumNote.G_SHARP, KeyEvent.VK_Z);
		map.put(EnumNote.A, KeyEvent.VK_H);
		map.put(EnumNote.A_SHARP, KeyEvent.VK_U);
		map.put(EnumNote.H, KeyEvent.VK_J);
		mnemonicMap.put(EnumOctave.OCTAVE_0, map);

		map = new EnumMap<EnumNote, Integer>(EnumNote.class);
		map.put(EnumNote.C, KeyEvent.VK_K);
		map.put(EnumNote.C_SHARP, KeyEvent.VK_O);
		map.put(EnumNote.D, KeyEvent.VK_L);
		map.put(EnumNote.D_SHARP, KeyEvent.VK_P);
		map.put(EnumNote.E, KeyEvent.VK_UNDEFINED);
		map.put(EnumNote.F, KeyEvent.VK_UNDEFINED);
		map.put(EnumNote.F_SHARP, KeyEvent.VK_UNDEFINED);
		map.put(EnumNote.G, KeyEvent.VK_UNDEFINED);
		map.put(EnumNote.G_SHARP, KeyEvent.VK_UNDEFINED);
		map.put(EnumNote.A, KeyEvent.VK_UNDEFINED);
		map.put(EnumNote.A_SHARP, KeyEvent.VK_UNDEFINED);
		map.put(EnumNote.H, KeyEvent.VK_UNDEFINED);
		mnemonicMap.put(EnumOctave.OCTAVE_1, map);
	}

	public static int getMnemonic(EnumOctave octave, EnumNote note) {
		return mnemonicMap.get(octave).get(note);
	}

	public static Sound getNote(EnumOctave octave, EnumNote note) {
		return toneMap.get(octave).get(note);
	}
}
