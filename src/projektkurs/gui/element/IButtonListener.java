package projektkurs.gui.element;

import java.awt.event.MouseEvent;

/**
 * Ein Interface fuer ein GUI, das auf Knopfeingaben hoeren soll.
 */
public interface IButtonListener extends IElementListener {

    /**
     * Wird ausgefuehrt, wenn mit der linken Maustaste auf einen Knopf geklickt wird.
     *
     * @param button
     *            der gedrueckte Knopf
     * @param e
     *            MouseEvent
     */
    void onButtonLeftClick(Button button, MouseEvent e);

    /**
     * Wird ausgefuehrt, wenn mit der rechten Maustaste auf einen Knopf geklickt wird.
     *
     * @param button
     *            der gedrueckte Knopf
     * @param e
     *            MouseEvent
     */
    void onButtonRightClick(Button button, MouseEvent e);

}
