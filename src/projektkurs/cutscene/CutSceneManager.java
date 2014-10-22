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

		while (!currCutScene.isFinished()) {

			time = System.currentTimeMillis();
			ForSchleife: {
				while (true) {
					if (System.currentTimeMillis() - time > Integers.TICK_TIME) {
						currCutScene.update();
						break ForSchleife;
					}
				}
			}
			// FIXME: Ineffzient!!!!!!!!!!!!!!!!
			// some sleepy thing

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
