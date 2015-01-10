package projektkurs.raster.extra;

import projektkurs.Main;
import projektkurs.entity.Entity;
import projektkurs.entity.EntityLiving;
import projektkurs.lib.Raster;
import projektkurs.lib.Strings;
import projektkurs.raster.AbstractRaster;
import projektkurs.util.SaveData;

/**
 * ExtraInformation von Feuer.
 */
public class ExtraInformationFire extends ExtraInformation {

    /**
     * Das Hintergrundraster.
     */
    private AbstractRaster background;

    /**
     * Konstruktor.
     */
    public ExtraInformationFire() {
        background = Raster.destroyedRaster;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    /**
     * Das Hintergrundraster.
     *
     * @return Hintergrundraster
     */
    public AbstractRaster getBackground() {
        return background;
    }

    @Override
    public String getInternalName() {
        return "fire";
    }

    @Override
    public void load(SaveData data) {
        super.load(data);
        background = Raster.RASTER[data.getInteger(Strings.EXTRA_RASTER)];
    }

    /**
     * Setzt das Hintergrundraster.
     *
     * @param background
     *            Hintergrundraster
     */
    public void setBackground(AbstractRaster background) {
        this.background = background;
    }

    @Override
    public void update() {
        for (Entity e : Main.getLevel().getMap().getEntitiesAt(posX, posY)) {
            if (e != null && e instanceof EntityLiving) {
                ((EntityLiving) e).damage(1);
            }
        }
    }

    @Override
    public void write(SaveData data) {
        super.write(data);
        data.set(Strings.EXTRA_RASTER, background.getID());
    }

}
