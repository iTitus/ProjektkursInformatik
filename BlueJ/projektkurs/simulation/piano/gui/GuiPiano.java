package projektkurs.simulation.piano.gui;

import projektkurs.gui.Gui;
import projektkurs.render.Screen;
import projektkurs.simulation.piano.EnumNote;
import projektkurs.simulation.piano.EnumOctave;
import projektkurs.simulation.piano.PianoNotes;
import projektkurs.util.RenderUtil;

public class GuiPiano extends Gui {

    @Override
    public void initGui() {
        super.initGui();
        if (PianoNotes.toneMap == null) {
            PianoNotes.fillMap();
        }
        int i = 0;
        int offset = 0;
        for (EnumOctave octave : EnumOctave.OCTAVES) {
            for (EnumNote note : EnumNote.ALL_NOTES) {
                if (note.isNoteInBetween()) {
                    addElement(new ElementPianoButton(64 + i * 64 - offset * 64 - 16, 64, 32, 128, i, octave, note));
                    offset++;
                } else {
                    addElement(new ElementPianoButton(64 + i * 64 - offset * 64, 64, 64, 256, i, octave, note));
                }
                i++;
            }
        }
    }

    @Override
    public void render(Screen screen) {
        RenderUtil.drawDefaultBackground(screen);
        super.render(screen);
    }

}
