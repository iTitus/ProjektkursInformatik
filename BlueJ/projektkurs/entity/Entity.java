package projektkurs.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import projektkurs.Main;
import projektkurs.entity.behaviour.Behaviours;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.Strings;
import projektkurs.raster.AbstractRaster;
import projektkurs.util.Direction;
import projektkurs.util.ICanUpdate;
import projektkurs.util.IHasPositionAndSize;
import projektkurs.util.ISaveable;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;
import projektkurs.util.SaveData;

/**
 * Ein Entity.
 */
public abstract class Entity implements ICanUpdate, ISaveable, IHasPositionAndSize {

    /**
     * Richtung, in die dieser Entity guckt.
     */
    private Direction facing;
    /**
     * Bild.
     */
    private BufferedImage image;
    /**
     * Soll dieser Entity nächsten Tick verschwinden.
     */
    private boolean shouldDeSpawn;
    /**
     * X-Koordinate.
     */
    protected int posX;
    /**
     * Y-Koordinate.
     */
    protected int posY;
    /**
     * Breite.
     */
    protected int sizeX;
    /**
     * Höhe.
     */
    protected int sizeY;

    /**
     * Konstruktor.
     */
    public Entity() {
    }

    /**
     * Konstruktor.
     *
     * @param posX
     *            X-Position
     * @param posY
     *            Y-Position
     * @param image
     *            Bild
     */
    public Entity(int posX, int posY, BufferedImage image) {
        this(posX, posY, 1, 1, image);
    }

    /**
     * Konstruktor.
     *
     * @param posX
     *            X-Position
     * @param posY
     *            Y-Position
     * @param sizeX
     *            Breite
     * @param sizeY
     *            Höhe
     * @param image
     *            Bild
     */
    public Entity(int posX, int posY, int sizeX, int sizeY, BufferedImage image) {
        this.posX = posX;
        this.posY = posY;
        this.image = image;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        shouldDeSpawn = false;
        facing = Direction.UNKNOWN;
    }

    /**
     * Kann sich der Entity an die gegebene Stelle bewegen.
     *
     * @param x
     *            X-Position
     * @param y
     *            Y-Position
     * @return true, wenn ja; false, wenn nein
     */
    public boolean canMoveTo(int x, int y) {

        if (!Main.getLevel().getMap().isInMap(x, y)) {
            return false;
        }

        facing = Direction.getDirectionForOffset(MathUtil.signum(x - posX), MathUtil.signum(y - posY));

        boolean ret = true;

        Entity e = Main.getLevel().getMap().getEntityAt(x, y);
        if (e != null) {
            onCollideWith(e);
            ret = false;
        }

        AbstractRaster r = Main.getLevel().getMap().getRasterAt(x, y);
        if (r != null) {
            Direction d = Direction.getDirectionForOffset(MathUtil.signum(x - posX), MathUtil.signum(y - posY)).getOpposite();
            r.onCollideWith(x, y, this);
            if (r.canWalkOnFromDirection(x, y, this, d) && ret) {
                r.onWalkOnFromDirection(x, y, this, d);
            } else {
                ret = false;
            }
        }
        return ret;

    }

    @Override
    public boolean canUpdate() {
        return !getBehaviour().equals(Behaviours.NOTHING);
    }

    /**
     * Behaviours dieses Entities.
     *
     * @return Behaviours
     */
    public Behaviours getBehaviour() {
        return Behaviours.NOTHING;
    }

    /**
     * Rectangle dieses Entities.
     *
     * @return Rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle(posX, posY, sizeX, sizeY);
    }

    /**
     * Richtung, in die dieser Entity guckt.
     *
     * @return Direction
     */
    public Direction getFacing() {
        return facing;
    }

