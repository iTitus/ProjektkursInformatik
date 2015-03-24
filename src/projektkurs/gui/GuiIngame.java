package projektkurs.gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.List;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.gui.element.IPlayerInventoryElementListener;
import projektkurs.gui.element.InventoryElement;
import projektkurs.gui.element.PlayerInventoryElement;
import projektkurs.lib.Integers;
import projektkurs.lib.KeyBindings;
import projektkurs.raster.AbstractRaster;
import projektkurs.util.MathUtil;

/**
 * Das Ingame-GUI.
 */
public class GuiIngame extends Gui implements IPlayerInventoryElementListener {

    @Override
    public void addTooltip(int mouseX, int mouseY, List<String> tooltip) {
        super.addTooltip(mouseX, mouseY, tooltip);

        int rX = MathUtil.floorDiv(mouseX - Integers.WINDOW_HUD_X, Integers.RASTER_SIZE) + Main.getRenderHelper().getSightX();
        int rY = MathUtil.floorDiv(mouseY - Integers.WINDOW_HUD_Y, Integers.RASTER_SIZE) + Main.getRenderHelper().getSightY();

        List<Entity> list = Main.getLevel().getMap().getEntitiesAt(rX, rY);
        if (list != null) {
            for (Entity e : list) {
                if (e != null && !e.shouldDeSpawn()) {
                    e.addTooltip(Main.getLevel().getMap(), rX, rY, tooltip);
                }
            }
        }

        AbstractRaster r = Main.getLevel().getMap().getRasterAt(rX, rY);
        if (r != null && Main.getRenderHelper().isInSight(rX, rY)) {
            r.addTooltip(Main.getLevel().getMap(), rX, rY, tooltip);
        }

    }

    @Override
    public void initGui() {
        super.initGui();
        addElement(new PlayerInventoryElement(MathUtil.floorDiv(Integers.windowX, 2), Integers.windowY - MathUtil.floorDiv(Integers.SLOT_SIZE, 2), 0, this, Main.getPlayer().getInventory()));
    }

    @Override
    public void onKeyTyped(char keyChar, KeyEvent e) {
        if (e.getKeyCode() == KeyBindings.KEY_MENU) {
            Main.openGui(new GuiIngameMenu());
        } else if (e.getKeyCode() == KeyBindings.KEY_CONSOLE) {
            Main.openGui(new GuiConsole());
        } else if (e.getKeyCode() == KeyBindings.KEY_INVENTORY) {
            Main.openGui(new GuiPlayerInventory());
        }
    }

    @Override
    public void onLeftClick(int screenX, int screenY, MouseEvent e) {
        super.onLeftClick(screenX, screenY, e);

        int rX = MathUtil.floorDiv(screenX - Integers.WINDOW_HUD_X, Integers.RASTER_SIZE) + Main.getRenderHelper().getSightX();
        int rY = MathUtil.floorDiv(screenY - Integers.WINDOW_HUD_Y, Integers.RASTER_SIZE) + Main.getRenderHelper().getSightY();

        AbstractRaster r = Main.getLevel().getMap().getRasterAt(rX, rY);
        if (r != null && Main.getRenderHelper().isInSight(rX, rY)) {
            r.onLeftClick(Main.getLevel().getMap(), rX, rY, e);
        }

        Main.getPlayer().onLeftClick(screenX, screenY, e);
    }

    @Override
    public void onRightClick(int screenX, int screenY, MouseEvent e) {
        super.onRightClick(screenX, screenY, e);

        int rX = MathUtil.floorDiv(screenY - Integers.WINDOW_HUD_X, Integers.RASTER_SIZE) + Main.getRenderHelper().getSightX();
        int rY = MathUtil.floorDiv(screenX - Integers.WINDOW_HUD_Y, Integers.RASTER_SIZE) + Main.getRenderHelper().getSightY();

        AbstractRaster r = Main.getLevel().getMap().getRasterAt(rX, rY);
        if (r != null && Main.getRenderHelper().isInSight(rX, rY)) {
            r.onRightClick(Main.getLevel().getMap(), rX, rY, e);
        }

        Main.getPlayer().onRightClick(screenX, screenY, e);
    }

    @Override
    public void onSlotChanged(PlayerInventoryElement invE, MouseWheelEvent e) {
        // NO-OP
    }

    @Override
    public void onSlotLeftClick(int slotIndex, InventoryElement invE, MouseEvent e) {
        // NO-OP
    }

    @Override
    public void onSlotRightClick(int slotIndex, InventoryElement invE, MouseEvent e) {
        // NO-OP
    }

}
