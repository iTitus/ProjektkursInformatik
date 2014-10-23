package projektkurs.cutscene;

import projektkurs.Main;
import projektkurs.cutscene.action.ConditionedExitAction;
import projektkurs.cutscene.condition.TickCondition;
import projektkurs.cutscene.condition.TickCondition.TickConditionType;
import projektkurs.lib.Integers;

/**
 * Managt die aktuell laufende CutScene
 * 
 */
public final class CutSceneManager {

	private static CutScene currCutScene;

	private static long time;

	public static void startCutScene(CutScene cutScene) {

		Main.pause();

		currCutScene = cutScene;

		time = 0L;

		while (!currCutScene.isFinished()) {
			if (System.currentTimeMillis() - time > Integers.TICK_TIME) {
				time = System.currentTimeMillis();
				currCutScene.update();
			}

		}

		Main.resume();

	}

	public static CutScene TEST() {
		CutScene ret = new CutScene();
		ret.registerTickAction(new ConditionedExitAction(new TickCondition(
				TickConditionType.GREATER, 100)));
		return ret;
	}

	public static void update() {

		if (currCutScene != null) {

			currCutScene.update();

			if (currCutScene.isFinished()) {
				Main.pause();
				currCutScene = null;
			}

		}
	}

	private CutSceneManager() {
	}
}
