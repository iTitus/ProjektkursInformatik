package projektkurs.raster;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityPlayer;
import projektkurs.gui.GuiInventory;
import projektkurs.lib.Sprites;
import projektkurs.raster.extra.ExtraInformation;
import projektkurs.raster.extra.ExtraInformationChest;
import projektkurs.raster.extra.IHasExtraInformation;
import projektkurs.util.Direction;
import projektkurs.world.Spielfeld;

/**
 * Kiste.
 */
public class ChestRaster extends SolidRaster implements IHasExtraInformation {

    /**
     * Konstruktor.
     * @param id
     * ID
     */
    public ChestRaster(int id) {
        super(id, "chest", Sprites.chest);
    }

    @Override
    public boolean canWalkOnFromDirection(Spielfeld map, int x, int y, Entity entity, Direction dir) {
        if (entity instanceof EntityPlayer) {
            Main.openGui(new GuiInventory(((ExtraInformationChest) map.getExtraInformationAt(x, y)).getInventory()));
        }
        return super.canWalkOnFromDirection(map, x, y, entity, dir);
    }

    @Override
    public ExtraInformation createExtraInformation(Spielfeld map, int x, int y) {
        return new ExtraInformationChest(map, x, y);
    }

}
