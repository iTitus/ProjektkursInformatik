package projektkurs.raster;

import projektkurs.Main;
import projektkurs.render.AnimationFrame;
import projektkurs.render.Screen;
import projektkurs.util.RenderUtil;
import projektkurs.world.Spielfeld;

/**
 * Ein einfaches animiertes Raster.
 */
public class AnimatedRaster extends AbstractRaster {

	/**
	 * Alle AnimationFrame.
	 */
	private final AnimationFrame[] animationFrames;
	/**
	 * Gesamtlaenge der Animation.
	 */
	private final int totalAnimationDuration;

	/**
	 * Konstruktor.
	 *
	 * @param id              ID
	 * @param name            Name
	 * @param animationFrames alle AnimationFrames
	 */
	public AnimatedRaster(int id, String name, AnimationFrame... animationFrames) {
		super(id, name);
		this.animationFrames = animationFrames;
		int totalAnimationDuration = 0;
		for (AnimationFrame animationFrame : animationFrames) {
			totalAnimationDuration += animationFrame.getAnimationFrameDuration();
		}
		this.totalAnimationDuration = totalAnimationDuration;
	}

	@Override
	public void render(Screen screen, Spielfeld map, int x, int y) {
		if (animationFrames != null && animationFrames.length > 0) {
			int animationTime = Main.getTicks() % totalAnimationDuration;
			int totalAnimationTime = 0;
			for (AnimationFrame animationFrame : animationFrames) {
				if (animationTime >= totalAnimationTime) {
					totalAnimationTime += animationFrame.getAnimationFrameDuration();
					if (animationTime < totalAnimationTime) {
						RenderUtil.drawDefaultRaster(screen, animationFrame.getSprite(), x, y);
						break;
					}
				}
			}
		}
	}

	@Override
	public void renderCutScene(Screen screen, int x, int y) {
		if (animationFrames != null && animationFrames.length > 0) {
			int animationTime = Main.getTicks() % totalAnimationDuration;
			int totalAnimationTime = 0;
			for (AnimationFrame animationFrame : animationFrames) {
				if (animationTime >= totalAnimationTime) {
					totalAnimationTime += animationFrame.getAnimationFrameDuration();
					if (animationTime < totalAnimationTime) {
						RenderUtil.drawCutSceneRaster(screen, animationFrame.getSprite(), x, y);
						break;
					}
				}
			}
		}
	}
}
