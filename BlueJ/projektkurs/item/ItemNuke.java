package projektkurs.item;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityLiving;
import projektkurs.entity.EntityPlayer;
import projektkurs.lib.Images;

public class ItemNuke extends BaseItem {

	public ItemNuke() {
		super("nuke", Images.nuke);
	}

	@Override
	public void onLeftClick(Entity e, ItemStack stack) {
		if (stack.getStackSize() > 0) {
			for (Entity toKill : Main.getLevel().getCurrMap()
					.getEntitiesInRect(e.getPosX() - 2, e.getPosY() - 2, 5, 5)) {
				if (!(toKill instanceof EntityPlayer)) {
					if (toKill instanceof EntityLiving)
						((EntityLiving) toKill).damage(((EntityLiving) toKill)
								.getMaxHealth() + 1);
					else
						toKill.setDead();
				}
			}
			stack.setStackSize(stack.getStackSize() - 1);
		}
	}
}
