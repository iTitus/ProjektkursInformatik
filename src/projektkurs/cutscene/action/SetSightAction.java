package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.CutSceneManager;

public class SetSightAction extends Action {

    private int x, y;

    public SetSightAction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void doAction(CutScene cutScene) {
        CutSceneManager.getCutSceneRenderHelper().setSight(x, y);
    }

}
