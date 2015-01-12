package projektkurs.item;

import java.awt.event.MouseEvent;
import java.util.Random;

import projektkurs.entity.Entity;
import projektkurs.entity.EntityLiving;
import projektkurs.entity.EntityPlayer;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.Raster;
import projektkurs.lib.Sounds;
import projektkurs.raster.FireRaster;
import projektkurs.world.Spielfeld;

/**
 * Ein Atombomben-Item.
 */
public class ItemNuke extends BaseItem {

    /**
     * Konstruktor.
     *
     * @param id
     *            ID
     */
    public ItemNuke(int id) {
        super(id, "nuke", Images.nuke);
    }

    @Override
    public void onLeftClick(Spielfeld map, Entity e, ItemStack stack, int screenX, int screenY, MouseEvent event) {
        if (stack.getStackSize() > 0) {
            Random rand = new Random();
            Sounds.explosion.playFromStart();

            stack.decrStackSize(1);

            int centerX = e.getFacing().getOffsetX() * Integers.NUKE_RADIUS + e.getPosX() + e.getFacing().getOffsetX();
            int centerY = e.getFacing().getOffsetY() * Integers.NUKE_RADIUS + e.getPosY() + e.getFacing().getOffsetY();

            for (Entity toKill : map.getEntitiesInRect(centerX - Integers.NUKE_RADIUS, centerY - Integers.NUKE_RADIUS, 2 * Integers.NUKE_RADIUS + 1, 2 * Integers.NUKE_RADIUS + 1)) {
                if (!(toKill instanceof EntityPlayer)) {
                    if (toKill instanceof EntityLiving) {
                        ((EntityLiving) toKill).damage(((EntityLiving) toKill).getMaxHealth() + 1);
                    } else {
                        toKill.setDead();
                    }
                }
            }

            for (int y = centerY - Integers.NUKE_RADIUS; y <= centerY + Integers.NUKE_RADIUS; y++) {
                for (int x = centerX - Integers.NUKE_RADIUS; x <= centerX + Integers.NUKE_RADIUS; x++) {
                    if (rand.nextInt(2) == 0) {
                        map.setRasterAt(x, y, Raster.fire);
                    } else {
                        if (!(map.getRasterAt(x, y) instanceof FireRaster)) {
                            map.setRasterAt(x, y, Raster.destroyedRaster);
                        }
                    }

                }
            }
        }
    }
}
