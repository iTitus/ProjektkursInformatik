package projektkurs.gui.element;

/**
 * Ein Interface für ein GUI, das auf Textfeldeingaben hören soll.
 */
public interface ITextFieldListener extends IElementListener {

  /**
   * Wird ausgeführt, wenn das Textfeld fokussiert wird.
   *
   * @param field
   *          TextField
   */
  void onFocusGained(TextField field);

  /**
   * Wird ausgeführt, wenn das Textfeld den Fokus verliert.
   *
   * @param field
   *          TextField
   */
  void onFocusLost(TextField field);

  /**
   * Wird ausgeführt, wenn sich der Text im Textfeld verändert.
   *
   * @param field
   *          TextField
   */
  void onTextChanged(TextField field);

}
