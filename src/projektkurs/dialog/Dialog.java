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
    
    
    
    private int valueDialog;

    /**
     * Konstruktor.
     *
     * @param name
     *            Name
     * @param parts
     *            alle DialogParts
     */
    public Dialog(String name, int valueDialog,DialogPart... parts ) {
        this.name = name;
        this.parts = parts;
        this.valueDialog = valueDialog;
    }

    public int getMaxDialogParts() {
        return parts.length;
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
     * Alle DialogParts die sich zeigen koennen.
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
    
    public int getValueDialog() {
        return valueDialog;
    }
    
    public void setValueDialog(int valueDialog){
        this.valueDialog = valueDialog;
    }

}
