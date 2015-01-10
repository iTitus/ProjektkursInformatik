package projektkurs.raster;

import java.awt.Graphics2D;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityPlayer;
import projektkurs.raster.extra.ExtraInformation;
import projektkurs.raster.extra.ExtraInformationDoor;
import projektkurs.raster.extra.IHasExtraInformation;
import projektkurs.util.Direction;
import projektkurs.util.RenderUtil;

/**
 * Eine Tür.
 */
public class DoorRaster extends AbstractRaster implements IHasExtraInformation {

    /**
     * Konstruktor.
     *
     * @param id
     *            ID
     */
    public DoorRaster(byte id) {
        super(id, "door");
    }

    @Override
    public boolean canWalkOnFromDirection(int x, int y, Entity entity, Direction dir) {
        ExtraInformation extra = Main.getLevel().getMap().getExtraInformationAt(x, y);
        if (extra instanceof ExtraInformationDoor) {
            return ((ExtraInformationDoor) extra).isOpenFromDirection(dir);
        }
        return false;
    }

    @Override
    public ExtraInformation createExtraInformation() {
        return new ExtraInformationDoor();
    }

    @Override
    public void onCollideWith(int x, int y, Entity entity) {
        if (entity instanceof EntityPlayer) {
            ExtraInformation extra = Main.getLevel().getMap().getExtraInformationAt(x, y);
            if (extra instanceof ExtraInformationDoor) {
                ((ExtraInformationDoor) extra).tryOpen(Main.getPlayer().getInventory().getSelectedItemStack());
            }
        }
    }

    @Override
    public void render(Graphics2D g, int x, int y) {
        ExtraInformation extra = Main.getLevel().getMap().getExtraInformationAt(x, y);
        if (extra instanceof ExtraInformationDoor) {
            RenderUtil.drawDefaultRaster(g, ((ExtraInformationDoor) extra).getImage(), x, y);
        }
    }

    @Override
    public void renderCutScene(Graphics2D g, int x, int y) {
        ExtraInformation extra = Main.getLevel().getMap().getExtraInformationAt(x, y);
        if (extra instanceof ExtraInformationDoor) {
            RenderUtil.drawCutSceneRaster(g, ((ExtraInformationDoor) extra).getImage(), x, y);
        }

    }
}
