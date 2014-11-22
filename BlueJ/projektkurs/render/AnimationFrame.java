package projektkurs.render;

import java.awt.image.BufferedImage;

/**
 * Ein Animationsframe.
 */
public class AnimationFrame {

    /**
     * Statische Hilsfmethode, die aus einem gegebenen BufferedImage[] und einer gegebenen frameTime eine gleichmäßige Animation macht.
     *
     * @param images
     *            BufferedImage[]
     * @param frameTime
     *            Zei, die ein Bild angezeigt wird, in Renderticks
     * @return AnimationFrame[]
     */
    public static AnimationFrame[] getSynchronousAnimation(BufferedImage[] images, int frameTime) {
        AnimationFrame[] frames = new AnimationFrame[images.length];
        for (int i = 0; i < frames.length; i++) {
            frames[i] = new AnimationFrame(images[i], frameTime);
        }
        return frames;
    }

    /**
     * Zeit, die dieser AnimationFrame angezeigt wird, in Renderticks.
     */
    private final int           animationTime;

    /**
     * Bild.
     */
    private final BufferedImage image;

    /**
     * Konstruktor.
     *
     * @param image
     *            Bild
     * @param animationTime
     *            Zeitspanne, die dieser AnimationFrame angezeigt werden soll
     */
    public AnimationFrame(BufferedImage image, int animationTime) {
        this.image = image;
        this.animationTime = animationTime;
    }

    /**
     * Zeit, die dieser AnimationFrame angezeigt wird, in Renderticks.
     *
     * @return Animationszeit.
     */
    public int getAnimationTime() {
        return animationTime;
    }

    /**
     * Bild.
     *
     * @return Bild
     */
    public BufferedImage getImage() {
        return image;
    }

}
