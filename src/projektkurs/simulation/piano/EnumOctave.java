package projektkurs.simulation.piano;

import projektkurs.lib.Sounds.Sound;

public enum EnumOctave {

    OCTAVE_0(0, "0"), OCTAVE_1(1, "1");

    public static final EnumOctave[] OCTAVES = { OCTAVE_0, EnumOctave.OCTAVE_1 };

    private final int index;
    private final String name;

    private EnumOctave(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public Sound getTone(EnumNote note) {
        return PianoNotes.getNote(this, note);
    }
}
