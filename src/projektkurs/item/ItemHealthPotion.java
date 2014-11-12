package projektkurs.item;

import projektkurs.entity.Entity;
import projektkurs.entity.EntityLiving;
import projektkurs.lib.Images;

public class ItemHealthPotion extends BaseItem {

	private int health;

	public ItemHealthPotion(int health) {
		super("potion.health", Images.healthpotion);
		this.health = health;
	}

	@Override
	public void onLeftClick(Entity e, ItemStack stack) {
		((EntityLiving) e).damage(-health);
		stack.changeStackSize(-1);
	}
}
