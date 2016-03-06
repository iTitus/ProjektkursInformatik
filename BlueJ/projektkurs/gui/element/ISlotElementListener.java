package projektkurs.gui.element;

import java.awt.event.MouseEvent;

public interface ISlotElementListener extends IElementListener {

    /**
     * Wird ausgefuehrt, wenn mit der linken Maustaste auf einen Slot geklickt wird.
     * @param slotIndex
     * der Index des angeklickten Slots
     * @param slotE
     * das SlotElement, in dem dieser Slot liegt
     * @param e
     * MouseEvent
     */
    void onSlotLeftClick(int slotIndex, SlotElement slotE, MouseEvent e);

    /**
     * Wird ausgefuehrt, wenn mit der rechten Maustaste auf einen Slot geklickt wird.
     * @param slotIndex
     * der Index des angeklickten Slots
     * @param slotE
     * das SlotElement, in dem dieser Slot liegt
     * @param e
     * MouseEvent
     */
    void onSlotRightClick(int slotIndex, SlotElement slotE, MouseEvent e);

}
