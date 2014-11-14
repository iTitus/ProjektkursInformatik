package projektkurs.item;

import java.awt.image.BufferedImage;

import projektkurs.lib.Items;
import projektkurs.lib.Strings;
import projektkurs.util.SaveData;

public class ItemStack {

	public static ItemStack load(SaveData data) {
		if (data == null)
			return null;
		ItemStack stack = new ItemStack(Items.MAPPINGS.get(data
				.getString(Strings.STACK_ITEM)));
		stack.setStackSize(data.getInteger(Strings.STACK_SIZE));
		stack.setDamage(data.getInteger(Strings.STACK_DAMAGE));
		return stack;
	}

	private AbstractItem item;

	private int stackSize, damage;

	/**
	 * @param item
	 */
	public ItemStack(AbstractItem item) {
		this(item, 1);
	}

	/**
	 * @param item
	 * @param stackSize
	 */
	public ItemStack(AbstractItem item, int stackSize) {
		this(item, stackSize, 0);
	}

	/**
	 * @param item
	 * @param stackSize
	 * @param damage
	 */
	public ItemStack(AbstractItem item, int stackSize, int damage) {
		this.item = item;
		this.stackSize = stackSize;
		this.damage = damage;
	}

	/**
	 * Beschädigt den Stack um by
	 *
	 * @param by
	 */
	public void damage(int by) {
		damage += by;
	}

	public void decrStackSize(int by) {
		stackSize -= by;
	}

	public int getDamage() {
		return damage;
	}

	/**
	 * @return
	 */
	public BufferedImage getImage() {
		return item.getImage(this);
	}

	public AbstractItem getItem() {
		return item;
	}

	public String getName() {
		return String.format("%d x %s:%d", stackSize, item.toString(), damage);
	}

	public int getStackSize() {
		return stackSize;
	}

	public void incrStackSize(int by) {
		stackSize += by;
	}

	/**
	 * Ignoriert stacksize
	 *
	 * @param other
	 * @return
	 */
	public boolean itemAndDamageEquals(ItemStack other) {
		return other.damage == damage
				&& ((other.item == null && item == null) || (other.item != null
						&& item != null && other.item.equals(item)));
	}

	/**
	 * Ignoriert damage
	 *
	 * @param other
	 * @return
	 */
	public boolean itemAndStackSizeEquals(ItemStack other) {
		return other.stackSize == stackSize
				&& ((other.item == null && item == null) || (other.item != null
						&& item != null && other.item.equals(item)));
	}

	/**
	 * Ignore stacksize and damage
	 *
	 * @param other
	 * @return
	 */
	public boolean itemEquals(ItemStack other) {
		return (other.item == null && item == null)
				|| (other.item != null && item != null && other.item
						.equals(item));
	}

	/**
	 * Setzt den Damage
	 * 
	 * @param damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setItem(AbstractItem item) {
		this.item = item;
	}

	public void setStackSize(int stackSize) {
		this.stackSize = stackSize;
	}

	/**
	 * Streng
	 *
	 * @param other
	 * @return
	 */
	public boolean stackEquals(ItemStack other) {
		return other.stackSize == stackSize
				&& other.damage == damage
				&& ((other.item == null && item == null) || (other.item != null
						&& item != null && other.item.equals(item)));
	}

	@Override
	public String toString() {
		return String.format("ItemStack {%s}", getName());
	}

	public SaveData write() {
		SaveData data = new SaveData();
		data.set(Strings.STACK_ITEM, Items.BACK_MAPPINGS.get(item));
		data.set(Strings.STACK_SIZE, stackSize);
		data.set(Strings.STACK_DAMAGE, damage);
		return data;
	}

}
