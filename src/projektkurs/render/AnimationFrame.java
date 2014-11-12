package projektkurs.render;

import java.awt.image.BufferedImage;

/**
 *
 */
public class AnimationFrame {

	public static AnimationFrame[] getSynchronousAnimation(
			BufferedImage[] images, int frameTime) {
		AnimationFrame[] frames = new AnimationFrame[images.length];
		for (int i = 0; i < frames.length; i++) {
			frames[i] = new AnimationFrame(images[i], frameTime);
		}
		return frames;
	}
	private final int animationTime;

	private final BufferedImage image;

	/**
	 * 
	 * @param image
	 * @param animationTime
	 */
	public AnimationFrame(BufferedImage image, int animationTime) {
		this.image = image;
		this.animationTime = animationTime;
	}

	/**
	 * 
	 * @return
	 */
	public int getAnimationTime() {
		return animationTime;
	}

	/**
	 * 
	 * @return
	 */
	public BufferedImage getImage() {
		return image;
	}

}
