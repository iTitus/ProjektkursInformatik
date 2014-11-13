package projektkurs.item;

import java.util.Random;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityLiving;
import projektkurs.entity.EntityPlayer;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.Raster;
import projektkurs.lib.Sounds;

public class ItemNuke extends BaseItem {

	public ItemNuke() {
		super("nuke", Images.nuke);
	}

	@Override
	public void onLeftClick(Entity e, ItemStack stack) {
		if (stack.getStackSize() > 0) {
			Random rand = new Random();
			Sounds.boom.playFromStart();

			for (Entity toKill : Main
					.getLevel()
					.getCurrMap()
					.getEntitiesInRect(e.getPosX() - Integers.NUKE_RADIUS,
							e.getPosY() - Integers.NUKE_RADIUS,
							2 * Integers.NUKE_RADIUS + 1,
							2 * Integers.NUKE_RADIUS + 1)) {
				if (!(toKill instanceof EntityPlayer)) {
					if (toKill instanceof EntityLiving)
						((EntityLiving) toKill).damage(((EntityLiving) toKill)
								.getMaxHealth() + 1);
					else
						toKill.setDead();
				}
			}
			stack.changeStackSize(-1);

			int centerX = (e.getFacing().getOffsetX() * Integers.NUKE_RADIUS)
					+ e.getPosX() + e.getFacing().getOffsetX();
			int centerY = (e.getFacing().getOffsetY() * Integers.NUKE_RADIUS)
					+ e.getPosY() + e.getFacing().getOffsetY();

			for (int x = centerX - Integers.NUKE_RADIUS; x <= centerX
					+ Integers.NUKE_RADIUS; x++) {
				for (int y = centerY - Integers.NUKE_RADIUS; y <= centerY
						+ Integers.NUKE_RADIUS; y++) {
					if (rand.nextInt(2) == 0) {
						Main.getLevel().getCurrMap()
								.setRasterAt(x, y, Raster.fire);
					} else {
						Main.getLevel().getCurrMap()
								.setRasterAt(x, y, Raster.destroyedRaster);
					}

				}
			}
		}
	}
}
