package projektkurs.gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import projektkurs.Main;
import projektkurs.gui.element.IPlayerInventoryElementListener;
import projektkurs.gui.element.InventoryElement;
import projektkurs.gui.element.PlayerInventoryElement;
import projektkurs.lib.Integers;
import projektkurs.lib.KeyBindings;
import projektkurs.lib.Scripts;
import projektkurs.raster.AbstractRaster;
import projektkurs.util.MathUtil;

/**
 * Das Ingame-GUI.
 */
public class GuiIngame extends Gui implements IPlayerInventoryElementListener {

    @Override
    public void initGui() {
        super.initGui();
        guiElements.add(new PlayerInventoryElement(MathUtil.floorDiv(Integers.windowX, 2), Integers.windowY - MathUtil.floorDiv(Integers.SLOT_SIZE, 2), 0,
                this, Main.getPlayer().getInventory()));
    }

    @Override
    public void onKeyTyped(char keyChar, KeyEvent e) {
        if (keyChar == KeyBindings.KEY_OPTION) {
            Main.openGui(new GuiOption());
        } else if (keyChar == KeyBindings.KEY_CONSOLE) {
            Main.openGui(new GuiConsole());
        }
    }

    @Override
    public void onLeftClick(int screenX, int screenY, MouseEvent e) {
        super.onLeftClick(screenX, screenY, e);

        int rX = MathUtil.floorDiv(screenX - Integers.WINDOW_HUD_X, Integers.RASTER_SIZE) + Main.getRenderHelper().getSightX();
        int rY = MathUtil.floorDiv(screenY - Integers.WINDOW_HUD_Y, Integers.RASTER_SIZE) + Main.getRenderHelper().getSightY();

        AbstractRaster r = Main.getLevel().getCurrMap().getRasterAt(rX, rY);
        if (r != null && Main.getRenderHelper().isInSight(rX, rY)) {
            r.onLeftClick(rX, rY, e);
        }

        Main.getPlayer().onLeftClick(screenX, screenY, e);
    }

    @Override
    public void onRightClick(int screenX, int screenY, MouseEvent e) {
        super.onRightClick(screenX, screenY, e);

        int rX = MathUtil.floorDiv(screenY - Integers.WINDOW_HUD_X, Integers.RASTER_SIZE) + Main.getRenderHelper().getSightX();
        int rY = MathUtil.floorDiv(screenX - Integers.WINDOW_HUD_Y, Integers.RASTER_SIZE) + Main.getRenderHelper().getSightY();

        AbstractRaster r = Main.getLevel().getCurrMap().getRasterAt(rX, rY);
        if (r != null && Main.getRenderHelper().isInSight(rX, rY)) {
            r.onRightClick(rX, rY, e);
        }

        Main.getPlayer().onRightClick(screenX, screenY, e);

        if (e.isShiftDown()) {
            Scripts.cutSceneOne();
        }
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
