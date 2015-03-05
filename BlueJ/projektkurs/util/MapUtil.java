package projektkurs.util;

import projektkurs.lib.Raster;
import projektkurs.raster.AbstractRaster;
import projektkurs.util.Init.State;

public class MapUtil {

    private static AbstractRaster[] TREES;

    public static AbstractRaster[] getRanTree() {
        int i = MathUtil.randomInt((TREES.length - 3) / 4);
        return new AbstractRaster[] { TREES[4 * i], TREES[4 * i + 1], TREES[4 * i + 2], TREES[4 * i + 3] };
    }

    @Init(State.PRE)
    public static void init() {
        TREES = new AbstractRaster[] { Raster.tree_2nw, Raster.tree_2ne, Raster.tree_2se, Raster.tree_2sw, Raster.tree_4nw, Raster.tree_4ne, Raster.tree_4se, Raster.tree_4sw, Raster.tree_5nw, Raster.tree_5ne, Raster.tree_5se, Raster.tree_5sw, Raster.tree_7nw, Raster.tree_7ne, Raster.tree_7se, Raster.tree_7sw,
                Raster.tree_9nw, Raster.tree_9ne, Raster.tree_9se, Raster.tree_9sw };
    }

}
