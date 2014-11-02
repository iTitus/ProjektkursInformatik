package projektkurs.entity;

import java.awt.image.BufferedImage;

import projektkurs.item.ItemStack;

public class EntityItem extends Entity {

	private ItemStack stack;

	public EntityItem(int posX, int posY, ItemStack stack) {
		super(posX, posY, stack.getItem().getImage(stack));
		this.stack = stack;
	}

	@Override
	public BufferedImage getImage() {
		return stack.getItem().getImage(stack);
	}

	public ItemStack getStack() {
		return stack;
	}
}
