package projektkurs.util;

import java.lang.reflect.Method;

/**
 * Ein MethodInvoker. Fuehrt eine Methode mit Rueckgabewerte aus.
 * @param <T>
 * erwarteter Rueckgabewert.
 */
public class MethodInvokerWithReturn<T> {

    /**
     * Methode.
     */
    protected final Method m;
    /**
     * Eventuelle Parameter.
     */
    protected final Object[] objects;

    /**
     * Konstruktor.
     * @param m
     * Methode
     * @param objects
     * eventuelle Parameter
     */
    public MethodInvokerWithReturn(Method m, Object... objects) {
        this.m = m;
        this.objects = objects;
    }

    /**
     * Fuehrt die Methode aus.
     * @return Rueckgabewert
     */
    public T invoke() {
        return ReflectionUtil.<T>invokeStaticWithReturn(m, objects);
    }

}
