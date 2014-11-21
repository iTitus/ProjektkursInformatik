package projektkurs.world.raster.extra;

import projektkurs.lib.Strings;
import projektkurs.util.ICanUpdate;
import projektkurs.util.ISaveable;
import projektkurs.util.SaveData;

/**
 * ExtraInformationen
 */
public class ExtraInformation implements ICanUpdate, ISaveable {

    protected int x, y;

    @Override
    public boolean canUpdate() {
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void load(SaveData data) {
        x = data.getInteger(Strings.EXTRA_X);
        y = data.getInteger(Strings.EXTRA_Y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
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
