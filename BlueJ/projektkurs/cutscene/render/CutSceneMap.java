package projektkurs.cutscene.render;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import projektkurs.cutscene.object.CutSceneObject;

/**
 * 
 */
public class CutSceneMap {

	private final int sizeX, sizeY;
	private final BufferedImage[][] images;
	private final ArrayList<CutSceneObject> objects;

	/**
	 * 
	 * @param sizeX
	 * @param sizeY
	 */
	public CutSceneMap(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		images = new BufferedImage[sizeX][sizeY];
		objects = new ArrayList<CutSceneObject>();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return image
	 */
	public BufferedImage getImageAt(int x, int y) {
		if (x < 0 || x >= images.length || y < 0 || y >= images[x].length)
			return null;
		return images[x][y];
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param image
	 */
	public void setImageAt(int x, int y, BufferedImage image) {
		if (x < 0 || x >= images.length || y < 0 || y >= images[x].length)
			return;
		images[x][y] = image;
	}

	public boolean isImageAt(int x, int y) {
		return getImageAt(x, y) != null;
	}

	public ArrayList<CutSceneObject> getCutSceneObjectList() {
		return objects;
	}

	public int getMapSizeX() {
		return sizeX;
	}

	public int getMapSizeY() {
		return sizeY;
	}
}
