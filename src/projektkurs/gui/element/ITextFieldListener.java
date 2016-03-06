package projektkurs.gui.element;

/**
 * Ein Interface fuer ein GUI, das auf Textfeldeingaben hoeren soll.
 */
public interface ITextFieldListener extends IElementListener {

    /**
     * Wird ausgefuehrt, wenn das Textfeld fokussiert wird.
     * @param field
     * TextField
     */
    void onFocusGained(TextField field);

    /**
     * Wird ausgefuehrt, wenn das Textfeld den Fokus verliert.
     * @param field
     * TextField
     */
    void onFocusLost(TextField field);

    /**
     * Wird ausgefuehrt, wenn sich der Text im Textfeld veraendert.
     * @param field
     * TextField
     */
    void onTextChanged(TextField field);

}
