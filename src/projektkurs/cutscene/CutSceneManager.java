package projektkurs.cutscene;

import projektkurs.Main;
import projektkurs.cutscene.action.ConditionedExitAction;
import projektkurs.cutscene.condition.TickCondition;
import projektkurs.cutscene.condition.TickCondition.TickConditionType;
import projektkurs.cutscene.render.CutsceneRender;
import projektkurs.cutscene.render.CutsceneRenderHelper;
import projektkurs.lib.Integers;

/**
 * Managt die aktuell laufende CutScene
 */
public final class CutSceneManager {

	private static CutScene currCutScene;
	private static CutsceneRender currCutSceneRender;
	private static CutsceneRenderHelper currCutSceneRenderHelper;

	private static long time;

	public static CutScene getCurrentCutScene() {
		return currCutScene;
	}

	public static CutsceneRender getCurrentCutSceneRender() {
		return currCutSceneRender;
	}

	// public static void update() {
	//
	// if (isRunning()) {
	//
	// currCutScene.update();
	//
	// if (currCutScene.isFinished()) {
	// Main.pause();
	// currCutScene = null;
	// }
	//
	// }
	// }

	public static CutsceneRenderHelper getCurrentCutSceneRenderHelper() {
		return currCutSceneRenderHelper;
	}

	public static boolean isRunning() {
		return currCutScene != null;
	}

	public static void startCutScene(CutScene cutScene) {

		if (!isRunning()) {

			Main.pause();

			currCutScene = cutScene;
			currCutSceneRenderHelper = new CutsceneRenderHelper(Main
					.getRenderHelper().getSight());
			currCutSceneRender = new CutsceneRender(currCutSceneRenderHelper);

			time = 0L;

			while (!currCutScene.isFinished()) {
				if (System.currentTimeMillis() - time > Integers.UPS) {
					time = System.currentTimeMillis();
					currCutScene.update();
					currCutSceneRender.update();
				}

			}

			Main.resume();
		}

	}

	public static CutScene TEST() {
		CutScene ret = new CutScene();
		ret.registerTickAction(new ConditionedExitAction(new TickCondition(
				TickConditionType.GREATER, 100)));
		return ret;
	}

	private CutSceneManager() {
	}
}
