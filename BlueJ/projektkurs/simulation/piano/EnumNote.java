package projektkurs.simulation.piano;

import projektkurs.lib.Sounds.Sound;

public enum EnumNote {

	A(9, "a"), A_SHARP(10, "a", true), C(0, "c"), C_SHARP(1, "c", true), D(2, "d"), D_SHARP(3, "d", true), E(4, "e"), F(5, "f"), F_SHARP(6, "f", true), G(7, "g"), G_SHARP(8, "g", true), H(11, "h");

	public static final EnumNote[] ALL_NOTES = {C, C_SHARP, D, D_SHARP, E, F, F_SHARP, G, G_SHARP, A, A_SHARP, H};
	public static final EnumNote[] BETWEEN_NOTES = {C_SHARP, D_SHARP, F_SHARP, G_SHARP, A_SHARP};
	public static final EnumNote[] NOTES = {C, D, E, F, G, A, H};

	private final int index;
	private final String name;
	private final boolean noteInBetween;

	private EnumNote(int index, String name) {
		this(index, name, false);
	}

	private EnumNote(int index, String name, boolean noteInBetween) {
		this.index = index;
		this.name = name;
		this.noteInBetween = noteInBetween;
	}

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name + (noteInBetween ? "_sharp" : "");
	}

	public Sound getTone(EnumOctave octave) {
		return PianoNotes.getNote(octave, this);
	}

	public boolean isNoteInBetween() {
		return noteInBetween;
	}

	public EnumNote oneDown() {
		return ALL_NOTES[index > 0 ? index - 1 : ALL_NOTES.length - 1];
	}

	public EnumNote oneUp() {
		return ALL_NOTES[index < ALL_NOTES.length - 1 ? index + 1 : 0];
	}

}
