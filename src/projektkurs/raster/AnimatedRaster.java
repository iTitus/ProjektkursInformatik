package projektkurs.raster;

import java.awt.Graphics2D;

import projektkurs.Main;
import projektkurs.render.AnimationFrame;
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
     * Gesamtlänge der Animation.
     */
    private final int totalAnimationDuration;

    /**
     * Konstruktor.
     *
     * @param id
     *            ID
     * @param name
     *            Name
     * @param animationFrames
     *            alle AnimationFrames
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
    public void render(Graphics2D g, Spielfeld map, int x, int y) {
        if (animationFrames != null && animationFrames.length > 0) {
            int animationTime = Main.getTicks() % totalAnimationDuration;
            int totalAnimationTime = 0;
            for (AnimationFrame animationFrame : animationFrames) {
                if (animationTime >= totalAnimationTime) {
                    totalAnimationTime += animationFrame.getAnimationFrameDuration();
                    if (animationTime < totalAnimationTime) {
                        RenderUtil.drawDefaultRaster(g, animationFrame.getImage(), x, y);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void renderCutScene(Graphics2D g, int x, int y) {
        if (animationFrames != null && animationFrames.length > 0) {
            int animationTime = Main.getTicks() % totalAnimationDuration;
            int totalAnimationTime = 0;
            for (AnimationFrame animationFrame : animationFrames) {
                if (animationTime >= totalAnimationTime) {
                    totalAnimationTime += animationFrame.getAnimationFrameDuration();
                    if (animationTime < totalAnimationTime) {
                        RenderUtil.drawCutSceneRaster(g, animationFrame.getImage(), x, y);
                        break;
                    }
                }
            }
        }
    }
}
