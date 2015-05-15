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
 * Helperklasse fuer Reflection.
 */
public final class ReflectionUtil {

    /**
     * Gibt alle Klassen im Classpath zurueck, die im gegebenen Package sind.
     *
     * @param packageName
     *            Name des Packages
     * @return alle Klassen
     */
    public static ArrayList<Class<?>> getClasses(String packageName) {
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        for (String path : getPathesFromClasspath()) {
            File fileOrDir = new File(path);
            if (fileOrDir.isDirectory()) {
                classes.addAll(getClassesFromDir(fileOrDir, packageName));
            }
            if (fileOrDir.isFile()) {
                String name = fileOrDir.getName().toLowerCase();
                if (name.endsWith(".jar") || name.endsWith(".zip")) {
                    classes.addAll(getClassesFromJarOrZip(fileOrDir, packageName));
                }
            }
        }
        return classes;
    }

    /**
     * Gibt alle Klassen aus einem Ordner zurueck, die im gegebenen Package sind.
     *
     * @param dir
     *            zu durchsuchender Ordner
     * @param packageName
     *            Name des Packages
     * @return alle Klassen
     */
    public static ArrayList<Class<?>> getClassesFromDir(File dir, String packageName) {
        if (packageName == null) {
            packageName = "";
        }
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        File dirSearched = new File(dir.getPath() + File.separator + packageName.replace(".", "/"));
        if (dirSearched.isDirectory()) {
            getClassesFromFileOrDirIntern(true, dirSearched, packageName, classes);
        }
        return classes;
    }

    /**
     * Liest eine kompressierte Datei (ZIP oder JAR) und findet alle Klassen im gegebenen Package.
     *
     * @param jarOrZipFile
     *            JAR oder ZIP Datei
     * @param packageName
     *            Name des Packages
     * @return alle Klassen
     */
    public static ArrayList<Class<?>> getClassesFromJarOrZip(File jarOrZipFile, String packageName) {
        if (packageName == null) {
            packageName = "";
        }
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        String dirSearched = packageName.replace(".", "/");
        try (ZipFile zipFile = new ZipFile(jarOrZipFile)) {

            for (Enumeration<? extends ZipEntry> zipEntries = zipFile.entries(); zipEntries.hasMoreElements();) {
                String entryName = zipEntries.nextElement().getName();
                if (!entryName.startsWith(dirSearched) || !entryName.toLowerCase().endsWith(".class")) {
                    continue;
                }
                entryName = entryName.substring(0, entryName.length() - ".class".length());
                entryName = entryName.replace("/", ".");
                try {
                    Class<?> clazz = Class.forName(entryName);
                    classes.add(clazz);
                } catch (Throwable t) {
                    Logger.logThrowable("Unable to get class '" + entryName + "' from zip or jar '" + jarOrZipFile + "'", t);
                }
            }
        } catch (Throwable t) {
            Logger.logThrowable("Unable to read from jar or zip '" + jarOrZipFile + "'", t);
        }
        return classes;

    }

    /**
     * Gibt die Methode aus der gegebenen Klasse mit dem gegebenen Namen und den gegebenen Argumenten zurueck.
     *
     * @param cls
     *            Klasse
     * @param methodName
     *            Name der Methode
     * @param args
     *            Argumente
     * @return Methode
     */
    public static Method getMethod(Class<?> cls, String methodName, Class<?>... args) {
        try {
            return cls.getDeclaredMethod(methodName, args);
        } catch (Throwable t) {
            Logger.logThrowable("Unable to get method '" + cls + "." + methodName + "'", t);
            return null;
        }
    }

