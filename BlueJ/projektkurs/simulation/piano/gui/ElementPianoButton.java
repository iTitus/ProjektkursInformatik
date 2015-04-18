package projektkurs.simulation.piano.gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import projektkurs.gui.Gui;
import projektkurs.gui.element.Element;
import projektkurs.lib.Sounds.Sound;
import projektkurs.render.Screen;
import projektkurs.simulation.piano.EnumNote;
import projektkurs.simulation.piano.EnumOctave;
import projektkurs.simulation.piano.PianoNotes;
import projektkurs.util.RenderUtil;

public class ElementPianoButton extends Element {

    private final int mnemonic;
    private final EnumNote note;
    private final EnumOctave octave;
    private final Sound sound;

    public ElementPianoButton(int posX, int posY, int sizeX, int sizeY, int id, EnumOctave octave, EnumNote note) {
        super(posX, posY, sizeX, sizeY, id, null);
        sound = PianoNotes.getNote(octave, note);
        mnemonic = PianoNotes.getMnemonic(octave, note);
        this.octave = octave;
        this.note = note;
    }

    @Override
    public void addTooltip(Gui gui, int mouseX, int mouseY, List<String> tooltip) {
        tooltip.add(note.getName() + " (" + octave.getName() + ") - " + KeyEvent.getKeyText(mnemonic));
    }

    @Override
    public void onKeyTyped(char keyChar, KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == mnemonic) {
            sound.playFromStart();
        }
    }

    @Override
    public void onLeftClick(int x, int y, MouseEvent e) {
        if (isInside(x, y)) {
            if (!note.isNoteInBetween()) {
                int xS = x - posX;
                int yS = y - posY;
                if (note.oneUp().isNoteInBetween()) {
                    if (xS >= 48 && yS <= 128) {
                        return;
                    }
                }
                if (note.oneDown().isNoteInBetween()) {
                    if (xS <= 16 && yS <= 128) {
                        return;
                    }
                }
            }
            sound.playFromStart();
        }
    }

    @Override
    public void render(Screen screen) {
        if (note.isNoteInBetween()) {
            RenderUtil.drawFilledRectangle(screen, posX, posY, sizeX, sizeY);
        } else {
            RenderUtil.drawRectangle(screen, posX, posY, sizeX, sizeY);
        }
    }
}
