package projektkurs.raster;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityPlayer;
import projektkurs.gui.GuiInventory;
import projektkurs.lib.Images;
import projektkurs.raster.extra.ExtraInformation;
import projektkurs.raster.extra.ExtraInformationChest;
import projektkurs.raster.extra.IHasExtraInformation;
import projektkurs.util.Direction;

/**
 * Kiste.
 */
public class ChestRaster extends SolidRaster implements IHasExtraInformation {

    /**
     * Konstruktor.
     *
     * @param id
     *            ID
     */
    public ChestRaster(byte id) {
        super(id, "chest", Images.kiste);
    }

    @Override
    public boolean canWalkOnFromDirection(int x, int y, Entity entity, Direction dir) {
        if (entity instanceof EntityPlayer) {
            Main.openGui(new GuiInventory(((ExtraInformationChest) Main.getLevel().getMap().getExtraInformationAt(x, y)).getInventory()));
        }
        return super.canWalkOnFromDirection(x, y, entity, dir);
    }

    @Override
    public ExtraInformation createExtraInformation() {
        return new ExtraInformationChest();
    }

}
