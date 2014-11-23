package projektkurs.cutscene.action;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.condition.Condition;
import projektkurs.cutscene.condition.TrueCondition;

/**
 * Abstrakte Action.
 */
public abstract class Action {

  /**
   * Ausführbedingung.
   */
  private final Condition condition;

  /**
   * Konstruktor mit der Ausführbedingung 'TrueCondition'.
   */
  public Action() {
    this(new TrueCondition());
  }

  /**
   * Konstruktor.
   *
   * @param condition
   *          Ausführbedingung
   */
  public Action(Condition condition) {
    if (condition != null) {
      this.condition = condition;
    } else {
      this.condition = new TrueCondition();
    }
  }

  /**
   * Führt die Action aus.
   *
   * @param cutScene
   *          Aktuelle CutScene
   */
  public abstract void doAction(CutScene cutScene);

  /**
   * Die Bedingung, um diese Action auszuführen.
   *
   * @return Condition
   */
  public final Condition getCondition() {
    return condition;
  }

  /**
   * Soll diese Action ausgeführt werden.
   *
   * @param cutScene
   *          Aktuelle CutScene
   * @return true, wenn ja; false, wenn nein
   */
  public final boolean shouldDoAction(CutScene cutScene) {
    return condition.isTrue(this, cutScene);
  }
}
