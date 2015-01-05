package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.cutscene.condition.Condition;
import projektkurs.raster.AbstractRaster;

public class ConditionedRasterChangeAction extends Action {

    private final AbstractRaster abstractRaster;
    private final int x;
    private final int y;

    public ConditionedRasterChangeAction(int x, int y, Condition condition, AbstractRaster abstractRaster) {
        super(condition);
        this.x = x;
        this.y = y;
        this.abstractRaster = abstractRaster;
    }

    @Override
    public void doAction(CutScene cutScene) {
        CutSceneManager.getMap().setRasterAt(x, y, abstractRaster);
    }

}
