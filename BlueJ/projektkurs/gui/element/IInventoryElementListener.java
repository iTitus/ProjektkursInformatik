package projektkurs.gui.element;

import java.awt.event.MouseEvent;

/**
 * Ein Interface für ein GUI, das auf Slotklicks hören soll.
 */
public interface IInventoryElementListener extends IElementListener {

  /**
   * Wird ausgeführt, wenn mit der linken Maustaste auf einen Slot geklickt wird.
   *
   * @param slotIndex
   *          der Index des angeklickten Slots
   * @param invE
   *          das InventoryElement, in dem dieser Slot liegt
   * @param e
   *          MouseEvent
   */
  void onSlotLeftClick(int slotIndex, InventoryElement invE, MouseEvent e);

  /**
   * Wird ausgeführt, wenn mit der rechten Maustaste auf einen Slot geklickt wird.
   *
   * @param slotIndex
   *          der Index des angeklickten Slots
   * @param invE
   *          das InventoryElement, in dem dieser Slot liegt
   * @param e
   *          MouseEvent
   */
  void onSlotRightClick(int slotIndex, InventoryElement invE, MouseEvent e);

}
