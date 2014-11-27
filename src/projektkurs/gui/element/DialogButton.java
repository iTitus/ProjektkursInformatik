package projektkurs.gui.element;

import projektkurs.dialog.part.DialogPart;

/**
 * Ein Dialog-Auswählbutton.
 */
public class DialogButton extends Button {

    /**
     * Der hierauf folgende DialogPart.
     */
    private final DialogPart dialogPart;

    /**
     * Konstruktor.
     *
     * @param posX
     *            X-Koordinate
     * @param posY
     *            Y-Koordinate
     * @param sizeX
     *            Breite
     * @param sizeY
     *            Höhe
     * @param id
     *            Nummer
     * @param gui
     *            Gui/Listener
     * @param dialogPart
     *            folgende DialogPart
     */
    public DialogButton(int posX, int posY, int sizeX, int sizeY, int id, IButtonListener gui, DialogPart dialogPart) {
        super(posX, posY, sizeX, sizeY, id, gui, dialogPart.getName());
        this.dialogPart = dialogPart;
    }

    /**
     * Der hierauf folgende DialogPart.
     *
     * @return DialogPart
     */
    public DialogPart getDialogPart() {
        return dialogPart;
    }

}
