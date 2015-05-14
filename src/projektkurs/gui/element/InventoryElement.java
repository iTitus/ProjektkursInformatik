package projektkurs.gui.element;

import java.awt.event.MouseEvent;
import java.util.List;

import projektkurs.gui.Gui;
import projektkurs.inventory.Inventory;
import projektkurs.item.ItemStack;
import projektkurs.lib.Integers;
import projektkurs.lib.Sprites;
import projektkurs.render.Font;
import projektkurs.render.Screen;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

/**
 * Ein Inventory in einem GUI.
 */
public class InventoryElement extends Element {

	/**
	 * Das Inventory, das hiermit repraesentiert wird.
	 */
	protected Inventory inv;

	/**
	 * Konstruktor fuer ein InventoryElement in der Mitte des Guis.
	 *
	 * @param id       Nummer
	 * @param listener Listener
	 * @param inv      zu repraesentierendes Inventory
	 */
	public InventoryElement(int id, IInventoryElementListener listener, Inventory inv) {
		this(MathUtil.floorDiv(Integers.windowX, 2), MathUtil.floorDiv(Integers.windowY, 2), id, listener, inv);
	}

	/**
	 * Konstruktor.
	 *
	 * @param centerX  X-Koordinate des Mittelpunkts
	 * @param centerY  Y-Koordinate des Mittelpunkts
	 * @param id       Nummer
	 * @param listener Listener
	 * @param inv      zu repraesentierendes Inventory
	 */
	public InventoryElement(int centerX, int centerY, int id, IInventoryElementListener listener, Inventory inv) {
		this(centerX - MathUtil.floorDiv(Integers.SLOT_SIZE * inv.getSize(), 2), centerY - MathUtil.floorDiv(Integers.SLOT_SIZE, 2), Integers.SLOT_SIZE * inv.getSize(), Integers.SLOT_SIZE, id, listener, inv);
	}

	/**
	 * Konstruktor.
	 *
	 * @param posX     X-Koordinate
	 * @param posY     Y-Koordinate
	 * @param sizeX    Breite
	 * @param sizeY    Hoehe
	 * @param id       Nummer
	 * @param listener Listener
	 * @param inv      zu repraesentierendes Inventory
	 */
	public InventoryElement(int posX, int posY, int sizeX, int sizeY, int id, IInventoryElementListener listener, Inventory inv) {
		super(posX, posY, sizeX, sizeY, id, listener);
		this.inv = inv;
	}

	@Override
	public void addTooltip(Gui gui, int mouseX, int mouseY, List<String> tooltip) {
		ItemStack hovered = inv.getItemStackAt(MathUtil.floorDiv(mouseX - posX, Integers.SLOT_SIZE));
		if (hovered != null) {
			hovered.addTooltip(gui, mouseX, mouseY, tooltip);
		}
	}

	/**
	 * Das Inventory.
	 *
	 * @return Inventory
	 */
	public Inventory getInventory() {
		return inv;
	}

	@Override
	public IInventoryElementListener getListener() {
		return (IInventoryElementListener) super.getListener();
	}

	@Override
	public void onLeftClick(int x, int y, MouseEvent e) {
		if (isInside(x, y)) {
			getListener().onSlotLeftClick(MathUtil.floorDiv(x - posX, Integers.SLOT_SIZE), this, e);
		}
	}

	@Override
	public void onRightClick(int x, int y, MouseEvent e) {
		if (isInside(x, y)) {
			getListener().onSlotRightClick(MathUtil.floorDiv(x - posX, Integers.SLOT_SIZE), this, e);
		}
	}

	@Override
	public void render(Screen screen) {
		ItemStack stack;
		for (int i = 0; i < inv.getSize(); i++) {
			RenderUtil.drawSprite(screen, Sprites.slot, i * Integers.SLOT_SIZE + posX, posY);
			stack = inv.getItemStackAt(i);
			if (stack != null) {
				RenderUtil.drawSprite(screen, stack.getSprite(), i * Integers.SLOT_SIZE + posX + 1, posY + 1);
				Font.drawString(screen, stack.getStackSize() + "", i * Integers.SLOT_SIZE + posX + 1, posY + 11);
			}
		}
	}

}
