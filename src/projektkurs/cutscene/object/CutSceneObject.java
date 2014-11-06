package projektkurs.cutscene.object;

import java.awt.image.BufferedImage;

/**
 * Ein Objekt in einer CutScene
 */
public class CutSceneObject {

	private BufferedImage image;
	private int posX, posY;
	private final int sizeX, sizeY;

	/**
	 * Konstruktor für eine CutScene
	 *
	 * @param image
	 * @param sizeX
	 * @param sizeY
	 */
	public CutSceneObject(BufferedImage image, int posX, int posY, int sizeX,
			int sizeY) {
		this.image = image;
		setPos(posX, posY);
		this.sizeX = sizeX;
		this.sizeY = sizeY;
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
	 * @param object
	 * @return
	 */
	public boolean isInside(CutSceneObject object) {
		return !(object.sizeX <= 0 || object.sizeY <= 0 || sizeX <= 0 || sizeY <= 0)
				&& (((object.posX + object.sizeX) <= object.posX || (object.posX + object.sizeX) >= posX)
						&& ((object.posY + object.sizeY) <= object.posY || (object.posY + object.sizeY) >= posY)
						&& (sizeX <= posX || sizeX >= object.posX) && (sizeY <= posY || sizeY >= object.posY));
	}

	/**
	 * Bewegt das CutSceneObject
	 *
	 * @param deltaX
	 * @param deltaY
	 */
	public void moveBy(int deltaX, int deltaY) {
		posX += deltaX;
		posY += deltaY;
	}

	/**
	 * Setzt das CutSceneObject an eine Position
	 *
	 * @param posX
	 * @param posY
	 */
	public void setPos(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	/**
	 * @param posX
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @param posY
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

}
