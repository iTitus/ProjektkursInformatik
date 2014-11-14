package projektkurs.entity;

import java.awt.image.BufferedImage;

import projektkurs.Main;
import projektkurs.inventory.PlayerInventory;
import projektkurs.item.ItemStack;
import projektkurs.lib.Integers;
import projektkurs.lib.Strings;
import projektkurs.story.script.Scripts;
import projektkurs.util.SaveData;

/**
 * Der Spieler
 */
public class EntityPlayer extends EntityLiving {

	private PlayerInventory inventar;

	public EntityPlayer() {
		super();
	}

	/**
	 * Konstruktor für einen Spieler
	 *
	 * @param posX
	 * @param posY
	 * @param image
	 */
	public EntityPlayer(int posX, int posY, BufferedImage image) {
		super(posX, posY, image, 500);
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
	public void load(SaveData data) {
		super.load(data);
		inventar = PlayerInventory.load(data.getSaveData(Strings.ENTITY_INV));
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

	public void onLeftClick(int screenX, int screenY) {
		ItemStack stack = inventar.getSelectedItemStack();
		if (stack != null) {
			stack.getItem().onLeftClick(this, stack, screenX, screenY);
			if (stack.getStackSize() <= 0)
				inventar.removeItemStack(inventar.getSelectedIndex());
		}
	}

	public void onRightClick(int screenX, int screenY) {
		ItemStack stack = inventar.getSelectedItemStack();
		if (stack != null) {
			stack.getItem().onRightClick(this, stack, screenX, screenY);
			if (stack.getStackSize() <= 0)
				inventar.removeItemStack(inventar.getSelectedIndex());
		}
	}

	@Override
	public void setDead() {
		super.setDead();
		Scripts.loose();
	}

	@Override
	public void write(SaveData data) {
		super.write(data);
		data.set(Strings.ENTITY_INV, inventar.write());
	}

}
