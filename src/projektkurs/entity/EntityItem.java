package projektkurs.entity;

import projektkurs.item.ItemStack;

public class EntityItem extends Entity {

	private ItemStack stack;

	public EntityItem(int posX, int posY, ItemStack stack) {
		super(posX, posY, stack.getItem().getImage());
		this.stack = stack;

	}

	public ItemStack getStack() {
		return stack;
	}

}
