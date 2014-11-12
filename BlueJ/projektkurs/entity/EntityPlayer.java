package projektkurs.entity;

import java.awt.image.BufferedImage;

import projektkurs.Main;
import projektkurs.inventory.PlayerInventory;
import projektkurs.lib.Integers;

/**
 * Der Spieler
 */
public class EntityPlayer extends EntityLiving {

	private final PlayerInventory inventar;

	/**
	 * Konstruktor für Figuren
	 *
	 * @param posX
	 * @param posY
	 * @param image
	 */
	public EntityPlayer(int posX, int posY, BufferedImage image) {
		super(posX, posY, image, 42);
		inventar = new PlayerInventory(Integers.INVENTARGROESSE, 0);
	}

	/**
	 * Das Inventar
	 *
	 * @return
	 */
	public PlayerInventory getInventory() {
		return inventar;
	}

	@Override
	public void moveBy(int dx, int dy) {
		if ((dx != 0 || dy != 0) && canMoveTo(posX + dx, posY + dy)) {
			posX += dx;
			posY += dy;
			Main.getRenderHelper().moveSight(dx, dy);
		}
	}

	@Override
	public void onCollideWith(Entity e) {
		super.onCollideWith(e);
		if (e instanceof EntityItem) {
			EntityItem item = (EntityItem) e;
			if (inventar.addItemStack(item.getStack())) {
				item.setDead();
			}
		}
	}
}
