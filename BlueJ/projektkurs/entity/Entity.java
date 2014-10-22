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

	public Entity(int posX, int posY, BufferedImage image) {
		this.posX = posX;
		this.posY = posY;
		this.image = image;
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	public Behaviours getBehaviour() {
		return Behaviours.NOTHING;
	}

	public BufferedImage getImage() {
		return image;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void moveBy(int dx, int dy) {
		posX += dx;
		posY += dy;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	@Override
	public void update() {
		// NO-OP
	}

}
