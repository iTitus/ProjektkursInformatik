package projektkurs.world.raster;

import java.awt.Graphics2D;

import projektkurs.Main;
import projektkurs.render.AnimationFrame;
import projektkurs.util.RenderUtil;

public class AnimatedRaster extends SimpleRaster {

	private final AnimationFrame[] animationFrames;
	private final int animationLength;

	public AnimatedRaster(AnimationFrame... animations) {
		super(null);
		this.animationFrames = animations;
		int animationLength = 0;
		for (int i = 0; i < animations.length; i++) {
			animationLength += animations[i].getAnimationTime();
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
