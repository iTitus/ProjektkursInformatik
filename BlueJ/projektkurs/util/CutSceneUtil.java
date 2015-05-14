package projektkurs.util;

import java.awt.image.BufferedImage;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.ConditionedDeSpawnAction;
import projektkurs.cutscene.condition.Condition;
import projektkurs.cutscene.condition.DelayedCondition;
import projektkurs.cutscene.object.CutSceneObject;
import projektkurs.lib.Images;
import projektkurs.util.Init.State;

@SuppressWarnings("deprecation")
public final class CutSceneUtil {

	private static BufferedImage[] TREES;

	private CutSceneUtil() {
	}

	public static BufferedImage getRandomTree() {
		return TREES[MathUtil.nextInt(TREES.length)];
	}

	@Init(State.PRE)
	public static void init() {
		TREES = new BufferedImage[]{Images.baum1, Images.baum2, Images.baum3, Images.baum4, Images.baum5, Images.baum6, Images.baum7, Images.baum8, Images.baum9, Images.baum10, Images.baum11, Images.baum12, Images.baum13};
	}

	public static void registerAnimation(CutScene cutScene, int difTime, Condition condition, CutSceneObject... objects) {
		for (int i = 0; i < objects.length - 1; i++) {
			cutScene.registerTickAction(new ConditionedDeSpawnAction(new DelayedCondition(condition, i * 8), objects[i]));
			cutScene.registerTickAction(new ConditionedDeSpawnAction(new DelayedCondition(condition, i * 8), objects[i + 1]));
		}
	}
}
