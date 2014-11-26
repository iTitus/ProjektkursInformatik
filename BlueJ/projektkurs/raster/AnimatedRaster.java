package projektkurs.raster;

import java.awt.Graphics2D;

import projektkurs.Main;
import projektkurs.render.AnimationFrame;
import projektkurs.util.RenderUtil;

/**
 * Ein einfaches animiertes Raster.
 */
public class AnimatedRaster extends SimpleRaster {

    /**
     * Alle AnimationFrame.
     */
    private final AnimationFrame[] animationFrames;
    /**
     * Gesamtlänge der Animation.
     */
    private final int animationLength;

    /**
     * Konstruktor.
     *
     * @param animationFrames
     *            alle AnimationFrames
     */
    public AnimatedRaster(AnimationFrame... animationFrames) {
        super(null);
        this.animationFrames = animationFrames;
        int animationLength = 0;
        for (AnimationFrame animationFrame : animationFrames) {
            animationLength += animationFrame.getAnimationTime();
        }
        this.animationLength = animationLength;
    }

    @Override
    public void render(Graphics2D g, int x, int y) {
        if (animationFrames != null) {
            int currentAnimationTime = Main.getRenderHelper().getRenderTicks() % animationLength;
            int totalAnimationTime = 0;
            for (AnimationFrame animationFrame : animationFrames) {
                if (currentAnimationTime >= totalAnimationTime) {
                    totalAnimationTime += animationFrame.getAnimationTime();
                    if (currentAnimationTime < totalAnimationTime) {
                        RenderUtil.drawDefaultRaster(g, animationFrame.getImage(), x, y);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void renderCutScene(Graphics2D g, int x, int y) {
        if (animationFrames != null) {
            int currentAnimationTime = Main.getRenderHelper().getRenderTicks() % animationLength;
            int totalAnimationTime = 0;
            for (AnimationFrame animationFrame : animationFrames) {
                if (currentAnimationTime >= totalAnimationTime) {
                    totalAnimationTime += animationFrame.getAnimationTime();
                    if (currentAnimationTime < totalAnimationTime) {
                        RenderUtil.drawCutSceneRaster(g, animationFrame.getImage(), x, y);
                        break;
                    }
                }
            }
        }
    }
}
