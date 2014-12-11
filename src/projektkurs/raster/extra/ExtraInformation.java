package projektkurs.raster.extra;

import projektkurs.lib.Strings;
import projektkurs.util.IHasPosition;
import projektkurs.util.ISaveable;
import projektkurs.util.IUpdatable;
import projektkurs.util.SaveData;

/**
 * Eine ExtraInformation.
 */
public class ExtraInformation implements IUpdatable, ISaveable, IHasPosition {

    /**
     * X-Koordinate.
     */
    protected int posX;
    /**
     * Y-Koordinate.
     */
    protected int posY;

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public void load(SaveData data) {
        posX = data.getInteger(Strings.EXTRA_X);
        posY = data.getInteger(Strings.EXTRA_Y);
    }

    @Override
    public void setPosition(int x, int y) {
        posX = x;
        posY = y;
    }

    @Override
    public void update() {
        // NO-OP
    }

    @Override
    public void write(SaveData data) {
        data.set(Strings.EXTRA_X, posX);
        data.set(Strings.EXTRA_Y, posY);
    }

}
