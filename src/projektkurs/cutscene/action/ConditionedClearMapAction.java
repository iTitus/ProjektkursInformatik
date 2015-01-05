package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.cutscene.condition.Condition;
import projektkurs.raster.AbstractRaster;

public class ConditionedClearMapAction extends Action {

    private AbstractRaster abstractRaster;

    public ConditionedClearMapAction(Condition condition, AbstractRaster abstractRaster) {
        super(condition);
        this.abstractRaster = abstractRaster;
    }

    @Override
    public void doAction(CutScene cutScene) {
        for (int i = 0; i < CutSceneManager.getMap().getMapSizeX(); i++) {
            for (int j = 0; j < CutSceneManager.getMap().getMapSizeY(); j++) {
                CutSceneManager.getMap().setRasterAt(i, j, abstractRaster);
            }
        }
    }
}
