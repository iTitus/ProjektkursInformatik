package projektkurs.gui.element;

import java.awt.event.MouseWheelEvent;

/**
 * Ein Interface für ein GUI, das auf Slotklicks hören soll.
 */
public interface IPlayerInventoryElementListener extends IInventoryElementListener {

  /**
   * Wird ausgeführt, wenn der ausgewählte Slot verändert wird.
   *
   * @param invE
   *          PlayerInventoryElement
   * @param e
   *          MouseWheelEvent
   */
  void onSlotChanged(PlayerInventoryElement invE, MouseWheelEvent e);

}
