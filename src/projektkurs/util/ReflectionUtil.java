package projektkurs.util;

import java.lang.reflect.Method;

/**
 * Helperklasse für Reflection
 */
public class ReflectionUtil {

	/**
	 * @param cls
	 * @param methodName
	 * @param args
	 * @return
	 */
	public static Method getMethod(Class<?> cls, String methodName,
			Class<?>... args) {
		try {
			return cls.getDeclaredMethod(methodName, args);
		} catch (Throwable t) {
			Logger.logThrowable("Could not get method '" + cls + "."
					+ methodName + "'", t);
			return null;
		}
	}

	/**
	 * @param m
	 * @param args
	 * @return
	 */
	public static Object invokeStatic(Method m, Object... args) {
		try {
			return m.invoke(null, args);
		} catch (Throwable t) {
			Logger.logThrowable("Could not invoke method '" + m + "'", t);
			return null;
		}
	}

}
