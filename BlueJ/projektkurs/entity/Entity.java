package projektkurs.entity;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import projektkurs.Main;
import projektkurs.entity.behaviour.Behaviour;
import projektkurs.lib.Integers;
import projektkurs.lib.Sprites;
import projektkurs.lib.Strings;
import projektkurs.raster.AbstractRaster;
import projektkurs.render.Screen;
import projektkurs.render.Sprite;
import projektkurs.util.Direction;
import projektkurs.util.IHasPositionAndSize;
import projektkurs.util.ISaveable;
import projektkurs.util.IUpdatable;
import projektkurs.util.Logger;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;
import projektkurs.util.SaveData;
import projektkurs.world.Spielfeld;

/**
 * Ein Entity.
 */
public abstract class Entity implements IUpdatable, ISaveable, IHasPositionAndSize {

    /**
     * Alle Behaviours dieses Entities.
     */
    private final ArrayList<Behaviour> behaviours = new ArrayList<Behaviour>();
    /**
     * Richtung, in die dieser Entity guckt.
     */
    private Direction facing;
    /**
     * Soll das Bild sich mit aendernder Richtung veraendern.
     */
    private boolean shouldChangeImageWithFacing;
    /**
     * Soll dieser Entity naechsten Tick verschwinden.
     */
    private boolean shouldDeSpawn;
    /**
     * Bild.
     */
    private Sprite[] sprites;
    /**
     * Spielfeld.
     */
    protected Spielfeld map;
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
     * Hoehe.
     */
    protected int sizeY;

    /**
     * Konstruktor.
     */
    public Entity(Spielfeld map) {
        this.map = map;
        shouldDeSpawn = false;
        facing = Direction.UNKNOWN;
        sprites = null;
        shouldChangeImageWithFacing = false;
        posX = 0;
        posY = 0;
        sizeX = 1;
        sizeY = 1;
    }

    /**
     * Konstruktor.
     *
     * @param map
     *            Spielfeld
     * @param posX
     *            X-Position
     * @param posY
     *            Y-Position
     * @param sizeX
     *            Breite
     * @param sizeY
     *            Hoehe
     * @param sprites
     *            Sprite
     */
    public Entity(Spielfeld map, int posX, int posY, int sizeX, int sizeY, Sprite... sprites) {
        this(map);
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        if (sprites.length == 4) {
            this.sprites = sprites;
            shouldChangeImageWithFacing = true;
        } else if (sprites.length == 1) {
            this.sprites = sprites;
            shouldChangeImageWithFacing = false;
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Konstruktor.
     *
     * @param map
     *            Spielfeld
     * @param posX
     *            X-Position
     * @param posY
     *            Y-Position
     * @param sprites
     *            Sprite
     */
    public Entity(Spielfeld map, int posX, int posY, Sprite... sprites) {
        this(map, posX, posY, 1, 1, sprites);
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

        if (!map.isInMap(x, y)) {
            return false;
        }

        facing = Direction.getDirectionForOffset(MathUtil.signum(x - posX), MathUtil.signum(y - posY));

        boolean ret = true;

        for (Entity e : map.getEntitiesAt(x, y)) {
            if (e != null) {
                onCollideWith(e);
                ret = false;
            }
        }

        AbstractRaster r = map.getRasterAt(x, y);
        if (r != null) {
            Direction d = Direction.getDirectionForOffset(MathUtil.signum(x - posX), MathUtil.signum(y - posY)).getOpposite();
            r.onCollideWith(map, x, y, this);
            if (r.canWalkOnFromDirection(map, x, y, this, d) && ret) {
                r.onWalkOnFromDirection(map, x, y, this, d);
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
     * Der Interne Name dieses Entity-Typs.
     *
     * @return Interner Name
     */
    public final String getInternalName() {
        return getClass().getName();
    }

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
     * Bild.
     *
     * @return Sprite
     */
    public Sprite getSprite() {
        if (!shouldChangeImageWithFacing) {
            return sprites[0];
        }
        return sprites[MathUtil.floorDiv(facing.getIndex(), 2)];
    }

    /**
     * Alle Bilder.
     *
     * @return Bilder
     */
    public Sprite[] getSprites() {
        return sprites;
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
     *            Hoehe des Rechtecks
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
        sprites = new Sprite[data.getInteger(Strings.ENTITY_SPRITE_LENGTH)];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = Sprites.MAPPINGS.get(data.getString(Strings.ENTITY_SPRITE + "[" + i + "]"));
        }
        if (sprites.length == 1) {
            shouldChangeImageWithFacing = false;
        } else if (sprites.length == 4) {
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
     * Wird aufgerufen wenn dieser Entity einen anderen (Entity e) anstoesst.
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
     * @param screen
     *            Screen
     */
    public void render(Screen screen) {
        RenderUtil.drawDefaultEntity(screen, this);
    }

    /**
     * Markiert diesen Entity zum Verschwinden.
     */
    public void setDead() {
        shouldDeSpawn = true;
    }

    /**
     * Setzt das Spielfeld.
     *
     * @param map
     *            Spielfeld
     */
    public void setMap(Spielfeld map) {
        this.map = map;
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
     * Setzt das Bild dieses Entities.
     *
     * @param sprite
     *            Bild
     */
    public void setSprite(Sprite sprite, int i) {
        sprites[i] = sprite;
    }

    /**
     * Aendert dieser Entity sein Bild mit sich aendernder Richtung.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public boolean shouldChangeImageWithFacing() {
        return shouldChangeImageWithFacing;
    }

    /**
     * Soll dieser Entity naechsten Tick verschwinden.
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
        data.set(Strings.ENTITY_SPRITE_LENGTH, sprites.length);
        for (int i = 0; i < sprites.length; i++) {
            data.set(Strings.ENTITY_SPRITE + "[" + i + "]", sprites[i].getName());
        }
    }

    /**
     * Fuegt eine Behaviour hinzu.
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
