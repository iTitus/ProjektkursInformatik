package projektkurs.cutscene.object;

import java.awt.image.BufferedImage;

/**
 * Ein Objekt in einer CutScene
 *
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
	 * @param object
	 * @return
	 */
	public boolean isInside(CutSceneObject object) {
		if (object.sizeX <= 0 || object.sizeY <= 0 || sizeX <= 0 || sizeY <= 0)
			return false;
		return (((object.posX + object.sizeX) < object.posX || (object.posX + object.sizeX) > posX)
				&& ((object.posY + object.sizeY) < object.posY || (object.posY + object.sizeY) > posY)
				&& ((posX + sizeX) < posX || (posX + sizeX) > object.posX) && ((posY + sizeY) < posY || (posY + sizeY) > object.posY));
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
	 * 
	 * @param posX
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * 
	 * @param posY
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	@SuppressWarnings("unused")
	private boolean isInside2(CutSceneObject object) {
		int tw = sizeX;
		int th = sizeY;
		int rw = object.sizeX;
		int rh = object.sizeY;
		if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0)
			return false;
		int tx = posX;
		int ty = posY;
		int rx = object.posX;
		int ry = object.posY;
		rw += rx;
		rh += ry;
		tw += tx;
		th += ty;
		return ((rw < rx || rw > tx) && (rh < ry || rh > ty)
				&& (tw < tx || tw > rx) && (th < ty || th > ry));
	}
}
