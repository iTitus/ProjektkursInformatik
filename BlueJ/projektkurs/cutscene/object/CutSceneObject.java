package projektkurs.cutscene.object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import projektkurs.cutscene.CutSceneManager;
import projektkurs.lib.Integers;

/**
 * Ein Objekt in einer CutScene.
 */
public class CutSceneObject {

    /**
     * Bild.
     */
    private final BufferedImage image;
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
    private final int sizeX;
    /**
     * Höhe.
     */
    private final int sizeY;

    /**
     * @param image
     *            Bild des CutSceneObject @param posX
     * @param posX
     *            X-Position
     * @param posY
     *            Y-Position
     * @param sizeX
     *            Breite
     * @param sizeY
     *            Höhe
     */
    public CutSceneObject(BufferedImage image, int posX, int posY, int sizeX, int sizeY) {
        this.image = image;
        setPos(posX, posY);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     * Bild.
     *
     * @return BufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * X-Position.
     *
     * @return X-Position
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Y-Position.
     *
     * @return Y-Position
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Breite.
     *
     * @return Breite
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * Höhe.
     *
     * @return Höhe
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * Ist ein anderes CutSceneObject innerhalb von diesem CutSceneObject.
     *
     * @param object
     *            anderes CutSceneObject
     * @return true, wenn ja; false, wenn nein
     */
    public boolean isInside(CutSceneObject object) {
        return !(object.sizeX <= 0 || object.sizeY <= 0 || sizeX <= 0 || sizeY <= 0) && (object.posX + object.sizeX <= object.posX || object.posX + object.sizeX >= posX) && (object.posY + object.sizeY <= object.posY || object.posY + object.sizeY >= posY) && (sizeX <= posX || sizeX >= object.posX)
                && (sizeY <= posY || sizeY >= object.posY);
    }

    /**
     * Ist das gegebene Rechteck innerhalb von diesem CutSceneObject.
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

    /**
     * Bewegt das CutSceneObject.
     *
     * @param deltaX
     *            Bewegung in X-Richtung
     * @param deltaY
     *            Bewegung in Y-Richtung
     */
    public void moveBy(int deltaX, int deltaY) {
        posX += deltaX;
        posY += deltaY;
    }

    /**
     * Rendert das CutSceneObject.
     *
     * @param g
     *            das Graphics2D Objekt
     */
    public void render(Graphics2D g) {
        g.drawImage(image, getRenderX(), getRenderY(), sizeX * Integers.RASTER_SIZE, sizeY * Integers.RASTER_SIZE, null);
    }

    /**
     * Setzt das CutSceneObject an eine Position.
     *
     * @param posX
     *            X-Position
     * @param posY
     *            Y-Position
     */
    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Setzt die X-Position.
     *
     * @param posX
     *            X-Position
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Setzt die Y-Position.
     *
     * @param posY
     *            Y-Position
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * X-Koordinate umgerechnet in Bildschirmkoordinaten.
     *
     * @return X-Koordinate auf dem Bildschirm
     */
    private int getRenderX() {
        return (posY - CutSceneManager.getCurrentCutSceneRenderHelper().getSightY()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_Y;
    }

    /**
     * Y-Koordinate umgerechnet in Bildschirmkoordinaten.
     *
     * @return Y-Koordinate auf dem Bildschirm
     */
    private int getRenderY() {
        return (posX - CutSceneManager.getCurrentCutSceneRenderHelper().getSightX()) * Integers.RASTER_SIZE + Integers.WINDOW_HUD_X;
    }
}
