package projektkurs.entity;

import java.awt.image.BufferedImage;

import projektkurs.item.ItemStack;
import projektkurs.lib.Strings;
import projektkurs.util.SaveData;

public class EntityItem extends Entity {

	private ItemStack stack;

	public EntityItem() {
		super();
	}

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

	@Override
	public void load(SaveData data) {
		super.load(data);
		stack = ItemStack.load(data.getSaveData(Strings.ENTITY_ITEM));
	}

	@Override
	public void write(SaveData data) {
		super.write(data);
		data.set(Strings.ENTITY_ITEM, stack.write());
	}
}