    /**
     * Bild.
     *
     * @return BufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    /**
     * X-Koordinate umgerechnet in Bildschirmkoordinaten.
     *
     * @return X-Koordinate auf dem Bildschirm
     */
    public int getRenderX() {
        return (posX - Main.getRenderHelper().getSightX()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_X;
    }

    /**
     * Y-Koordinate umgerechnet in Bildschirmkoordinaten.
     *
     * @return Y-Koordinate auf dem Bildschirm
     */
    public int getRenderY() {
        return (posY - Main.getRenderHelper().getSightY()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_Y;
    }

    @Override
    public int getSizeX() {
        return sizeX;
    }

    @Override
    public int getSizeY() {
        return sizeY;
    }

    /**
     * Ist ein anderer Entity innerhalb von diesem Entity.
     *
     * @param e
     *            anderer Entity
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isInside(Entity e) {
        return isInside(e.posX, e.posY, e.sizeX, e.sizeY);
    }

    /**
     * Ist das gegebene Rechteck innerhalb von diesem Entity.
     *
     * @param posX
     *            X-Koordinate der oberen linken Ecke des Rechtecks
     * @param posY
     *            Y-Koordinate der oberen linken Ecke des Rechtecks
     * @param sizeX
     *            Breite des Rechtecks
     * @param sizeY
     *            Höhe des Rechtecks
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isInside(int posX, int posY, int sizeX, int sizeY) {
        return Math.max(posX, this.posX) < Math.min(posX + sizeX, this.posX + this.sizeX) && Math.max(posY, this.posY) < Math.min(posY + sizeY, this.posY + this.sizeY);
    }

    @Override
    public void load(SaveData data) {
        posX = data.getInteger(Strings.ENTITY_X);
        posY = data.getInteger(Strings.ENTITY_Y);
        sizeX = data.getInteger(Strings.ENTITY_SIZE_X);
        sizeY = data.getInteger(Strings.ENTITY_SIZE_Y);
        shouldDeSpawn = data.getBoolean(Strings.ENTITY_DESPAWN);
        facing = Direction.values()[data.getInteger(Strings.ENTITY_FACING)];
        image = Images.MAPPINGS.get(data.getString(Strings.ENTITY_IMAGE));
    }

    /**
     * Bewegt diesen Entity in die gegebene Richtung.
     *
     * @param dir
     *            Direction
     */
    public void moveBy(Direction dir) {
        moveBy(dir.getOffsetX(), dir.getOffsetY());
    }

    /**
     * Bewegt diesen Entity.
     *
     * @param dx
     *            Bewegung in X-Richtung
     * @param dy
     *            Bewegung in Y-Richtung
     */
    public void moveBy(int dx, int dy) {
        if ((dx != 0 || dy != 0) && canMoveTo(posX + dx, posY + dy)) {
            posX += dx;
            posY += dy;
        }
    }

    /**
     * Wird aufgerufen wenn dieser Entity einen anderen (e) anstößt.
     *
     * @param e
     *            anderer Entity
     */
    public void onCollideWith(Entity e) {
        // NO-OP
    }

    /**
     * Rendert den Entity.
     *
     * @param g
     *            Graphics2D Objekt
     */
    public void render(Graphics2D g) {
        RenderUtil.drawDefaultEntity(g, this);
    }

    /**
     * Markiert diesen Entity zum Verschwinden.
     */
    public void setDead() {
        shouldDeSpawn = true;
    }

    /**
     * Setzt das Bild dieses Entities.
     *
     * @param image
     *            Bild
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public void setPosition(int posX, int posY) {
        moveBy(posX - this.posX, posY - this.posY);
    }

    @Override
    public void setSize(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     * Soll dieser Entity nächsten Tick verschwinden.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public boolean shouldDeSpawn() {
        return shouldDeSpawn;
    }

    @Override
    public void update() {
        getBehaviour().getBehaviour().update(this);
    }

    @Override
    public void write(SaveData data) {
        data.set(Strings.ENTITY_X, posX);
        data.set(Strings.ENTITY_Y, posY);
        data.set(Strings.ENTITY_SIZE_X, sizeX);
        data.set(Strings.ENTITY_SIZE_Y, sizeY);
        data.set(Strings.ENTITY_DESPAWN, shouldDeSpawn);
        data.set(Strings.ENTITY_FACING, facing.ordinal());
        data.set(Strings.ENTITY_IMAGE, Images.BACK_MAPPINGS.get(image));
    }

}
