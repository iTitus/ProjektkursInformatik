package projektkurs.gui.element;

import java.awt.event.MouseEvent;

public interface IInventoryElementListener extends IElementListener {

	public void onSlotLeftClick(int slotIndex, InventoryElement invE,
			MouseEvent e);

	public void onSlotRightClick(int slotIndex, InventoryElement invE,
			MouseEvent e);

}
