package projektkurs.gui.element;

import java.awt.event.MouseEvent;

/**
 * Ein Interface für ein GUI, das auf Knopfeingaben hören soll.
 */
public interface IButtonListener extends IElementListener {

  /**
   * Wird ausgeführt, wenn mit der linken Maustaste auf einen Knopf geklickt wird.
   *
   * @param button
   *          der gedrückte Knopf
   * @param e
   *          MouseEvent
   */
  void onButtonLeftClick(Button button, MouseEvent e);

  /**
   * Wird ausgeführt, wenn mit der rechten Maustaste auf einen Knopf geklickt wird.
   *
   * @param button
   *          der gedrückte Knopf
   * @param e
   *          MouseEvent
   */
  void onButtonRightClick(Button button, MouseEvent e);

}
