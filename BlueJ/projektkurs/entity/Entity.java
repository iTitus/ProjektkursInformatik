package projektkurs.entity;

import java.awt.image.BufferedImage;

import projektkurs.entity.behavior.Behaviours;
import projektkurs.lib.ICanUpdate;

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
		this(posX, posY, 32, 32, image);
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
		if (sizeX <= 0 || sizeY <= 0 || this.sizeX <= 0 || this.sizeY <= 0)
			return false;
		return (((posX + sizeX) < posX || (posX + sizeX) > this.posX)
				&& ((posY + sizeY) < posY || (posY + sizeY) > this.posY)
				&& (this.sizeX < this.posX || this.sizeX > posX) && (this.sizeY < this.posY || this.sizeY > posY));
	}

	/**
	 * 
	 * @param dx
	 * @param dy
	 */
	public void moveBy(int dx, int dy) {
		posX += dx;
		posY += dy;
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
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * 
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	@Override
	public void update() {
		getBehaviour().getBehaviour().onTick(this);
	}

}
