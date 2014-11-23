package projektkurs.cutscene.condition;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;

/**
 *
 *
 */
public class TrueCondition extends Condition {

  @Override
  public boolean isTrue(Action action, CutScene cutScene) {
    return true;
  }

}
