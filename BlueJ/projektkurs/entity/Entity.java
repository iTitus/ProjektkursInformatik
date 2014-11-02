package projektkurs.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import projektkurs.Main;
import projektkurs.entity.behaviour.Behaviours;
import projektkurs.util.Direction;
import projektkurs.util.ICanUpdate;

/**
 * Ein Entity
 *
 */
public class Entity implements ICanUpdate {

	protected BufferedImage image;

	protected int posX, posY, sizeX, sizeY;

	/**
	 * 
	 * @param posX
	 * @param posY
	 * @param image
	 */
	public Entity(int posX, int posY, BufferedImage image) {
		this(posX, posY, 1, 1, image);
	}

	/**
	 * 
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
	}

	public boolean canMoveTo(int x, int y) {
		return (!Main.getSpielfeld().isEntityAtPos(x, y))
				&& (Main.getSpielfeld().isRasterAt(x, y) ? Main
						.getSpielfeld()
						.getRasterAt(x, y)
						.canWalkOnFromDirection(
								x,
								y,
								Direction.getDirectionForOffset(x - posX,
										y - posY).getOpposite()) : true);

	}

	@Override
	public boolean canUpdate() {
		return !getBehaviour().equals(Behaviours.NOTHING);
	}

	/**
	 * 
	 * @return
	 */
	public Behaviours getBehaviour() {
		return Behaviours.NOTHING;
	}

	public Rectangle getBounds() {
		return new Rectangle(posX, posY, sizeX, sizeY);
	}

	/**
	 * 
	 * @return
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * 
	 * @return
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * 
	 * @return
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * 
	 * @return
	 */
	public int getSizeX() {
		return sizeX;
	}

	/**
	 * 
	 * @return
	 */
	public int getSizeY() {
		return sizeY;
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	public boolean isInside(Entity e) {
		return isInside(e.posX, e.posY, e.sizeX, e.sizeY);
	}

	/**
	 * 
	 * @param posX
	 * @param posY
	 * @param sizeX
	 * @param sizeY
	 * @return
	 */
	public boolean isInside(int posX, int posY, int sizeX, int sizeY) {
		if ((Math.max(posX, this.posX) < Math.min((posX + sizeX),
				(this.posX + this.sizeX)))
				&& ((Math.max(posY, this.posY) < Math.min((posY + sizeY),
						(this.posY + this.sizeY)))))
			return true;
		return false;
	}

	/**
	 * 
	 * @param dir
	 */
	public void moveBy(Direction dir) {
		moveBy(dir.getOffsetX(), dir.getOffsetY());
	}

	/**
	 * 
	 * @param dx
	 * @param dy
	 */
	public void moveBy(int dx, int dy) {
		if ((dx != 0 || dy != 0) && canMoveTo(posX + dx, posY + dy)) {
			posX += dx;
			posY += dy;
			Main.getRenderHelper().move(this);
		}
	}

	/**
	 * 
	 * @param image
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * 
	 * @param posX
	 */
	public void setPos(int posX, int posY) {
		moveBy(posX - this.posX, posY - this.posY);
	}

	@Override
	public void update() {
		getBehaviour().getBehaviour().onTick(this);
	}

}
