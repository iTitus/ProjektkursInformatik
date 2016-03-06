package projektkurs.gui.element;

import java.awt.event.MouseWheelEvent;

/**
 * Ein Interface fuer ein GUI, das auf Interaktionen mit dem Spielerinventar hoeren soll hoeren soll.
 */
public interface IPlayerInventoryElementListener extends IInventoryElementListener {

    /**
     * Wird ausgefuehrt, wenn der ausgewaehlte Slot veraendert wird.
     * @param invE
     * PlayerInventoryElement
     * @param e
     * MouseWheelEvent
     */
    void onSlotChanged(PlayerInventoryElement invE, MouseWheelEvent e);

}
