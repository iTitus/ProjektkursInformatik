package projektkurs.item;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityLiving;
import projektkurs.entity.EntityPlayer;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.Sounds;
import projektkurs.world.raster.Raster;

public class ItemNuke extends BaseItem {

	public ItemNuke() {
		super("nuke", Images.nuke);
	}

	@Override
	public void onLeftClick(Entity e, ItemStack stack) {
		if (stack.getStackSize() > 0) {

			Sounds.boom.playFromStart();

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

			for (int x = e.getPosX() - Integers.NUKE_RADIUS; x <= e.getPosX()
					+ Integers.NUKE_RADIUS; x++) {
				for (int y = e.getPosY() - Integers.NUKE_RADIUS; y <= e
						.getPosY() + Integers.NUKE_RADIUS; y++) {
					// AbstractRaster r = Main.getLevel().getCurrMap()
					// .getRasterAt(x, y);
					// if (r.canWalkOnFromDirection(x, y, e, Direction.UNKNOWN)
					// && !(r instanceof IHasExtraInformation))
					Main.getLevel().getCurrMap()
							.setRasterAt(x, y, Raster.destroyedRaster);
				}
			}
		}
	}
}
