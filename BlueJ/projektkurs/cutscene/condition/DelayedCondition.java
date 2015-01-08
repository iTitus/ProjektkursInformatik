package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;

public class DelayedCondition extends Condition {

    private final Condition condition;
    private int counter;
    private final int delay;

    public DelayedCondition(Condition condition, int delay) {
        this.delay = delay;
        this.condition = condition;
        counter = -1;
    }

    @Override
    public boolean isTrue(Action action, CutScene cutScene) {
        if (counter < 0 && condition.isTrue(action, cutScene)) {
            counter = cutScene.getElapsedTicks();
        }
        if (counter >= 0 && cutScene.getElapsedTicks() - counter >= delay) {
            counter = -1;
            return true;
        }
        return false;
    }
}
