package projektkurs.world.raster;

import java.awt.Graphics2D;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityPlayer;
import projektkurs.util.Direction;
import projektkurs.util.RenderUtil;
import projektkurs.world.raster.extra.ExtraInformation;
import projektkurs.world.raster.extra.ExtraInformationDoor;
import projektkurs.world.raster.extra.IHasExtraInformation;

public class DoorRaster extends AbstractRaster implements IHasExtraInformation {

    @Override
    public boolean canWalkOnFromDirection(int x, int y, Entity entity, Direction dir) {
        if (entity instanceof EntityPlayer) {
            ExtraInformation extra = Main.getLevel().getCurrMap().getExtraInformationAt(x, y);
            if (extra instanceof ExtraInformationDoor) {
                ExtraInformationDoor door = (ExtraInformationDoor) extra;
                door.tryOpen(Main.getPlayer().getInventory().getSelectedItemStack());
                return door.getIsOpen(dir);
            }
        }
        return false;
    }

    @Override
    public ExtraInformation createExtraInformation() {
        return new ExtraInformationDoor();
    }

    @Override
    public void render(Graphics2D g, int x, int y) {
        ExtraInformation extra = Main.getLevel().getCurrMap().getExtraInformationAt(x, y);
        if (extra instanceof ExtraInformationDoor) {
            RenderUtil.drawDefaultRaster(g, ((ExtraInformationDoor) extra).getImage(), x, y);
        }
    }

    @Override
    public void renderCutScene(Graphics2D g, int x, int y) {
        ExtraInformation extra = Main.getLevel().getCurrMap().getExtraInformationAt(x, y);
        if (extra instanceof ExtraInformationDoor) {
            RenderUtil.drawCutSceneRaster(g, ((ExtraInformationDoor) extra).getImage(), x, y);
        }

    }
}
