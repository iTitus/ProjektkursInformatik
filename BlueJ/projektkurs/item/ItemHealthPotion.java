package projektkurs.item;

import java.awt.event.MouseEvent;

import projektkurs.entity.Entity;
import projektkurs.entity.EntityLiving;
import projektkurs.lib.Sprites;
import projektkurs.world.Spielfeld;

/**
 * Ein Gesundheitstrank-Item.
 */
public class ItemHealthPotion extends BaseItem {

    /**
     * Konstruktor.
     *
     * @param id
     *            ID
     */
    public ItemHealthPotion(int id) {
        super(id, "potion.health", Sprites.healthPotion);
    }

    @Override
    public void onLeftClick(Spielfeld map, Entity e, ItemStack stack, int screenX, int screenY, MouseEvent event) {
        if (e instanceof EntityLiving) {
            EntityLiving l = (EntityLiving) e;
            if (stack.getDamage() < 0 && l.getHealth() > 0 || stack.getDamage() > 0 && l.getHealth() < l.getMaxHealth()) {
                l.heal(stack.getDamage());
                stack.decrStackSize(1);
            }
        }
    }
}
