package projektkurs.raster.extra;

import projektkurs.lib.Strings;
import projektkurs.util.ICanUpdate;
import projektkurs.util.ISaveable;
import projektkurs.util.SaveData;

/**
 * Eine ExtraInformation.
 */
public class ExtraInformation implements ICanUpdate, ISaveable {

    /**
     * X-Koordinate.
     */
    protected int x;
    /**
     * Y-Koordinate.
     */
    protected int y;

    @Override
    public boolean canUpdate() {
        return false;
    }

    /**
     * X-Koordinate.
     *
     * @return X-Koordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Y-Koordinate.
     *
     * @return Y-Koordinate
     */
    public int getY() {
        return y;
    }

    @Override
    public void load(SaveData data) {
        x = data.getInteger(Strings.EXTRA_X);
        y = data.getInteger(Strings.EXTRA_Y);
    }

    /**
     * Setzt die Positon.
     *
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        // NO-OP
    }

    @Override
    public void write(SaveData data) {
        data.set(Strings.EXTRA_X, x);
        data.set(Strings.EXTRA_Y, y);
    }

}
