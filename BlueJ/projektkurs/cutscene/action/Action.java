package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.condition.Condition;
import projektkurs.cutscene.condition.TrueCondition;

/**
 * Abstrakte Action
 * 
 */
public abstract class Action {

	/**
	 * Tu es einfach!
	 * 
	 * @param cutScene
	 */
	public abstract void doAction(CutScene cutScene);

	/**
	 * 
	 * @param condition
	 */
	public Condition getCondition() {
		return new TrueCondition();
	}

	/**
	 * Sollst du es diesmal tun?
	 * 
	 * @param cutScene
	 * @return
	 */
	public boolean shouldDoAction(CutScene cutScene) {
		return getCondition().isTrue(this, cutScene);
	}
}
