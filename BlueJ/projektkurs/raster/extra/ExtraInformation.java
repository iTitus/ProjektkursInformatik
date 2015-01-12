package projektkurs.raster.extra;

import projektkurs.lib.Strings;
import projektkurs.util.IHasPosition;
import projektkurs.util.ISaveable;
import projektkurs.util.IUpdatable;
import projektkurs.util.SaveData;
import projektkurs.world.Spielfeld;

/**
 * Eine ExtraInformation.
 */
public abstract class ExtraInformation implements IUpdatable, ISaveable, IHasPosition {

    /**
     * Spielfeld.
     */
    protected final Spielfeld map;
    /**
     * X-Koordinate.
     */
    protected int posX;
    /**
     * Y-Koordinate.
     */
    protected int posY;

    /**
     * Konstruktor.
     *
     * @param map
     *            Spielfeld
     */
    public ExtraInformation(Spielfeld map, int x, int y) {
        this.map = map;
        posX = x;
        posY = y;
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    /**
     * Der Interne Name dieses ExtraInformation-Typs.
     *
     * @return Interner Name
     */
    public abstract String getInternalName();

    /**
     * Das Spielfeld.
     *
     * @return Spielfeld
     */
    public Spielfeld getMap() {
        return map;
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
