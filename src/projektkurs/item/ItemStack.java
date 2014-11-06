package projektkurs.item;

import java.awt.image.BufferedImage;

public class ItemStack {

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

}
