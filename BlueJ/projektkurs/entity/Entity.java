package projektkurs.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import projektkurs.Main;
import projektkurs.entity.behaviour.Behaviour;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.Strings;
import projektkurs.raster.AbstractRaster;
import projektkurs.util.Direction;
import projektkurs.util.IHasPositionAndSize;
import projektkurs.util.ISaveable;
import projektkurs.util.IUpdatable;
import projektkurs.util.Logger;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;
import projektkurs.util.SaveData;

/**
 * Ein Entity.
 */
public abstract class Entity implements IUpdatable, ISaveable, IHasPositionAndSize {

    /**
     * Alle Behaviours dieses Entities.sd
     */
    private final ArrayList<Behaviour> behaviours = new ArrayList<Behaviour>();
    /**
     * Richtung, in die dieser Entity guckt.
     */
    private Direction facing;
    /**
     * Bild.
     */
    private BufferedImage[] images;
    /**
     * Soll das Bild sich mit ändernder Richtung verändern.
     */
    private boolean shouldChangeImageWithFacing;
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
        shouldDeSpawn = false;
        facing = Direction.UNKNOWN;
        images = null;
        shouldChangeImageWithFacing = false;
        posX = 0;
        posY = 0;
        sizeX = 1;
        sizeY = 1;
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
    public Entity(int posX, int posY, BufferedImage... images) {
        this(posX, posY, 1, 1, images);
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
    public Entity(int posX, int posY, int sizeX, int sizeY, BufferedImage... images) {
        this();
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        if (images.length == 4) {
            this.images = images;
            shouldChangeImageWithFacing = true;
        } else if (images.length == 1) {
            this.images = images;
            shouldChangeImageWithFacing = false;
        } else {
            throw new IllegalArgumentException();
        }

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

        for (Entity e : Main.getLevel().getMap().getEntitiesAt(x, y)) {
            if (e != null) {
                onCollideWith(e);
                ret = false;
            }
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
        return !behaviours.isEmpty();
    }

    /**
     * Alle Behaviours.
     *
     * @return Behaviours
     */
    public List<Behaviour> getBehaviours() {
        return Collections.unmodifiableList(behaviours);
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
        if (!shouldChangeImageWithFacing) {
            return images[0];
        }
        return images[MathUtil.floorDiv(facing.getIndex(), 2)];
    }

    /**
     * Alle Bilder.
     *
     * @return Bilder
     */
    public BufferedImage[] getImages() {
        return images;
    }

    /**
     * Der Interne Name dieses Entity-Typs.
     *
     * @return Interner Name
     */
    public abstract String getInternalName();

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
        facing = Direction.getDirectionForIndex(data.getInteger(Strings.ENTITY_FACING));
        images = new BufferedImage[data.getInteger(Strings.ENTITY_IMAGE_LENGTH)];
        for (int i = 0; i < images.length; i++) {
            images[i] = Images.MAPPINGS.get(data.getString(Strings.ENTITY_IMAGE + "[" + i + "]"));
        }
        if (images.length == 1) {
            shouldChangeImageWithFacing = false;
        } else if (images.length == 4) {
            shouldChangeImageWithFacing = true;
        } else {
            Logger.warn(getInternalName() + ": Wrong SaveData format -> 1 or 4 images");
        }
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
    public void setImage(BufferedImage image, int i) {
        images[i] = image;
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
     * Ändert dieser Entity sein Bild mit sich ändernder Richtung.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public boolean shouldChangeImageWithFacing() {
        return shouldChangeImageWithFacing;
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
        for (Behaviour behaviour : behaviours) {
            if (behaviour.canUpdate()) {
                behaviour.update();
            }
        }
    }

    @Override
    public void write(SaveData data) {
        data.set(Strings.ENTITY_X, posX);
        data.set(Strings.ENTITY_Y, posY);
        data.set(Strings.ENTITY_SIZE_X, sizeX);
        data.set(Strings.ENTITY_SIZE_Y, sizeY);
        data.set(Strings.ENTITY_DESPAWN, shouldDeSpawn);
        data.set(Strings.ENTITY_FACING, facing.getIndex());
        data.set(Strings.ENTITY_IMAGE_LENGTH, images.length);
        for (int i = 0; i < images.length; i++) {
            data.set(Strings.ENTITY_IMAGE + "[" + i + "]", Images.BACK_MAPPINGS.get(images[i]));
        }
    }

    /**
     * Fügt eine Behaviour hinzu.
     *
     * @param behaviour
     *            Behaviour
     */
    protected final void addBehaviour(Behaviour behaviour) {
        if (behaviour != null) {
            for (Behaviour bhvr : behaviours) {
                if (!bhvr.isCompatibleWith(behaviour) || !behaviour.isCompatibleWith(bhvr)) {
                    return;
                }
            }
            behaviours.add(behaviour);
        }
    }

}
