package projektkurs.cutscene.object;

import java.awt.image.BufferedImage;

import projektkurs.cutscene.CutSceneManager;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.Sprites;
import projektkurs.render.Screen;
import projektkurs.render.Sprite;
import projektkurs.util.IHasPositionAndSize;
import projektkurs.util.RenderUtil;

/**
 * Ein Objekt in einer CutScene.
 */
@SuppressWarnings("deprecation")
public class CutSceneObject implements IHasPositionAndSize<Integer, Integer> {

	/**
	 * Bild.
	 */
	private final Sprite sprite;
	/**
	 * X-Position.
	 */
	private int posX;
	/**
	 * Y-Position.
	 */
	private int posY;
	/**
	 * Breite.
	 */
	private int sizeX;
	/**
	 * Hoehe.
	 */
	private int sizeY;

	/**
	 * Konstruktor.
	 *
	 * @param image BufferedImage
	 * @param posX  X-Position
	 * @param posY  Y-Position
	 * @param sizeX Breite
	 * @param sizeY Hoehe
	 */
	@Deprecated
	public CutSceneObject(BufferedImage image, int posX, int posY, int sizeX, int sizeY) {
		sprite = image != null ? new Sprite("", image).rescale(Images.BACK_MAPPINGS.get(image), sizeX * Integers.RASTER_SIZE / (double) image.getWidth(), sizeY * Integers.RASTER_SIZE / (double) image.getHeight()) : Sprites.MISSING_ICON;
		setPosition(posX, posY);
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	/**
	 * @param sprite Sprite
	 * @param posX   X-Position
	 * @param posY   Y-Position
	 * @param sizeX  Breite
	 * @param sizeY  Hoehe
	 */
	public CutSceneObject(Sprite sprite, int posX, int posY, int sizeX, int sizeY) {
		this.sprite = sprite;
		setPosition(posX, posY);
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	@Override
	public Integer getPosX() {
		return posX;
	}

	@Override
	public Integer getPosY() {
		return posY;
	}

	@Override
	public Integer getSizeX() {
		return sizeX;
	}

	@Override
	public Integer getSizeY() {
		return sizeY;
	}

	/**
	 * Bild.
	 *
	 * @return Sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * Ist ein anderes CutSceneObject innerhalb von diesem CutSceneObject.
	 *
	 * @param object anderes CutSceneObject
	 * @return true, wenn ja; false, wenn nein
	 */
	public boolean isInside(CutSceneObject object) {
		return !(object.sizeX <= 0 || object.sizeY <= 0 || sizeX <= 0 || sizeY <= 0) && (object.posX + object.sizeX <= object.posX || object.posX + object.sizeX >= posX) && (object.posY + object.sizeY <= object.posY || object.posY + object.sizeY >= posY) && (sizeX <= posX || sizeX >= object.posX)
				&& (sizeY <= posY || sizeY >= object.posY);
	}

	/**
	 * Ist das gegebene Rechteck innerhalb von diesem CutSceneObject.
	 *
	 * @param posX  X-Koordinate der oberen linken Ecke des Rechtecks
	 * @param posY  Y-Koordinate der oberen linken Ecke des Rechtecks
	 * @param sizeX Breite des Rechtecks
	 * @param sizeY Hoehe des Rechtecks
	 * @return true, wenn ja; false, wenn nein
	 */
	public boolean isInside(int posX, int posY, int sizeX, int sizeY) {
		return Math.max(posX, this.posX) < Math.min(posX + sizeX, this.posX + this.sizeX) && Math.max(posY, this.posY) < Math.min(posY + sizeY, this.posY + this.sizeY);
	}

	/**
	 * Bewegt das CutSceneObject.
	 *
	 * @param deltaX Bewegung in X-Richtung
	 * @param deltaY Bewegung in Y-Richtung
	 */
	public void moveBy(int deltaX, int deltaY) {
		posX += deltaX;
		posY += deltaY;
	}

	/**
	 * Rendert das CutSceneObject.
	 *
	 * @param screen Screen
	 */
	public void render(Screen screen) {
		RenderUtil.drawSprite(screen, sprite, getRenderX(), getRenderY());
	}

	@Override
	public void setPosition(Integer posX, Integer posY) {
		this.posX = posX;
		this.posY = posY;
	}

	@Override
	public void setSize(Integer sizeX, Integer sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	/**
	 * X-Koordinate umgerechnet in Bildschirmkoordinaten.
	 *
	 * @return X-Koordinate auf dem Bildschirm
	 */
	private int getRenderX() {
		return (posX - CutSceneManager.getCutSceneRenderHelper().getSightX()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_X;
	}

	/**
	 * Y-Koordinate umgerechnet in Bildschirmkoordinaten.
	 *
	 * @return Y-Koordinate auf dem Bildschirm
	 */
	private int getRenderY() {
		return (posY - CutSceneManager.getCutSceneRenderHelper().getSightY()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_Y;
	}

}
