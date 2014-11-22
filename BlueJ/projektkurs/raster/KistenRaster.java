package projektkurs.raster;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityPlayer;
import projektkurs.gui.GuiInventory;
import projektkurs.lib.Images;
import projektkurs.raster.extra.ExtraInformation;
import projektkurs.raster.extra.ExtraInformationKiste;
import projektkurs.raster.extra.IHasExtraInformation;
import projektkurs.util.Direction;

/**
 * Kiste.
 */
public class KistenRaster extends SolidRaster implements IHasExtraInformation {

    /**
     * Konstruktor.
     */
    public KistenRaster() {
        super(Images.kiste);
    }

    @Override
    public boolean canWalkOnFromDirection(int x, int y, Entity entity, Direction dir) {
        if (entity instanceof EntityPlayer) {
            Main.openGui(new GuiInventory(((ExtraInformationKiste) Main.getLevel().getCurrMap().getExtraInformationAt(x, y)).getInventar()));
        }
        return super.canWalkOnFromDirection(x, y, entity, dir);
    }

    @Override
    public ExtraInformation createExtraInformation() {
        return new ExtraInformationKiste();
    }

}
