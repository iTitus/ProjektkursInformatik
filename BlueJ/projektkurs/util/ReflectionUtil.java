package projektkurs.util;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Helperklasse für Reflection
 */
public class ReflectionUtil {

	/**
	 * 
	 * @param classes
	 * @param annotationClass
	 * @param modifiers
	 * @return
	 */
	public static ArrayList<Method> getAllMethodsInClassesWithAnnotation(
			ArrayList<Class<?>> classes,
			Class<? extends Annotation> annotationClass, int... modifiers) {
		ArrayList<Method> methods = new ArrayList<Method>();
		for (Class<?> cls : classes) {
			methods.addAll(getMethodsInClassWithAnnotation(cls,
					annotationClass, modifiers));
		}
		return methods;
	}

	/**
	 * 
	 * @param packageName
	 * @return
	 */
	public static ArrayList<Class<?>> getClasses(String packageName) {
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		for (String path : getPathesFromClasspath()) {
			File fileOrDir = new File(path);
			if (fileOrDir.isDirectory())
				classes.addAll(getClassesFromDir(fileOrDir, packageName));
			if (fileOrDir.isFile()
					&& (fileOrDir.getName().toLowerCase().endsWith(".jar") || fileOrDir
							.getName().toLowerCase().endsWith(".zip")))
				classes.addAll(getClassesFromJar(fileOrDir, packageName));
		}
		return classes;
	}

	/**
	 * 
	 * @param dir
	 * @param packageName
	 * @return
	 */
	public static ArrayList<Class<?>> getClassesFromDir(File dir,
			String packageName) {
		if (packageName == null)
			packageName = "";
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		File dirSearched = new File(dir.getPath() + File.separator
				+ packageName.replace(".", "/"));
		if (dirSearched.isDirectory())
			getClassesFromFileOrDirIntern(true, dirSearched, packageName,
					classes);
		return classes;
	}

	/**
	 * 
	 * @param jarFile
	 * @param packageName
	 * @return
	 */
	public static ArrayList<Class<?>> getClassesFromJar(File jarFile,
			String packageName) {
		if (packageName == null)
			packageName = "";
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		String dirSearched = packageName.replace(".", "/");
		try (ZipFile zipFile = new ZipFile(jarFile)) {

			for (Enumeration<? extends ZipEntry> zipEntries = zipFile.entries(); zipEntries
					.hasMoreElements();) {
				String entryName = zipEntries.nextElement().getName();
				if (!entryName.startsWith(dirSearched)
						|| !entryName.toLowerCase().endsWith(".class"))
					continue;
				entryName = entryName.substring(0, entryName.length()
						- ".class".length());
				entryName = entryName.replace("/", ".");
				try {
					Class<?> clazz = Class.forName(entryName);
					classes.add(clazz);
				} catch (Throwable t) {
					Logger.logThrowable("Unable to get class '" + entryName
							+ "' from jar", t);
				}
			}
		} catch (Throwable t) {
			Logger.logThrowable("Unable to read ZIP-File '" + jarFile + "'", t);
		}
		return classes;

	}

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
			Logger.logThrowable("Unable to get method '" + cls + "."
					+ methodName + "'", t);
			return null;
		}
	}

	/**
	 * 
	 * @param cls
	 * @param annotationClass
	 * @param modifiers
	 * @return
	 */
	public static ArrayList<Method> getMethodsInClassWithAnnotation(
			Class<?> cls, Class<? extends Annotation> annotationClass,
			int... modifiers) {
		ArrayList<Method> methods = new ArrayList<Method>();
		for (Method m : cls.getDeclaredMethods()) {
			if (m.isAnnotationPresent(annotationClass)
					&& hasAllModifiers(m, modifiers)) {
				methods.add(m);
			}
		}
		return methods;
	}

	/**
	 * 
	 * @return
	 */
	public static ArrayList<String> getPathesFromClasspath() {
		String classpath = System.getProperty("java.class.path");
		String pathseparator = System.getProperty("path.separator");
		StringTokenizer tokenizer = new StringTokenizer(classpath,
				pathseparator);
		ArrayList<String> pathes = new ArrayList<String>();
		while (tokenizer.hasMoreElements()) {
			pathes.add(tokenizer.nextToken());
		}
		return pathes;
	}

	/**
	 * 
	 * @param m
	 * @param modifiers
	 * @return
	 */
	public static boolean hasAllModifiers(Method m, int... modifiers) {
		boolean ret = true;
		for (int mod : modifiers) {
			if ((mod & m.getModifiers()) == 0)
				ret = false;
		}
		return ret;
	}

	/**
	 * 
	 * @param o
	 * @param m
	 * @param args
	 */
	public static void invoke(Object o, Method m, Object... args) {
		try {
			m.invoke(o, args);
		} catch (Throwable t) {
			Logger.logThrowable("Unable to invoke method '" + m + "'", t);
		}
	}

	/**
	 * @param m
	 * @param args
	 */
	public static void invokeStatic(Method m, Object... args) {
		try {
			m.invoke(null, args);
		} catch (Throwable t) {
			Logger.logThrowable("Unable to invoke method '" + m + "'", t);
		}
	}

	/**
	 * 
	 * @param m
	 * @param args
	 * @return
	 */
	public static Object invokeStaticWithReturn(Method m, Object... args) {
		try {
			return m.invoke(null, args);
		} catch (Throwable t) {
			Logger.logThrowable("Unable to invoke method '" + m + "'", t);
			return null;
		}
	}

	/**
	 * 
	 * @param o
	 * @param m
	 * @param args
	 * @return
	 */
	public static Object invokeWithReturn(Object o, Method m, Object... args) {
		try {
			return m.invoke(o, args);
		} catch (Throwable t) {
			Logger.logThrowable("Unable to invoke method '" + m + "'", t);
			return null;
		}
	}

	/**
	 * 
	 * @param cls
	 * @return
	 */
	public static <T> T newInstance(Class<T> cls) {
		try {
			return cls.newInstance();
		} catch (Throwable t) {
			Logger.logThrowable("Unable to instantiate class '" + cls + "'", t);
			return null;
		}
	}

	/**
	 * 
	 * @param first
	 * @param fileOrDir
	 * @param packageName
	 * @param classes
	 */
	private static void getClassesFromFileOrDirIntern(boolean first,
			File fileOrDir, String packageName, ArrayList<Class<?>> classes) {
		if (fileOrDir.isDirectory()) {
			if (!first)
				packageName = (packageName + "." + fileOrDir.getName())
						.replaceAll("^\\.", "");
			for (String subFileOrDir : fileOrDir.list())
				getClassesFromFileOrDirIntern(false, new File(fileOrDir,
						subFileOrDir), packageName, classes);
		} else {
			if (fileOrDir.getName().toLowerCase().endsWith(".class")) {
				String classFile = fileOrDir.getName();
				classFile = packageName
						+ "."
						+ classFile.substring(0,
								classFile.length() - ".class".length());
				try {
					Class<?> clazz = Class.forName(classFile);
					classes.add(clazz);
				} catch (Throwable t) {
					Logger.logThrowable("Unable to get class '" + classFile
							+ "'", t);
				}
			}
		}
	}
}
