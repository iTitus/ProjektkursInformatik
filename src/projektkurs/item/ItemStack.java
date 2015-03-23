package projektkurs.item;

import java.util.List;

import projektkurs.gui.Gui;
import projektkurs.io.storage.SaveData;
import projektkurs.lib.Integers;
import projektkurs.lib.Items;
import projektkurs.lib.Strings;
import projektkurs.render.Sprite;
import projektkurs.util.IGuiTooltipProvider;

/**
 * Ein Itemhaufen.
 */
public class ItemStack implements IGuiTooltipProvider {

    /**
     * Laedt einen ItemStack aus der gegebenen SaveData.
     *
     * @param data
     *            SaveData
     * @return ItemStack
     */
    public static ItemStack load(SaveData data) {
        if (data == null) {
            return null;
        }
        ItemStack stack = new ItemStack(Items.ITEMS[data.getInteger(Strings.STACK_ITEM)]);
        stack.setStackSize(data.getInteger(Strings.STACK_SIZE));
        stack.setDamage(data.getInteger(Strings.STACK_DAMAGE));
        return stack;
    }

    /**
     * Schaden dieses ItemStacks.
     */
    private int damage;
    /**
     * Itemtyp dieses ItemStacks.
     */
    private final Item item;
    /**
     * Groesse dieses ItemStacks.
     */
    private int stackSize;

    /**
     * Konstruktor.
     *
     * @param item
     *            Itemtyp
     */
    public ItemStack(Item item) {
        this(item, 1);
    }

    /**
     * Konstruktor.
     *
     * @param item
     *            Itemtyp
     * @param stackSize
     *            Groesse
     */
    public ItemStack(Item item, int stackSize) {
        this(item, stackSize, 0);
    }

    /**
     * Konstruktor.
     *
     * @param item
     *            Itemtyp
     * @param stackSize
     *            Groesse
     * @param damage
     *            Schaden
     */
    public ItemStack(Item item, int stackSize, int damage) {
        if (stackSize < 0) {
            throw new IllegalArgumentException("StackSize must not be negative");
        }
        this.item = item;
        this.stackSize = stackSize;
        this.damage = damage;
    }

    @Override
    public void addTooltip(Gui gui, int mouseX, int mouseY, List<String> tooltip) {
        item.addTooltip(gui, mouseX, mouseY, this, tooltip);
    }

    /**
     * Kopiert den ItemStack.
     *
     * @return neuer ItemStack mit gleichen Feldern
     */
    public ItemStack copy() {
        return new ItemStack(item, stackSize, damage);
    }

    /**
     * Beschaedigt dieses ItemStack um den gegebenen Wert.
     *
     * @param by
     *            Wert
     */
    public void damage(int by) {
        damage += by;
    }

    /**
     * Verringert die Groesse dieses ItemStack um den gegebenen Wert.
     *
     * @param by
     *            Wert
     */
    public void decrStackSize(int by) {
        stackSize -= by;
    }

    /**
     * Der Schaden dieses ItemStacks.
     *
     * @return Schaden
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Der Itemtyp dieses ItemStacks.
     *
     * @return Itemtyp
     */
    public Item getItem() {
        return item;
    }

    public String getName() {
        return item.getName(this);
    }

    /**
     * Das Bild dieses ItemStacks.
     *
     * @return Bild
     */
    public Sprite getSprite() {
        return item.getSprite(this);
    }

    /**
     * Die Groesse dieses ItemStacks.
     *
     * @return Groesse
     */
    public int getStackSize() {
        return stackSize;
    }

    /**
     * Erhoeht die Groesse dieses ItemStacks um den gegebenen Wert.
     *
     * @param by
     *            Wert
     */
    public void incrStackSize(int by) {
        stackSize += by;
    }

    /**
     * Ist der Itemtyp und der Schaden des gegebenen ItemStacks identisch mit diesem Itemstack. Ignoriert die Groesse des ItemStacks
     *
     * @param other
     *            ItemStack
     * @return true, wenn ja; false, wenn nein
     */
    public boolean itemAndDamageEquals(ItemStack other) {
        return (other.damage == damage || damage == Integers.WILDCARD_VALUE || other.damage == Integers.WILDCARD_VALUE) && (other.item == null && item == null || other.item != null && item != null && other.item.equals(item));
    }

    /**
     * Ist der Itemtyp und die Groesse des gegebenen ItemStacks identisch mit diesem Itemstack. Ignoriert den Schaden des ItemStacks
     *
     * @param other
     *            ItemStack
     * @return true, wenn ja; false, wenn nein
     */
    public boolean itemAndStackSizeEquals(ItemStack other) {
        return other.stackSize == stackSize && (other.item == null && item == null || other.item != null && item != null && other.item.equals(item));
    }

    /**
     * Ist der gegebene Itemtyp identisch mit diesem Itemstack. Ignoriert die Groesse und den Schaden des ItemStacks
     *
     * @param other
     *            AbstractItem
     * @return true, wenn ja; false, wenn nein
     */
    public boolean itemEquals(Item other) {
        return other == null && item == null || other != null && item != null && other.equals(item);
    }

    /**
     * Ist der Itemtyp des gegebenen ItemStacks identisch mit diesem Itemstack. Ignoriert die Groesse und den Schaden des ItemStacks
     *
     * @param other
     *            ItemStack
     * @return true, wenn ja; false, wenn nein
     */
    public boolean itemEquals(ItemStack other) {
        return other.item == null && item == null || other.item != null && item != null && other.item.equals(item);
    }

    /**
     * Setzt den Schaden.
     *
     * @param damage
     *            Schaden
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Setzt die Groesse dieses ItemStacks.
     *
     * @param stackSize
     *            Groesse
     */
    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    /**
     * Erstellt einen neuen ItemStack mit dem Itemtyp und dem dem Schaden dieses ItemStacks, aber mit der gegebenen Groesse.
     *
     * @param stackSize
     *            Groesse
     * @return ItemStack
     */
    public ItemStack split(int stackSize) {
        ItemStack stack = copy();
        stack.setStackSize(stackSize);
        return stack;
    }

    /**
     * Ist der Itemtyp und die Groesse und der Schaden des gegebenen ItemStacks identisch mit diesem Itemstack.
     *
     * @param other
     *            ItemStack
     * @return true, wenn ja; false, wenn nein
     */
    public boolean stackEquals(ItemStack other) {
        return other.stackSize == stackSize && other.damage == damage && (other.item == null && item == null || other.item != null && item != null && other.item.equals(item));
    }

    @Override
    public String toString() {
        return String.format("ItemStack {%s}", String.format("%d x %s:%d", stackSize, item.getInternalName(), damage));
    }

    /**
     * Speichert diesen ItemStack in einem SaveData-Objekt.
     *
     * @return SaveData
     */
    public SaveData write() {
        SaveData data = new SaveData();
        data.set(Strings.STACK_ITEM, item.getID());
        data.set(Strings.STACK_SIZE, stackSize);
        data.set(Strings.STACK_DAMAGE, damage);
        return data;
    }

}
