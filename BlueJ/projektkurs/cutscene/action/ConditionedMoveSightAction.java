package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.cutscene.condition.Condition;

public class ConditionedMoveSightAction extends Action {

	private Condition condition;
	private int dx, dy;

	public ConditionedMoveSightAction(Condition condition, int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
		this.condition = condition;
	}

	@Override
	public void doAction(CutScene cutScene) {
		CutSceneManager.getCurrentCutSceneRenderHelper().moveSight(dx, dy);
	}

	@Override
	public Condition getCondition() {
		return condition;
	}
}
