package projektkurs.cutscene;

import projektkurs.Main;
import projektkurs.cutscene.action.ConditionedExitAction;
import projektkurs.cutscene.action.DebugAction;
import projektkurs.cutscene.condition.TickCondition;
import projektkurs.cutscene.condition.TickCondition.TickConditionType;
import projektkurs.cutscene.render.CutsceneRender;
import projektkurs.cutscene.render.CutsceneRenderHelper;
import projektkurs.lib.Integers;
import projektkurs.util.Logger;

/**
 * Managt die aktuell laufende CutScene
 */
public final class CutSceneManager {

	private static CutScene currCutScene;
	private static CutsceneRender currCutSceneRender;
	private static CutsceneRenderHelper currCutSceneRenderHelper;
	private static double delta;
	private static int fps, ups;

	public static CutScene getCurrentCutScene() {
		return currCutScene;
	}

	public static CutsceneRender getCurrentCutSceneRender() {
		return currCutSceneRender;
	}

	public static CutsceneRenderHelper getCurrentCutSceneRenderHelper() {
		return currCutSceneRenderHelper;
	}

	public static double getDelta() {
		return delta;
	}

	public static int getFPS() {
		return fps;
	}

	public static int getUPS() {
		return ups;
	}

	public static boolean isRunning() {
		return currCutScene != null;
	}

	public static void startCutScene(CutScene cutScene) {

		if (!isRunning()) {
			Logger.info("Starting CutScene");
			Main.pause();

			currCutScene = cutScene;
			currCutSceneRenderHelper = new CutsceneRenderHelper(Main
					.getRenderHelper().getSight());
			currCutSceneRender = new CutsceneRender(currCutSceneRenderHelper);

			final double nsPerTick = 1000000000D / Integers.UPS;
			fps = 0;
			ups = Integers.UPS;
			int loops = 0, frames = 0;
			delta = 0D;
			long lastTime = System.nanoTime();
			long lastTimer = System.nanoTime();

			while (!currCutScene.isFinished()) {
				long time = System.nanoTime();
				delta += (time - lastTime) / nsPerTick;
				lastTime = time;

				while (delta >= 1) {
					loops++;
					currCutScene.update();
					delta--;
				}

				frames++;
				currCutSceneRender.update();

				if (System.nanoTime() - lastTimer >= 1000000000) {
					lastTimer += 1000000000;
					ups = loops;
					fps = frames;
					frames = 0;
					loops = 0;
				}

			}

			currCutScene = null;
			Main.resume();
			Logger.info("Finished CutScene");
		}

	}

	public static CutScene TEST() {
		CutScene ret = new CutScene();
		ret.registerTickAction(new DebugAction());
		ret.registerTickAction(new ConditionedExitAction(new TickCondition(
				TickConditionType.GREATER, 600)));
		return ret;
	}

	private CutSceneManager() {
	}
}
