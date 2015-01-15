package projektkurs.item;

import projektkurs.lib.Items;
import projektkurs.lib.Strings;
import projektkurs.render.Sprite;
import projektkurs.util.SaveData;

/**
 * Ein Itemhaufen.
 */
public class ItemStack {

    /**
     * Lädt einen ItemStack aus der gegebenen SaveData.
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
     * Größe dieses ItemStacks.
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
     *            Größe
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
     *            Größe
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

    /**
     * Kopiert den ItemStack.
     *
     * @return neuer ItemStack mit gleichen Feldern
     */
    public ItemStack copy() {
        return new ItemStack(item, stackSize, damage);
    }

    /**
     * Beschädigt dieses ItemStack um den gegebenen Wert.
     *
     * @param by
     *            Wert
     */
    public void damage(int by) {
        damage += by;
    }

    /**
     * Verringert die Größe dieses ItemStack um den gegebenen Wert.
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
     * Das Bild dieses ItemStacks.
     *
     * @return Bild
     */
    public Sprite getSprite() {
        return item.getSprite(this);
    }

    /**
     * Der Itemtyp dieses ItemStacks.
     *
     * @return Itemtyp
     */
    public Item getItem() {
        return item;
    }

    /**
     * Der Name dieses ItemStacks.
     *
     * @return Name
     */
    public String getName() {
        return String.format("%d x %s:%d", stackSize, item.getName(this), damage);
    }

    /**
     * Die Größe dieses ItemStacks.
     *
     * @return Größe
     */
    public int getStackSize() {
        return stackSize;
    }

    /**
     * Erhöht die Größe dieses ItemStacks um den gegebenen Wert.
     *
     * @param by
     *            Wert
     */
    public void incrStackSize(int by) {
        stackSize += by;
    }

    /**
     * Ist der Itemtyp und der Schaden des gegebenen ItemStacks identisch mit diesem Itemstack. Ignoriert die Größe des ItemStacks
     *
     * @param other
     *            ItemStack
     * @return true, wenn ja; false, wenn nein
     */
    public boolean itemAndDamageEquals(ItemStack other) {
        return other.damage == damage && (other.item == null && item == null || other.item != null && item != null && other.item.equals(item));
    }

    /**
     * Ist der Itemtyp und die Größe des gegebenen ItemStacks identisch mit diesem Itemstack. Ignoriert den Schaden des ItemStacks
     *
     * @param other
     *            ItemStack
     * @return true, wenn ja; false, wenn nein
     */
    public boolean itemAndStackSizeEquals(ItemStack other) {
        return other.stackSize == stackSize && (other.item == null && item == null || other.item != null && item != null && other.item.equals(item));
    }

    /**
     * Ist der gegebene Itemtyp identisch mit diesem Itemstack. Ignoriert die Größe und den Schaden des ItemStacks
     *
     * @param other
     *            AbstractItem
     * @return true, wenn ja; false, wenn nein
     */
    public boolean itemEquals(Item other) {
        return other == null && item == null || other != null && item != null && other.equals(item);
    }

    /**
     * Ist der Itemtyp des gegebenen ItemStacks identisch mit diesem Itemstack. Ignoriert die Größe und den Schaden des ItemStacks
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
     * Setzt die Größe dieses ItemStacks.
     *
     * @param stackSize
     *            Größe
     */
    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    /**
     * Erstellt einen neuen ItemStack mit dem Itemtyp und dem dem Schaden dieses ItemStacks, aber mit der gegebenen Größe.
     *
     * @param stackSize
     *            Größe
     * @return ItemStack
     */
    public ItemStack split(int stackSize) {
        ItemStack stack = copy();
        stack.setStackSize(stackSize);
        return stack;
    }

    /**
     * Ist der Itemtyp und die Größe und der Schaden des gegebenen ItemStacks identisch mit diesem Itemstack.
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
        return String.format("ItemStack {%s}", getName());
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
