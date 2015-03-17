package projektkurs.raster.extra;

import projektkurs.io.storage.SaveData;
import projektkurs.item.ItemStack;
import projektkurs.lib.Items;
import projektkurs.lib.Sprites;
import projektkurs.lib.Strings;
import projektkurs.render.Sprite;
import projektkurs.util.Direction;
import projektkurs.world.Spielfeld;

/**
 * ExtraInformation einer Tuer.
 */
public class ExtraInformationDoor extends ExtraInformation {

    /**
     * Richtung/Orientierung.
     */
    private Direction direction;
    /**
     * Ist die Tuer geoeffnet.
     */
    private boolean isOpen;
    /**
     * Oeffnungscode.
     */
    private int openingKey;

    /**
     * Konstruktor.
     *
     * @param map
     *            Spielfeld
     * @param x
     *            X-Koordinate
     * @param y
     *            Y-Koordinate
     */
    public ExtraInformationDoor(Spielfeld map, int x, int y) {
        super(map, x, y);
        direction = Direction.UNKNOWN;
    }

    /**
     * Die Richtung/Orientierung.
     *
     * @return Richtung/Orientierung
     */
    public Direction getDirection() {
        return direction;
    }

    @Override
    public String getInternalName() {
        return "door";
    }

    /**
     * Das Bild.
     *
     * @return Bild.
     */
    public Sprite getSprite() {

        switch (direction) {
            case LEFT:
            case RIGHT:
                return isOpen ? Sprites.door_open_WE : Sprites.door_WE;
            default:
                return isOpen ? Sprites.door_open_NS : Sprites.door_NS;
        }
    }

    /**
     * Ist die Tuer von der gegebenen Richtung aus offen und damit begehbar.
     *
     * @param dir
     *            Richtung
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isOpenFromDirection(Direction dir) {
        return isOpen && (dir == direction || dir == direction.getOpposite());
    }

    @Override
    public void load(SaveData data) {
        super.load(data);
        direction = Direction.getDirectionForIndex(data.getInteger(Strings.EXTRA_DIR));
        isOpen = data.getBoolean(Strings.EXTRA_OPEN);
        openingKey = data.getInteger(Strings.EXTRA_KEY);
    }

    /**
     * Setzt die Richtung/Orientierung.
     *
     * @param direction
     *            Setzt die Richtung/Orientierung
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Setzt den Oeffnungscode.
     *
     * @param openingKey
     *            Oeffnungscode
     */
    public void setOpeningKey(int openingKey) {
        this.openingKey = openingKey;
    }

    /**
     * Versucht die Tuer mit dem gegebenen Schluessel zu oeffnen.
     *
     * @param key
     *            ItemStack
     */
    public void tryOpen(ItemStack key) {
        if (key != null && key.itemEquals(Items.key) && key.getDamage() == openingKey) {
            isOpen = true;
        }
    }

    @Override
    public void write(SaveData data) {
        super.write(data);
        data.set(Strings.EXTRA_DIR, direction.getIndex());
        data.set(Strings.EXTRA_OPEN, isOpen);
        data.set(Strings.EXTRA_KEY, openingKey);
    }
}
