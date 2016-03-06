package projektkurs.util;

import java.lang.reflect.Method;

/**
 * Ein MethodInvoker. Fuehrt eine Methode ohne Rueckgabewerte aus.
 */
public class MethodInvoker extends MethodInvokerWithReturn<Void> {

    /**
     * Konstruktor.
     * @param m
     * Methode
     * @param objects
     * eventuelle Parameter
     */
    public MethodInvoker(Method m, Object... objects) {
        super(m, objects);
    }

    @Override
    public Void invoke() {
        ReflectionUtil.invokeStatic(m, objects);
        return null;
    }

}
