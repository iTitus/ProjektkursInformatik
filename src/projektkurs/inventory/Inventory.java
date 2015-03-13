package projektkurs.inventory;

import projektkurs.item.ItemStack;
import projektkurs.lib.Strings;
import projektkurs.util.MathUtil;
import projektkurs.util.SaveData;

/**
 * Ein Inventar.
 */
public class Inventory {

    /**
     * Laedt ein Inventory aus der gegebenen SaveData.
     *
     * @param data
     *            SaveData
     * @return Inventory
     */
    public static Inventory load(SaveData data) {
        Inventory inv = new Inventory();

        inv.stacks = new ItemStack[data.getInteger(Strings.INV_SIZE)];

        for (int i = 0; i < inv.stacks.length; i++) {
            inv.setItemStackInSlot(i, ItemStack.load(data.getSaveData(Strings.INV_SLOT + i)));
        }

        return inv;
    }

    /**
     * Alle ItemStack in diesem Inventory.
     */
    protected ItemStack[] stacks;

    /**
     * Konstruktor.
     *
     * @param size
     *            Groesse
     */
    public Inventory(int size) {
        stacks = new ItemStack[size];
    }

    /**
     * Konstruktor.
     */
    protected Inventory() {
    }

    /**
     * Fuegt diesem Inventory einen neuen ItemStack hinzu.
     *
     * @param newStack
     *            ist der neue ItemStack
     * @return true, wenn es geklappt hat; false, wenn nicht.
     */
    public boolean addItemStack(ItemStack newStack) {
        if (newStack != null) {
            ItemStack stack;
            for (int i = 0; i < stacks.length; i++) {
                stack = getItemStackAt(i);
                if (stack == null) {
                    setItemStackInSlot(i, newStack);
                    return true;
                } else if (stack.itemAndDamageEquals(newStack)) {
                    incrStackSize(i, newStack.getStackSize());
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Ist der gegebene ItemStack im Inventar enthalten? - Streng.
     *
     * @param stack
     *            zu suchender ItemStack
     * @return true, wenn ja; false, wenn nicht
     */
    public boolean contains(ItemStack stack) {
        if (stack != null) {
            ItemStack item;
            for (ItemStack stack2 : stacks) {
                item = stack2;
                if (item != null && item.stackEquals(stack)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Ist der gegebene ItemStack im Inventar enthalten? - Ignoriert die StackSize.
     *
     * @param stack
     *            zu suchender ItemStack
     * @return true, wenn ja; false, wenn nicht
     */
    public boolean containsIgnoreStackSize(ItemStack stack) {
        if (stack != null) {
            ItemStack item;
            for (ItemStack stack2 : stacks) {
                item = stack2;
                if (item != null && item.itemAndDamageEquals(stack)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verringert die StackSize eines ItemStacks an der gegebenen Stelle um die gegebene Anzahl und entfernt ihn, wenn die StackSize dann negativ ist.
     *
     * @param index
     *            Index, an dem verringert werden soll
     * @param stackSize
     *            StackSize, um die verringert werden soll.
     * @return true, wenn es geklappt hat; false, wenn nicht.
     */
    public boolean decrStackSize(int index, int stackSize) {

        ItemStack stack = getItemStackAt(index);

        if (stack != null) {
            stack.decrStackSize(stackSize);
            if (stack.getStackSize() <= 0) {
                removeItemStack(index);
            }
            return true;
        }

        return false;

    }

    /**
     * Alle ItemStacks in diesem Inventar.
     *
     * @return ItemStacks
     */
    public ItemStack[] getItems() {
        return stacks;
    }

    /**
     * Returnt den ItemStack am gegebenen Index.
     *
     * @param index
     *            Index
     * @return ItemStack an der Stelle Index
     */
    public ItemStack getItemStackAt(int index) {
        if (MathUtil.isInArray(index, stacks.length)) {
            return stacks[index];
        }
        return null;
    }

    /**
     * Zahl aller Items im Inventar.
     *
     * @return Zahl.
     */
    public int getNumberOfItemsInInventory() {
        int i = 0;
        for (int j = 0; j < stacks.length; j++) {
            if (getItemStackAt(j) != null) {
                i += getItemStackAt(i).getStackSize();
            }
        }
        return i;
    }

    /**
     * Zahl aller Items im Inventar.
     *
     * @param stack
     *            ItemStack
     * @return Zahl.
     */
    public int getNumberOfItemsInInventory(ItemStack stack) {
        int i = 0;
        ItemStack stack1;
        for (int j = 0; j < stacks.length; j++) {
            stack1 = getItemStackAt(j);
            if (stack1 != null && stack1.itemAndDamageEquals(stack)) {
                i += stack1.getStackSize();
            }
        }
        return i;
    }

    /**
     * Zahl aller ItemStacks im Inventar.
     *
     * @return Zahl.
     */
    public int getNumberOfItemStacksInInventory() {
        int i = 0;
        for (int j = 0; j < stacks.length; j++) {
            if (getItemStackAt(j) != null) {
                i++;
            }
        }
        return i;
    }

    /**
     * Groesse dieses Inventars.
     *
     * @return Groesse
     */
    public int getSize() {
        return stacks.length;
    }

    /**
     * Erhoeht die StackSize eines ItemStacks am gegebenen Index um die gegebene Anzahl.
     *
     * @param index
     *            Index, an dem erhoeht werden soll
     * @param stackSize
     *            StackSize, um die erhoeht werden soll
     * @return true, wenn es geklappt hat; false, wenn nicht.
     */
    public boolean incrStackSize(int index, int stackSize) {

        ItemStack stack = getItemStackAt(index);

        if (stack != null) {
            stack.incrStackSize(stackSize);
            return true;
        }

        return false;

    }

    /**
     * Ist das Inventar leer.
     *
     * @return true, wenn es geklappt hat; false, wenn nicht.
     */
    public boolean isInventoryEmpty() {
        for (int i = 0; i < stacks.length; i++) {
            if (getItemStackAt(i) != null) {
                return false;
            }
        }
        return true;

    }

    /**
     * Ist das Inventar voll mit ItemStacks.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isInventoryFull() {
        for (int i = 0; i < stacks.length; i++) {
            if (getItemStackAt(i) == null) {
                return false;
            }
        }
        return true;

    }

    /**
     * Entfernt einen ItemStack am gegebenen Index aus dem Inventar.
     *
     * @param index
     *            Index
     * @return true, wenn es geklappt hat; false, wenn nicht.
     */
    public boolean removeItemStack(int index) {
        if (getItemStackAt(index) != null) {
            setItemStackInSlot(index, null);
            return true;
        }

        return false;
    }

    /**
     * Entfernt den ItemStack, der an dem gegebenen ItemStack entspricht - Streng.
     *
     * @param stackToRemove
     *            zu entfernender ItemStack
     * @return true, wenn es geklappt hat; false, wenn nicht.
     */
    public boolean removeItemStack(ItemStack stackToRemove) {
        if (stackToRemove != null) {
            ItemStack stack;
            for (int i = 0; i < stacks.length; i++) {
                stack = getItemStackAt(i);
                if (stack != null && stack.stackEquals(stackToRemove)) {
                    setItemStackInSlot(i, null);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Entfernt den ItemStack, der an dem gegebenen ItemStack entspricht - Ignoriert StackSize.
     *
     * @param stackToRemove
     *            zu entfernender ItemStack.
     * @return true, wenn es geklappt hat; false, wenn nicht.
     */
    public boolean removeItemStackIgnoreStackSize(ItemStack stackToRemove) {
        if (stackToRemove != null) {
            ItemStack stack;
            for (int i = 0; i < stacks.length; i++) {
                stack = getItemStackAt(i);
                if (stack != null && stack.itemAndDamageEquals(stackToRemove)) {
                    setItemStackInSlot(i, null);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Setzt den gegebenen ItemStack am gegebenen Index in dieses Inventar.
     *
     * @param index
     *            Index
     * @param stack
     *            ItemStack
     * @return true, wenn es geklappt hat; false, wenn nicht.
     */
    public boolean setItemStackInSlot(int index, ItemStack stack) {
        if (MathUtil.isInArray(index, stacks.length)) {
            stacks[index] = stack;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {

        String s = "Inventory";

        if (getSize() > 0) {
            ItemStack stack;
            s += "[";
            stack = getItemStackAt(0);
            s += stack != null ? stack.getName() : "";

            if (getSize() > 1) {
                for (int i = 1; i < stacks.length; i++) {
                    stack = getItemStackAt(i);
                    if (stack != null) {
                        s += ", " + stack.getName();
                    }
                }
            }

            s += "]";
        } else {
            s += " - EMPTY";
        }

        return s;
    }

    /**
     * Speichert dieses Inventar.
     *
     * @return SaveData
     */
    public SaveData write() {
        SaveData data = new SaveData();

        data.set(Strings.INV_SIZE, getSize());

        ItemStack stack;
        for (int i = 0; i < stacks.length; i++) {
            stack = getItemStackAt(i);
            if (stack != null) {
                data.set(Strings.INV_SLOT + i, stack.write());
            }
        }

        return data;
    }

}
