package projektkurs.dialog;

import java.util.ArrayList;

import projektkurs.dialog.part.DialogPart;

/**
 * Dialog.
 */
public class Dialog {

    /**
     * Alle DialogParts in diesem Dialog.
     */
    private final DialogPart[] parts;

    /**
     * Konstruktor.
     *
     * @param parts
     *            alle DialogParts
     */
    public Dialog(DialogPart... parts) {
        this.parts = parts;
    }

    /**
     * Alle DialogParts die sich zeigen können.
     *
     * @return alle DialogParts
     */
    public ArrayList<DialogPart> getShownParts() {
        ArrayList<DialogPart> list = new ArrayList<DialogPart>();
        for (DialogPart part : parts) {
            if (part.shouldShowUp()) {
                list.add(part);
            }
        }
        return list;
    }

}
