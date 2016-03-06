package projektkurs.render;

/**
 * Ein Animationsframe.
 */
public class AnimationFrame {

    /**
     * Statische Hilsfmethode, die aus einem gegebenen Sprite[] und einer gegebenen frameTime eine gleichmaessige Animation macht.
     * @param images
     * Sprite[]
     * @param frameTime
     * Zei, die ein Sprite angezeigt wird, in Renderticks
     * @return AnimationFrame[]
     */
    public static AnimationFrame[] getSynchronousAnimation(Sprite[] images, int frameTime) {
        AnimationFrame[] frames = new AnimationFrame[images.length];
        for (int i = 0; i < frames.length; i++) {
            frames[i] = new AnimationFrame(images[i], frameTime);
        }
        return frames;
    }

    /**
     * Zeit, die dieser AnimationFrame angezeigt wird, in Ticks.
     */
    private final int animationFrameDuration;

    /**
     * Sprite.
     */
    private final Sprite image;

    /**
     * Konstruktor.
     * @param image
     * Sprite
     * @param animationFrameDuration
     * Zeitspanne, die dieser AnimationFrame angezeigt werden soll
     */
    public AnimationFrame(Sprite image, int animationFrameDuration) {
        this.image = image;
        this.animationFrameDuration = animationFrameDuration;
    }

    /**
     * Zeit, die dieser AnimationFrame angezeigt wird, in Renderticks.
     * @return Animationszeit.
     */
    public int getAnimationFrameDuration() {
        return animationFrameDuration;
    }

    /**
     * Sprite.
     * @return Sprite
     */
    public Sprite getSprite() {
        return image;
    }

}
