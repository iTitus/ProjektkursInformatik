package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.cutscene.condition.Condition;

/**
 * Eine Action, die das Sichtfeld bewegt.
 */
public class ConditionedMoveSightAction extends Action {

    /**
     * Sichtfeldbewegung in x-Richtung.
     */
    private final int dx;
    /**
     * Sichtfeldbewegung in y-Richtung.
     */
    private final int dy;

    /**
     * Konstruktor.
     *
     * @param condition
     *            Ausführbedingung
     * @param dx
     *            Sichtfeldbewegung in x-Richtung
     * @param dy
     *            Sichtfeldbewegung in y-Richtung
     */
    public ConditionedMoveSightAction(Condition condition, int dx, int dy) {
        super(condition);
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void doAction(CutScene cutScene) {
        CutSceneManager.getCutSceneRenderHelper().moveSight(dx, dy);
    }
}
