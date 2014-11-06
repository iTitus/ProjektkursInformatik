package projektkurs.cutscene.render;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import projektkurs.cutscene.render.object.RenderCutSceneObject;
import projektkurs.lib.Integers;

public class CutsceneRenderHelper {

	private BufferedImage background;
	private final Set<RenderCutSceneObject> objects;

	public CutsceneRenderHelper(BufferedImage[][] sight) {

		objects = Collections
				.synchronizedSet(new HashSet<RenderCutSceneObject>());

		background = new BufferedImage(Integers.SIGHT_X * Integers.RASTER_SIZE,
				Integers.SIGHT_Y * Integers.RASTER_SIZE,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = background.createGraphics();
		g.clearRect(0, 0, background.getWidth(), background.getHeight());
		for (int x = 0; x < sight.length; x++) {
			for (int y = 0; y < sight[x].length; y++) {
				g.drawImage(sight[x][y], x * Integers.RASTER_SIZE, y
						* Integers.RASTER_SIZE, Integers.RASTER_SIZE,
						Integers.RASTER_SIZE, null);
			}
		}
		g.dispose();
	}

	public BufferedImage getBackgound() {
		return background;
	}

	public Set<RenderCutSceneObject> getRenderCutSceneObjects() {
		return objects;
	}

	public void setBackgroundAt(int x, int y, BufferedImage toSet) {
		Graphics2D g = background.createGraphics();
		g.clearRect(x, y, toSet.getWidth(), toSet.getHeight());
		g.drawImage(toSet, x, y, toSet.getWidth(), toSet.getHeight(), null);
		g.dispose();
	}
}
