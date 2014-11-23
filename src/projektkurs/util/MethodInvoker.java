package projektkurs.util;

import java.lang.reflect.Method;

/**
 * Ein MethodInvoker. Führt eine Methode ohne Rückgabewerte aus.
 */
public class MethodInvoker extends MethodInvokerWithReturn<Void> {

  /**
   * Konstruktor.
   *
   * @param m
   *          Methode
   * @param objects
   *          eventuelle Parameter
   */
  public MethodInvoker(Method m, Object... objects) {
    super(m, objects);
  }

}
