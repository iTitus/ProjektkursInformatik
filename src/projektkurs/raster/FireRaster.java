package projektkurs.raster;

import projektkurs.cutscene.CutSceneManager;
import projektkurs.lib.Sprites;
import projektkurs.raster.extra.ExtraInformation;
import projektkurs.raster.extra.ExtraInformationFire;
import projektkurs.raster.extra.IHasExtraInformation;
import projektkurs.render.AnimationFrame;
import projektkurs.render.Screen;
import projektkurs.world.Spielfeld;

/**
 * Feuer.
 */
public class FireRaster extends AnimatedRaster implements IHasExtraInformation {

	/**
	 * Konstruktor.
	 *
	 * @param id ID
	 */
	public FireRaster(int id) {
		super(id, "fire", AnimationFrame.getSynchronousAnimation(Sprites.fire, 5));
	}

	@Override
	public ExtraInformation createExtraInformation(Spielfeld map, int x, int y) {
		return new ExtraInformationFire(map, x, y);
	}

	@Override
	public void render(Screen screen, Spielfeld map, int x, int y) {
		ExtraInformation extra = map.getExtraInformationAt(x, y);
		if (extra instanceof ExtraInformationFire) {
			AbstractRaster r = ((ExtraInformationFire) extra).getBackground();
			if (r != null) {
				r.render(screen, map, x, y);
			}
		}
		super.render(screen, map, x, y);
	}

	@Override
	public void renderCutScene(Screen screen, int x, int y) {
		ExtraInformation extra = CutSceneManager.getMap().getExtraInformationAt(x, y);
		if (extra instanceof ExtraInformationFire) {
			AbstractRaster r = ((ExtraInformationFire) extra).getBackground();
			if (r != null) {
				r.renderCutScene(screen, x, y);
			}
		}
		super.renderCutScene(screen, x, y);
	}
}
