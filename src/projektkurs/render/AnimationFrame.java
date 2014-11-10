package projektkurs.render;

import java.awt.image.BufferedImage;

/**
 *
 */
public class AnimationFrame {

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
