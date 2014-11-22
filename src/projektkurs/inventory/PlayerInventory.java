package projektkurs.inventory;

import projektkurs.item.ItemStack;
import projektkurs.lib.Integers;
import projektkurs.lib.Strings;
import projektkurs.util.MathUtil;
import projektkurs.util.SaveData;

/**
 * Das PlayerInventory.
 */
public class PlayerInventory extends Inventory {

    /**
     * Lädt ein PlayerInventory aus der gegebenen SaveData.
     *
     * @param data
     *            SaveData
     * @return PlayerInventory
     */
    public static PlayerInventory load(SaveData data) {
        PlayerInventory inv = new PlayerInventory();

        inv.stacks = new ItemStack[data.getInteger(Strings.INV_SIZE)];

        for (int i = 0; i < inv.stacks.length; i++) {
            inv.setItemStackInSlot(i, ItemStack.load(data.getSaveData(Strings.INV_SLOT + i)));
        }

        inv.setSelectedItemStack(data.getInteger(Strings.INV_SELECTED));

        return inv;
    }

    /**
     * Ausgewählter Index.
     */
    private int selectedItemStack;

    /**
     * Konstruktor.
     *
     * @param size
     *            Größe
     */
    public PlayerInventory(int size) {
        this(size, -1);
    }

    /**
     * Konstruktor.
     *
     * @param size
     *            Größe
     * @param selectedItemStack
     *            auszuwählender Index
     */
    public PlayerInventory(int size, int selectedItemStack) {
        super(size);
        this.selectedItemStack = selectedItemStack;
    }

    /**
     * Konstruktor.
     */
    private PlayerInventory() {
        super();
    }

    /**
     * X-Koordinate auf dem Bildschirmkoordinaten.
     *
     * @return X-Koordinate auf dem Bildschirm
     */
    public int getRenderX() {
        return MathUtil.roundDiv(Integers.windowX, 2) - MathUtil.roundDiv(Integers.SLOT_SIZE * getSize(), 2);
    }

    /**
     * Y-Koordinate auf dem Bildschirmkoordinaten.
     *
     * @return Y-Koordinate auf dem Bildschirm
     */
    public int getRenderY() {
        return Integers.windowY - Integers.WINDOW_HUD_Y;
    }

    /**
     * Ausgewählter Index.
     *
     * @return Index
     */
    public int getSelectedIndex() {
        return selectedItemStack;
    }

    /**
     * Ausgewählter ItemStack.
     *
     * @return ItemStack
     */
    public ItemStack getSelectedItemStack() {
        return getItemStackAt(selectedItemStack);
    }

    /**
     * Ist ein ItemStack ausgewählt.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public boolean hasItemStackSelected() {
        return selectedItemStack < 0;
    }

    /**
     * Wählt den ItemStack am Index aus.
     *
     * @param index
     *            Index; -1, um keinen ItemStack auszuwählen
     */
    public void setSelectedItemStack(int index) {
        if (index < 0 || index >= stacks.length) {
            selectedItemStack = -1;
        } else {
            selectedItemStack = index;
        }
    }

    @Override
    public SaveData write() {
        SaveData data = super.write();
        data.set(Strings.INV_SELECTED, selectedItemStack);
        return data;
    }
}
