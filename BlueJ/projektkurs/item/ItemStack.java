package projektkurs.item;

public class ItemStack {

	private AbstractItem item;
	private int stackSize;

	public ItemStack(AbstractItem item) {
		this(item, 1);
	}

	public ItemStack(AbstractItem item, int stackSize) {
		this.item = item;
		this.stackSize = stackSize;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ItemStack) {
			ItemStack stack = (ItemStack) obj;
			return stack.stackSize == stackSize
					&& ((stack.item == null && item == null) || (stack.item != null
							&& item != null && stack.item.equals(item)));
		}
		return super.equals(obj);
	}

	public AbstractItem getItem() {
		return item;
	}

	public String getName() {
		return String.format("%d x %s", stackSize, item.toString());
	}

	public int getStackSize() {
		return stackSize;
	}

	@Override
	public int hashCode() {
		return stackSize * item.hashCode();
	}

	public void setItem(AbstractItem item) {
		this.item = item;
	}

	public void setStackSize(int stackSize) {
		this.stackSize = stackSize;
	}

	@Override
	public String toString() {
		return String.format("ItemStack {%s}", getName());
	}

}
