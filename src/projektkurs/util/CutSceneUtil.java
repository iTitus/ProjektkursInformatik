package projektkurs.util;

import java.awt.image.BufferedImage;

import projektkurs.lib.Images;
import projektkurs.util.Init.State;

public final class CutSceneUtil {

    private static BufferedImage[] TREES;

    public static BufferedImage getRandomTree() {
        return TREES[MathUtil.nextInt(TREES.length)];
    }

    @Init(state = State.PRE)
    public static void init() {
        TREES = new BufferedImage[] { Images.baum1, Images.baum2, Images.baum3, Images.baum4, Images.baum5, Images.baum6, Images.baum7, Images.baum8, Images.baum9, Images.baum10, Images.baum11, Images.baum12, Images.baum13 };
    }

    private CutSceneUtil() {
    }
}
