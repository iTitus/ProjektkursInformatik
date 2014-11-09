package projektkurs.world.raster;

import java.awt.Graphics2D;

import projektkurs.Main;
import projektkurs.render.AnimationFrame;
import projektkurs.util.RenderUtil;

/**
 * Ein einfaches animiertes Raster
 */
public class AnimatedRaster extends SimpleRaster {

	private final AnimationFrame[] animationFrames;
	private final int animationLength;

	/**
	 * 
	 * @param animationFrames
	 */
	public AnimatedRaster(AnimationFrame... animationFrames) {
		super(null);
		this.animationFrames = animationFrames;
		int animationLength = 0;
		for (int i = 0; i < animationFrames.length; i++) {
			animationLength += animationFrames[i].getAnimationTime();
		}
		this.animationLength = animationLength;
	}

	@Override
	public void render(Graphics2D g, int x, int y) {
		if (animationFrames != null) {
			int currentAnimationTime = Main.getRenderHelper().getRenderTicks()
					% animationLength;
			int totalAnimationTime = 0;
			for (int i = 0; i < animationFrames.length; i++) {
				if (currentAnimationTime >= totalAnimationTime) {
					totalAnimationTime += animationFrames[i].getAnimationTime();
					if (currentAnimationTime < totalAnimationTime) {
						RenderUtil.drawDefaultRaster(g,
								animationFrames[i].getImage(), x, y);
						break;
					}
				}
			}
		}
	}
}
