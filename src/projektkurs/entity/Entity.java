package projektkurs.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import projektkurs.Main;
import projektkurs.entity.behaviour.Behaviours;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.Strings;
import projektkurs.util.Direction;
import projektkurs.util.ICanUpdate;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;
import projektkurs.util.SaveData;
import projektkurs.world.raster.AbstractRaster;

/**
 * Ein Entity
 */
public abstract class Entity implements ICanUpdate {

	private Direction facing;
	private BufferedImage image;
	private boolean shouldDeSpawn;
	private int sizeX;
	private int sizeY;

	protected int posX, posY;

	public Entity() {
	}

	/**
	 * @param posX
	 * @param posY
	 * @param image
	 */
	public Entity(int posX, int posY, BufferedImage image) {
		this(posX, posY, 1, 1, image);
	}

	/**
	 * @param posX
	 * @param posY
	 * @param sizeX
	 * @param sizeY
	 * @param image
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

	public boolean canMoveTo(int x, int y) {

		facing = Direction.getDirectionForOffset(MathUtil.signum(x - posX),
				MathUtil.signum(y - posY));

		if (x < 0 || x >= Main.getLevel().getCurrMap().getMapSizeX() || y < 0
				|| y >= Main.getLevel().getCurrMap().getMapSizeY())
			return false;

		boolean ret = true;

		Entity e = Main.getLevel().getCurrMap().getEntityAt(x, y);
		if (e != null) {
			onCollideWith(e);
			ret = false;
		}

		AbstractRaster r = Main.getLevel().getCurrMap().getRasterAt(x, y);
		if (r != null) {
			Direction d = Direction.getDirectionForOffset(
					MathUtil.signum(x - posX), MathUtil.signum(y - posY))
					.getOpposite();
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
	 * @return
	 */
	public Behaviours getBehaviour() {
		return Behaviours.NOTHING;
	}

	public Rectangle getBounds() {
		return new Rectangle(posX, posY, sizeX, sizeY);
	}

	public Direction getFacing() {
		return facing;
	}

	/**
	 * @return
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @return
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @return
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * 
	 * @return int
	 */
	public int getRenderX() {
		return ((posX - Main.getRenderHelper().getSightX()) * Integers.RASTER_SIZE)
				+ Integers.WINDOW_HUD_X;
	}

	/**
	 * 
	 * @return int
	 */
	public int getRenderY() {
		return ((posY - Main.getRenderHelper().getSightY()) * Integers.RASTER_SIZE)
				+ Integers.WINDOW_HUD_Y;
	}

	/**
	 * @return
	 */
	public int getSizeX() {
		return sizeX;
	}

	/**
	 * @return
	 */
	public int getSizeY() {
		return sizeY;
	}

	/**
	 * @param e
	 * @return
	 */
	public boolean isInside(Entity e) {
		return isInside(e.posX, e.posY, e.sizeX, e.sizeY);
	}

	/**
	 * @param posX
	 * @param posY
	 * @param sizeX
	 * @param sizeY
	 * @return
	 */
	public boolean isInside(int posX, int posY, int sizeX, int sizeY) {
		return (Math.max(posX, this.posX) < Math.min((posX + sizeX),
				(this.posX + this.sizeX)))
				&& ((Math.max(posY, this.posY) < Math.min((posY + sizeY),
						(this.posY + this.sizeY))));
	}

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
	 * @param dir
	 */
	public void moveBy(Direction dir) {
		moveBy(dir.getOffsetX(), dir.getOffsetY());
	}

	/**
	 * @param dx
	 * @param dy
	 */
	public void moveBy(int dx, int dy) {
		if ((dx != 0 || dy != 0) && canMoveTo(posX + dx, posY + dy)) {
			posX += dx;
			posY += dy;
		}
	}

	/**
	 * Wird aufgerufen wenn dieser Entity einen anderen (e) anstößt
	 *
	 * @param e
	 *            other entity
	 */
	public void onCollideWith(Entity e) {
		// NO-OP
	}

	/**
	 * Rendert den Entity
	 * 
	 * @param g
	 */
	public void render(Graphics2D g) {
		RenderUtil.drawDefaultEntity(g, this);
	}

	public void setDead() {
		shouldDeSpawn = true;
	}

	/**
	 * @param image
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * @param posX
	 */
	public void setPos(int posX, int posY) {
		moveBy(posX - this.posX, posY - this.posY);
	}

	public boolean shouldDeSpawn() {
		return shouldDeSpawn;
	}

	@Override
	public void update() {
		getBehaviour().getBehaviour().onTick(this);
	}

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
