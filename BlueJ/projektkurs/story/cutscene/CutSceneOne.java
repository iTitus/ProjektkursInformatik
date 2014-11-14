package projektkurs.story.cutscene;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.ConditionedExitAction;
import projektkurs.cutscene.action.ConditionedMoveAction;
import projektkurs.cutscene.action.ConditionedMoveSightAction;
import projektkurs.cutscene.action.DebugAction;
import projektkurs.cutscene.action.SpawnAction;
import projektkurs.cutscene.condition.TickCondition;
import projektkurs.cutscene.condition.TickCondition.TickConditionType;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.lib.Images;

/**
 * No. 1
 */
public class CutSceneOne {

	@SuppressWarnings("unused")
	public static CutScene cutSceneOne() {
		CutScene ret = new CutScene();

		CutSceneObject auto = new CutSceneObject(Images.item_42, 10, 10, 3, 3);
		CutSceneObject auto2 = new CutSceneObject(Images.item_42, 0, 0, 1, 1);

		Actions: {
			ret.registerStartupAction(new SpawnAction(auto));
			ret.registerStartupAction(new SpawnAction(auto2));
			ret.registerTickAction(new ConditionedMoveAction(new TickCondition(
					TickConditionType.MODULO_0, 20), auto, 1, 1));
			ret.registerTickAction(new ConditionedMoveSightAction(
					new TickCondition(TickConditionType.MODULO_0, 20), 1, 1));
			ret.registerTickAction(new DebugAction());
			ret.registerTickAction(new ConditionedExitAction(new TickCondition(
					TickConditionType.GREATER, 600)));
			ret.registerTickAction(new ConditionedMoveAction(new TickCondition(
					TickConditionType.MODULO_0, 10), auto2, 1, 1));
		}
		return ret;
	}

}
