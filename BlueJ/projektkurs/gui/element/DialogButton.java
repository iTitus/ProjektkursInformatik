package projektkurs.gui.element;

import projektkurs.dialog.DialogPart;

/**
 * Ein Dialog-Auswaehlbutton.
 */
public class DialogButton extends Button {

	/**
	 * Der hierauf folgende DialogPart.
	 */
	private final DialogPart dialogPart;

	/**
	 * Konstruktor.
	 *
	 * @param posX       X-Koordinate
	 * @param posY       Y-Koordinate
	 * @param id         Nummer
	 * @param listener   Listener
	 * @param dialogPart folgende DialogPart
	 */
	public DialogButton(int posX, int posY, int id, IButtonListener listener, DialogPart dialogPart) {
		super(posX, posY, id, listener, dialogPart.getName());
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
