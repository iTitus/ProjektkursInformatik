package projektkurs.dialog;

import java.util.ArrayList;
import java.util.Arrays;

import projektkurs.dialog.part.DialogPart;

/**
 * Dialog.
 */
public class Dialog {

    /**
     * Alle DialogParts in diesem Dialog.
     */
    private final ArrayList<DialogPart> parts;

    /**
     * Konstruktor.
     *
     * @param parts
     *            alle DialogParts
     */
    public Dialog(DialogPart... parts) {
        this.parts = new ArrayList<DialogPart>(Arrays.asList(parts));
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
