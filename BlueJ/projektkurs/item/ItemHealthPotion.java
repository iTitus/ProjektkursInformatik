package projektkurs.item;

import java.awt.event.MouseEvent;

import projektkurs.entity.Entity;
import projektkurs.entity.EntityLiving;
import projektkurs.lib.Images;

/**
 * Ein Gesundheitstrank-Item.
 */
public class ItemHealthPotion extends BaseItem {

    /**
     * Gesundheit, die regeneriert wird.
     */
    private final int health;

    /**
     * Kosntruktor.
     *
     * @param health
     *            Gesundheit, die regeneriert wird
     */
    public ItemHealthPotion(int health) {
        super("potion.health", Images.healthpotion);
        this.health = health;
    }

    @Override
    public void onLeftClick(Entity e, ItemStack stack, int screenX, int screenY, MouseEvent event) {
        if (e instanceof EntityLiving) {
            EntityLiving l = (EntityLiving) e;
            if (l.getHealth() < l.getMaxHealth()) {
                l.heal(health);
                stack.decrStackSize(1);
            }
        }
    }
}
