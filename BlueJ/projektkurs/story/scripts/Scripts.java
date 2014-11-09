package projektkurs.story.scripts;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import projektkurs.Main;
import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.cutscene.action.ConditionedExitAction;
import projektkurs.cutscene.action.ConditionedMoveSightAction;
import projektkurs.cutscene.action.DebugAction;
import projektkurs.cutscene.condition.TickCondition;
import projektkurs.cutscene.condition.TickCondition.TickConditionType;
import projektkurs.lib.Images;
import projektkurs.util.I18n;
import projektkurs.util.RenderUtil;

public class Scripts {

	public static void loose() {
		Main.pause();
		JOptionPane.showOptionDialog(null,
				I18n.getString("description.meltdown"),
				I18n.getString("description.meltdown"), 0,
				JOptionPane.ERROR_MESSAGE, new ImageIcon(Images.nuke),
				new Object[] { I18n.getString("button.exit") }, null);
		Main.exit();
	}

	public static void win() {
		Main.pause();
		JOptionPane.showOptionDialog(null, I18n.getString("description.win"),
				I18n.getString("description.win"), 0,
				JOptionPane.ERROR_MESSAGE, new ImageIcon(Images.finish),
				new Object[] { I18n.getString("button.exit") }, null);
		Main.exit();
	}

	public static void testCutScene() {
		CutScene ret = new CutScene(100, 100);
		for (int x = 0; x < Main.getSpielfeld().getMapSizeX(); x++) {
			for (int y = 0; y < Main.getSpielfeld().getMapSizeY(); y++) {
				RenderUtil.getImage(Main.getSpielfeld().getRasterAt(x, y), x, y);
			}
		}
		ret.registerTickAction(new DebugAction());
		ret.registerTickAction(new ConditionedMoveSightAction(
				new TickCondition(TickConditionType.MODULO_0, 60), 1, 1));
		ret.registerTickAction(new ConditionedExitAction(new TickCondition(
				TickConditionType.GREATER, 600)));
		CutSceneManager.startCutScene(ret);
	}
}
