package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.cutscene.condition.Condition;

public class SetSightAction extends Action {

    private final int x, y;

    public SetSightAction(Condition condition, int x, int y) {
        super(condition);
        this.x = x;
        this.y = y;
    }

    public SetSightAction(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public void doAction(CutScene cutScene) {
        CutSceneManager.getCutSceneRenderHelper().setSight(x, y);
    }

}
