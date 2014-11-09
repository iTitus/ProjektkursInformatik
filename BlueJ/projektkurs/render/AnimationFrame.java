package projektkurs.render;

import java.awt.image.BufferedImage;

/**
 *
 */
public class AnimationFrame {

	private final BufferedImage image;
	private final int animationTime;

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
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * 
	 * @return
	 */
	public int getAnimationTime() {
		return animationTime;
	}

}
