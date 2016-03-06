package projektkurs.entity;

import java.awt.event.MouseEvent;

import projektkurs.Main;
import projektkurs.dialog.ITalkable;
import projektkurs.inventory.PlayerInventory;
import projektkurs.io.storage.SaveData;
import projektkurs.item.ItemStack;
import projektkurs.lib.Integers;
import projektkurs.lib.Scripts;
import projektkurs.lib.Strings;
import projektkurs.render.Sprite;
import projektkurs.world.Spielfeld;

/**
 * Der Spieler.
 */
public class EntityPlayer extends EntityLiving {

    /**
     * Das Inventar des Spierlers.
     */
    private PlayerInventory inventar;

    /**
     * Konstruktor.
     * @param map
     * Spielfeld
     */
    public EntityPlayer(Spielfeld map) {
        super(map);
    }

    /**
     * Konstruktor.
     * @param map
     * Spielfeld
     * @param posX
     * X-Koordinate
     * @param posY
     * Y-Koordinate
     * @param sprites
     * Bilder
     */
    public EntityPlayer(Spielfeld map, int posX, int posY, Sprite... sprites) {
        super(map, posX, posY, Integers.PLAYER_HEALTH, sprites);
        inventar = new PlayerInventory(Integers.PLAYER_INVENTORY_SIZE, 0);
    }

    /**
     * Das Inventar des Spielers.
     * @return PlayerInventory
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
        if (e instanceof ITalkable) {
            ITalkable talkable = (ITalkable) e;
            if (talkable.shouldStartDialog()) {
                map.getLevel().getDialogManager().startDialog(talkable.getDialog(), e);
            }
        }
        if (e instanceof EntityItem) {
            EntityItem item = (EntityItem) e;
            if (inventar.addItemStack(item.getStack())) {
                item.setDead();
            }
        }
    }

    /**
     * Wird ausgefuehrt, wenn mit der linken Maustaste auf den Bildschirm geklickt wird.
     * @param screenX
     * X-Bildschirmkoordinate
     * @param screenY
     * Y-Bildschirmkoordinate
     * @param e
     * MouseEvent
     */
    public void onLeftClick(int screenX, int screenY, MouseEvent e) {
        ItemStack stack = inventar.getSelectedItemStack();
        if (stack != null) {
            stack.getItem().onLeftClick(map, this, stack, screenX, screenY, e);
            if (stack.getStackSize() <= 0) {
                inventar.removeItemStack(inventar.getSelectedIndex());
            }
        }
    }

    /**
     * Wird ausgefuehrt, wenn mit der rechten Maustaste auf den Bildschirm geklickt wird.
     * @param screenX
     * X-Bildschirmkoordinate
     * @param screenY
     * Y-Bildschirmkoordinate
     * @param e
     * MouseEvent
     */
    public void onRightClick(int screenX, int screenY, MouseEvent e) {
        ItemStack stack = inventar.getSelectedItemStack();
        if (stack != null) {
            stack.getItem().onRightClick(map, this, stack, screenX, screenY, e);
            if (stack.getStackSize() <= 0) {
                inventar.removeItemStack(inventar.getSelectedIndex());
            }
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
