package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.condition.Condition;
import projektkurs.cutscene.object.CutSceneObject;

public class ConditionedMoveAction extends Action {

	private Condition condition;
	private CutSceneObject object;
	private int dx, dy;

	public ConditionedMoveAction(Condition condition, CutSceneObject object,
			int dx, int dy) {
		this.condition = condition;
		this.object = object;
		this.dx = dx;
		this.dy = dy;
	}

	@Override
	public void doAction(CutScene cutScene) {
		object.moveBy(dx, dy);
	}

	@Override
	public Condition getCondition() {
		return condition;
	}

}
