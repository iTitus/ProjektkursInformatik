package projektkurs.world.raster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import projektkurs.Main;
import projektkurs.util.MathUtil;
import projektkurs.util.RenderUtil;

public class AnimatedRaster extends SimpleRaster {

	private final BufferedImage[] images;
	private final int animationSize;
	private int animationLength;
	private final int[] animationLengths;

	public AnimatedRaster(BufferedImage... images) {
		super(null);
		this.images = images;
		animationSize = (images != null ? images.length : 0);
		animationLengths = new int[animationSize];
		for (int i = 0; i < animationLengths.length; i++) {
			// TODO: Maybe an AnimationData Object for this?
			animationLengths[i] = MathUtil.ceilMul(Math.random(), 21) + 10;
			animationLength += animationLengths[i];
		}
		System.out.println(animationSize + " - "
				+ Arrays.asList(animationLengths));
	}

	@Override
	public void render(Graphics2D g, int x, int y) {
		if (animationLength > 0) {
			int currentAnimationTime = Main.getRenderHelper().getRenderTicks()
					% animationLength;
			int totalAnimationTime = 0;
			for (int i = 0; i < animationLengths.length; i++) {
				if (currentAnimationTime >= totalAnimationTime) {
					totalAnimationTime += animationLengths[i];
					if (currentAnimationTime <= totalAnimationTime) {
						RenderUtil.drawDefaultRaster(g, images[i], x, y);
						break;
					}
				}
			}
		}
	}
}
