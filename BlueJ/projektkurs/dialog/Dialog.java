package projektkurs.dialog;

import java.util.ArrayList;

/**
 * Dialog.
 */
public class Dialog {

    /**
     * Name.
     */
    private final String name;
    /**
     * Alle DialogParts in diesem Dialog.
     */
    private final DialogPart[] parts;

    /**
     * Konstruktor.
     *
     * @param name
     *            Name
     * @param parts
     *            alle DialogParts
     */
    public Dialog(String name, DialogPart... parts) {
        this.name = name;
        this.parts = parts;
    }

    /**
     * Der Name des Dialogs.
     *
     * @return Name
     */
    public String getName() {
        return name;
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
