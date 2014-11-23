package projektkurs.cutscene.condition;

/**
 * Eine Condition, die mehrere andere Conditions in sich vereint.
 */
public abstract class CombinedCondition extends Condition {

  /**
   * Die Conditions.
   */
  protected final Condition[] conditions;

  /**
   * Konstruktor.
   *
   * @param conditions
   *          Zu prüfende Conditions.
   */
  public CombinedCondition(Condition... conditions) {
    this.conditions = conditions;
  }

}
