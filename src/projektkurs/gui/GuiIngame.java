package projektkurs.gui;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import projektkurs.Main;
import projektkurs.inventory.PlayerInventory;
import projektkurs.item.ItemStack;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.KeyBindings;
import projektkurs.lib.Scripts;
import projektkurs.raster.AbstractRaster;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

/**
 * Das Ingame-GUI.
 */
public class GuiIngame extends Gui {

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
    public void onMouseWheelMoved(int by, MouseWheelEvent e) {
        if (by > 0) {
            Main.getPlayer()
                    .getInventory()
                    .setSelectedItemStack(
                            Main.getPlayer().getInventory().getSelectedIndex() >= Main.getPlayer().getInventory().getSize() ? 0 : Main.getPlayer()
                                    .getInventory().getSelectedIndex() + 1);
        } else if (by < 0) {
            Main.getPlayer()
                    .getInventory()
                    .setSelectedItemStack(
                            Main.getPlayer().getInventory().getSelectedIndex() <= 0 ? Main.getPlayer().getInventory().getSize() - 1 : Main.getPlayer()
                                    .getInventory().getSelectedIndex() - 1);
        }
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
    public void render(Graphics2D g) {

        PlayerInventory inv = Main.getPlayer().getInventory();
        ItemStack stack = null;
        for (int i1 = 0; i1 < inv.getSize(); i1++) {
            RenderUtil.drawImage(g, i1 == inv.getSelectedIndex() ? Images.slothighlight : Images.slot, i1 * Integers.SLOT_SIZE + inv.getRenderX(),
                    inv.getRenderY());
            stack = inv.getItemStackAt(i1);
            if (stack != null) {
                RenderUtil.drawImage(g, stack.getImage(), i1 * Integers.SLOT_SIZE + inv.getRenderX() + 1, inv.getRenderY() + 1, Integers.SLOT_SIZE - 2,
                        Integers.SLOT_SIZE - 2);
                if (stack.getStackSize() > 1) {
                    g.drawString(stack.getStackSize() + "", i1 * Integers.SLOT_SIZE + inv.getRenderX() + 1, inv.getRenderY() + Integers.STACK_SIZE_OFFSET);
                }
            }
        }

        super.render(g);

    }
}
