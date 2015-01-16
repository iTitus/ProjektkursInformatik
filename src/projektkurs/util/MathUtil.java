package projektkurs.util;

import java.util.Random;

/**
 * Rechenhilfen.
 */
public final class MathUtil {

    /**
     * Zufallsobjekt.
     */
    private static final Random RANDOM = new Random();

    /**
     * Betrag der gegebenen Zahl.
     *
     * @param i
     *            Zahl
     * @return Betrag
     */
    public static int abs(int i) {
        return i < 0 ? -i : i;
    }

    /**
     * Aufrunden.
     *
     * @param d
     *            Zahl, die aufgerundet werden soll.
     * @return aufgerundete Zahl
     */
    public static int ceil(double d) {
        int floored = floor(d);
        if (d == floored) {
            return floored;
        }
        return floored + 1;
    }

    /**
     * Addieren und dann das Ergebnis abrunden.
     *
     * @param a
     *            Zahl 1
     * @param b
     *            Zahl 2
     * @return abgerundetes Ergebnis
     */
    public static int ceilAdd(double a, double b) {
        return ceil(a + b);
    }

    /**
     * Dividieren und dann das Ergebnis abrunden.
     *
     * @param a
     *            Zahl 1
     * @param b
     *            Zahl 2
     * @return abgerundetes Ergebnis
     */
    public static int ceilDiv(double a, double b) {
        return ceil(a / b);
    }

    /**
     * Multiplizieren und dann das Ergebnis abrunden.
     *
     * @param a
     *            Zahl 1
     * @param b
     *            Zahl 2
     * @return abgerundetes Ergebnis
     */
    public static int ceilMul(double a, double b) {
        return ceil(a * b);
    }

    /**
     * Subtrahieren und dann das Ergebnis abrunden.
     *
     * @param a
     *            Zahl 1
     * @param b
     *            Zahl 2
     * @return abgerundetes Ergebnis
     */
    public static int ceilSub(double a, double b) {
        return ceil(a - b);
    }

    /**
     * Gibt eine Zahl zurück, die im gegebenen Intervall liegt: [min; max].
     *
     * @param i
     *            Zahl
     * @param min
     *            Minimum
     * @param max
     *            Maximum
     * @return eingegrenzte Zahl
     */
    public static int clamp(int i, int min, int max) {
        return Math.max(Math.min(i, max), min);
    }

    /**
     * Gibt eine Zahl zurück, die in einem Array der gegebenen Länge liegt: [0; length[.
     *
     * @param i
     *            Zahl
     * @param length
     *            Länge des Arrays
     * @return eingegrenzte Zahl
     */
    public static int clampToArray(int i, int length) {
        return clamp(i, 0, length - 1);
    }

    /**
     * Abrunden.
     *
     * @param d
     *            Zahl
     * @return aufgerundetes Ergebnis
     */
    public static int floor(double d) {
        return (int) d;
    }

    /**
     * Addieren und dann das Ergebnis abrunden.
     *
     * @param a
     *            Zahl 1
     * @param b
     *            Zahl 2
     * @return abgerundetes Ergebnis
     */
    public static int floorAdd(double a, double b) {
        return floor(a + b);
    }

    /**
     * Addieren und dann das Ergebnis abrunden.
     *
     * @param a
     *            Zahl 1
     * @param b
     *            Zahl 2
     * @return abgerundetes Ergebnis
     */
    public static int floorDiv(double a, double b) {
        return floor(a / b);
    }

    /**
     * Addieren und dann das Ergebnis abrunden.
     *
     * @param a
     *            Zahl 1
     * @param b
     *            Zahl 2
     * @return abgerundetes Ergebnis
     */
    public static int floorMul(double a, double b) {
        return floor(a * b);
    }

    /**
     * Addieren und dann das Ergebnis abrunden.
     *
     * @param a
     *            Zahl 1
     * @param b
     *            Zahl 2
     * @return abgerundetes Ergebnis
     */
    public static int floorSub(double a, double b) {
        return floor(a - b);
    }

    /**
     * Prüft, ob der gegebenen Index innerhalb der Grenzen des gegebenen Arrays ist.
     *
     * @param i
     *            Index
     * @param length
     *            Array-Länge
     * @return true, wenn ja; false, wenn nein
     */
    public static boolean isInArray(int i, int length) {
        return i >= 0 && i < length;
    }

    /**
     * Zufällige Zahl zwischen 0 (inklusiv) und der gegebenen Zahl (exklusiv).
     *
     * @param max
     *            Zahl
     * @return Zufallszahl
     */
    public static int nextInt(int max) {
        return randomInt(0, max - 1);
    }

    /**
     * Zufällige Zahl zwischen dem gegebenen Minimum (inklusiv) und dem gegebenen Maximum (exklusiv).
     *
     * @param min
     *            Minimum
     * @param max
     *            Maximum
     * @return Zufallszahl
     */
    public static int nextInt(int min, int max) {
        return randomInt(min, max - 1);
    }

    /**
     * Zufällige Zahl zwischen 0 (inklusiv) und der gegebenen Zahl (inklusiv).
     *
     * @param max
     *            Zahl
     * @return Zufallszahl
     */
    public static int randomInt(int max) {
        return randomInt(0, max);
    }

    /**
     * Zufällige Zahl zwischen dem gegebenen Minimum (inklusiv) und dem gegebenen Maximum (inklusiv).
     *
     * @param min
     *            Minimum
     * @param max
     *            Maximum
     * @return Zufallszahl
     */
    public static int randomInt(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }

    public static double rotX(double angle, double x, double y) {
        return y * Math.cos(angle) + x * Math.sin(angle);
    }

    public static double rotY(double angle, double x, double y) {
        return y * Math.sin(angle) - x * Math.cos(angle);
    }

    /**
     * Runden.
     *
     * @param d
     *            Zahl
     * @return gerundetes Ergebnis
     */
    public static int round(double d) {
        return floor(d + .5);
    }

    /**
     * Addieren und dann das Ergebnis runden.
     *
     * @param a
     *            Zahl 1
     * @param b
     *            Zahl 2
     * @return gerundetes Ergebnis
     */
    public static int roundAdd(double a, double b) {
        return round(a + b);
    }

    /**
     * Dividieren und dann das Ergebnis runden.
     *
     * @param a
     *            Zahl 1
     * @param b
     *            Zahl 2
     * @return gerundetes Ergebnis
     */
    public static int roundDiv(double a, double b) {
        return round(a / b);
    }

    /**
     * Multiplizieren und dann das Ergebnis runden.
     *
     * @param a
     *            Zahl 1
     * @param b
     *            Zahl 2
     * @return gerundetes Ergebnis
     */
    public static int roundMul(double a, double b) {
        return round(a * b);
    }

    /**
     * Subtrahieren und dann das Ergebnis runden.
     *
     * @param a
     *            Zahl 1
     * @param b
     *            Zahl 2
     * @return gerundetes Ergebnis
     */
    public static int roundSub(double a, double b) {
        return round(a - b);
    }

    /**
     * Vorzeichen der gegebenen Zahl.
     *
     * @param i
     *            Zahl
     * @return -1, 0 oder 1 - je nach Vorzeichen
     */
    public static int signum(int i) {
        return i > 0 ? 1 : i < 0 ? -1 : 0;
    }

    /**
     * Nicht instanziierbar.
     */
    private MathUtil() {
    }

}
