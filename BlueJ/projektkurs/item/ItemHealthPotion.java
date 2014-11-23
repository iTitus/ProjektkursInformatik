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
   * Konstruktor.
   */
  public ItemHealthPotion() {
    super("potion.health", Images.healthpotion);
  }

  @Override
  public void onLeftClick(Entity e, ItemStack stack, int screenX, int screenY, MouseEvent event) {
    if (e instanceof EntityLiving) {
      EntityLiving l = (EntityLiving) e;
      if (l.getHealth() < l.getMaxHealth()) {
        l.heal(stack.getDamage());
        stack.decrStackSize(1);
      }
    }
  }
}
