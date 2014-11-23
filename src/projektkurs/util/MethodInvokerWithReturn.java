package projektkurs.util;

import java.lang.reflect.Method;

/**
 * Ein MethodInvoker. Führt eine Methode mit Rückgabewerte aus.
 *
 * @param <T>
 *          erwarteter Rückgabewert.
 */
public class MethodInvokerWithReturn<T> {

  /**
   * Methode.
   */
  private final Method   m;
  /**
   * Eventuelle Parameter.
   */
  private final Object[] objects;

  /**
   * Konstruktor.
   *
   * @param m
   *          Methode
   * @param objects
   *          eventuelle Parameter
   */
  public MethodInvokerWithReturn(Method m, Object... objects) {
    this.m = m;
    this.objects = objects;
  }

  /**
   * Führt die Methode aus.
   *
   * @return Rückgabewert
   */
  public T invoke() {
    return ReflectionUtil.<T>invokeStaticWithReturn(m, objects);
  }

}