    /**
     * Gibt die Methode aus der gegebenen Klasse mit dem gegebenen Namen, den gegebenen Argumenten zurueck und den gegebenenen Modifiern zurueck.
     *
     * @param cls
     *            Klasse
     * @param annotationClass
     *            Annotations-Klasse
     * @param modifiers
     *            Modifier
     * @return Methode
     */
    public static ArrayList<Method> getMethodInClassWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationClass, int... modifiers) {
        ArrayList<Method> methods = new ArrayList<Method>();
        for (Method m : cls.getDeclaredMethods()) {
            if (m.isAnnotationPresent(annotationClass) && hasAllModifiers(m, modifiers)) {
                methods.add(m);
            }
        }
        return methods;
    }

    /**
     * Gibt alle Methoden aus den gegebenen Klassen zurueck, die die gegebenen Annotationen besitzen und alle gegebenen Modifier haben.
     *
     * @param classes
     *            zu durchsuchende Klassen
     * @param annotationClass
     *            Annotations-Klasse
     * @param modifiers
     *            alle Modifiers
     * @return Methoden
     */
    public static ArrayList<Method> getMethodsInClassesWithAnnotation(ArrayList<Class<?>> classes, Class<? extends Annotation> annotationClass, int... modifiers) {
        ArrayList<Method> methods = new ArrayList<Method>();
        for (Class<?> cls : classes) {
            methods.addAll(getMethodInClassWithAnnotation(cls, annotationClass, modifiers));
        }
        return methods;
    }

    /**
     * Gibt alle Pfade zurueck, die im Classpath stehen.
     *
     * @return alle Pfade
     */
    public static ArrayList<String> getPathesFromClasspath() {
        StringTokenizer tokenizer = new StringTokenizer(System.getProperty("java.class.path"), System.getProperty("path.separator"));
        ArrayList<String> pathes = new ArrayList<String>();
        while (tokenizer.hasMoreElements()) {
            pathes.add(tokenizer.nextToken());
        }
        return pathes;
    }

    /**
     * Sind alle gegebenen Modifier an der gegebenen Methode praesent.
     *
     * @param m
     *            Methode
     * @param modifiers
     *            Modifier
     * @return true, wenn ja; false, wenn nein
     */
    public static boolean hasAllModifiers(Method m, int... modifiers) {
        boolean ret = true;
        for (int mod : modifiers) {
            if ((mod & m.getModifiers()) == 0) {
                ret = false;
            }
        }
        return ret;
    }

    /**
     * Fuehrt die gegebene Methode in dem gegebenen Objekt mit den gegegebenen Parametern aus. Ignoriert de Rueckgabewert.
     *
     * @param o
     *            Objekt
     * @param m
     *            Methode
     * @param args
     *            Parameter
     * @return Void
     */
    public static Void invoke(Object o, Method m, Object... args) {
        return invokeWithReturn(o, m, args);
    }

    /**
     * Fuehrt die gegebene statische Methode mit den gegegebenen Parametern aus. Ignoriert de Rueckgabewert.
     *
     * @param m
     *            Methode
     * @param args
     *            Parameter
     * @return Void
     */
    public static void invokeStatic(Method m, Object... args) {
        invokeStaticWithReturn(m, args);
    }

    /**
     * Fuehrt die gegebene statische Methode mit den gegegebenen Parametern aus.
     *
     * @param <T>
     *            der erwartete Rueckgabetyp
     * @param m
     *            Methode
     * @param args
     *            Parameter
     * @return Rueckgabewert
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokeStaticWithReturn(Method m, Object... args) {
        try {
            return (T) m.invoke(null, args);
        } catch (Throwable t) {
            Logger.logThrowable("Unable to invoke method '" + m + "'", t);
            return null;
        }
    }

    /**
     * Fuehrt die gegebene Methode in dem gegebenen Objekt mit den gegegebenen Parametern aus.
     *
     * @param <T>
     *            der erwartete Rueckgabetyp
     * @param o
     *            Objekt
     * @param m
     *            Methode
     * @param args
     *            Parameter
     * @return Rueckgabewert
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokeWithReturn(Object o, Method m, Object... args) {
        try {
            return (T) m.invoke(o, args);
        } catch (Throwable t) {
            Logger.logThrowable("Unable to invoke method '" + m + "'", t);
            return null;
        }
    }

    /**
     * Legt eine neue Instanz der gegebenen Klasse an.
     *
     * @param <T>
     *            Typ der Klasse
     * @param cls
     *            Klasse
     * @return Instanz
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
     * Legt eine neue Instanz der gegebenen Klasse an.
     *
     * @param <T>
     *            Typ der Klasse
     * @param cls
     *            Klasse
     * @param args
     *            Konstruktorargumente
     * @return Instanz
     */
    public static <T> T newInstance(Class<T> cls, Object... args) {
        try {
            Class<?>[] clazzes = new Class<?>[args.length];
            for (int i = 0; i < clazzes.length; i++) {
                clazzes[i] = args[i].getClass();
            }
            return cls.getConstructor(clazzes).newInstance(args);
        } catch (Throwable t) {
            Logger.logThrowable("Unable to instantiate class '" + cls + "'", t);
            return null;
        }
    }

    /**
     * Fuellt die gegebene Liste mit allen Klassen im gegebenem Ordner aus dem gegebenen Package.
     *
     * @param first
     *            ob dies der erste Methoden-Aufruf ist - sollte true sein
     * @param fileOrDir
     *            zu duchsuchender Ordner oder Datei
     * @param packageName
     *            Name des Packages
     * @param classes
     *            alle Klassen
     */
    private static void getClassesFromFileOrDirIntern(boolean first, File fileOrDir, String packageName, ArrayList<Class<?>> classes) {
        if (fileOrDir.isDirectory()) {
            if (!first) {
                packageName = (packageName + "." + fileOrDir.getName()).replaceAll("^\\.", "");
            }
            for (String subFileOrDir : fileOrDir.list()) {
                getClassesFromFileOrDirIntern(false, new File(fileOrDir, subFileOrDir), packageName, classes);
            }
        } else {
            if (fileOrDir.getName().toLowerCase().endsWith(".class")) {
                String classFile = fileOrDir.getName();
                classFile = packageName + "." + classFile.substring(0, classFile.length() - ".class".length());
                try {
                    Class<?> clazz = Class.forName(classFile);
                    classes.add(clazz);
                } catch (Throwable t) {
                    Logger.logThrowable("Unable to get class '" + classFile + "'", t);
                }
            }
        }
    }

    /**
     * Nicht instanziierbar.
     */
    private ReflectionUtil() {
    }
}
