package projektkurs.util;

import java.util.Random;

/**
 * Rechenhilfen.
 */
public final class MathUtil {

    private static final double DEG_TO_RAD = Math.PI / 180;

    private static final double RAD_TO_DEG = 180 / Math.PI;
    private static final double radToIndex, degToIndex;

    /**
     * Zufallsobjekt.
     */
    private static final Random RANDOM = new Random();
    private static final double[] sin, cos;
    private static final int SIN_BITS, SIN_MASK, SIN_COUNT;

    static {
        SIN_BITS = 12;
        SIN_MASK = ~(-1 << SIN_BITS);
        SIN_COUNT = SIN_MASK + 1;

        radToIndex = SIN_COUNT / (2 * Math.PI);
        degToIndex = SIN_COUNT / 360D;

        sin = new double[SIN_COUNT];
        cos = new double[SIN_COUNT];

        for (int i = 0; i < SIN_COUNT; i++) {
            sin[i] = Math.sin((i + 0.5) / SIN_COUNT * 2 * Math.PI);
            cos[i] = Math.cos((i + 0.5) / SIN_COUNT * 2 * Math.PI);
        }

        for (int i = 0; i < 360; i += 90) {
            sin[(int) (i * degToIndex) & SIN_MASK] = Math.sin(toRad(i));
            cos[(int) (i * degToIndex) & SIN_MASK] = Math.cos(toRad(i));
        }
    }

    /**
     * Betrag der gegebenen Zahl.
     *
     * @param d
     *            Zahl
     * @return Betrag
     */
    public static double abs(double d) {
        return d < 0 ? -d : d;
    }

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
     * Gibt eine Zahl zurueck, die im gegebenen Intervall liegt: [min; max].
     *
     * @param d
     *            Zahl
     * @param min
     *            Minimum
     * @param max
     *            Maximum
     * @return eingegrenzte Zahl
     */
    public static double clamp(double d, double min, double max) {
        return Math.max(Math.min(d, max), min);
    }

    /**
     * Gibt eine Zahl zurueck, die im gegebenen Intervall liegt: [min; max].
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
     * Gibt eine Zahl zurueck, die in einem Array der gegebenen Laenge liegt: [0; length[.
     *
     * @param i
     *            Zahl
     * @param length
     *            Laenge des Arrays
     * @return eingegrenzte Zahl
     */
    public static int clampToArray(int i, int length) {
        return clamp(i, 0, length - 1);
    }

    public static double cosDeg(double angle) {
        return cosRad(toRad(angle));
    }

    public static double cosRad(double rad) {
        return cos[(int) (rad * radToIndex) & SIN_MASK];
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
     * @param b
     * @return
     */
    public static int getBit(boolean b) {
        return b ? 1 : 0;
    }

    public static double getDistanceSq(double posX1, double posY1, double posX2, double posY2) {
        double dx = posX1 - posX2;
        double dy = posY1 - posY2;
        return dx * dx + dy * dy;
    }

    public static double getDistanceSq(IHasPosition<? extends Number> p1, IHasPosition<? extends Number> p2) {
        return getDistanceSq(p1.getPosX().doubleValue(), p1.getPosY().doubleValue(), p2.getPosX().doubleValue(), p2.getPosY().doubleValue());
    }

    public static double inverse(double d) {
        return 1 / d;
    }

    /**
     * Prueft, ob der gegebenen Index innerhalb der Grenzen des gegebenen Arrays ist.
     *
     * @param i
     *            Index
     * @param length
     *            Array-Laenge
     * @return true, wenn ja; false, wenn nein
     */
    public static boolean isInArray(int i, int length) {
        return i >= 0 && i < length;
    }

    public static boolean isInside(IHasPositionAndSize<? extends Number, ? extends Number> o1, IHasPositionAndSize<? extends Number, ? extends Number> o2) {
        return isInside(o1.getPosX(), o1.getPosY(), o1.getSizeX(), o1.getSizeY(), o2.getPosX(), o2.getPosY(), o2.getSizeX(), o2.getSizeY());
    }

    public static <P1 extends Number, S1 extends Number, P2 extends Number, S2 extends Number> boolean isInside(P1 x1, P1 y1, S1 sizeX1, S1 sizeY1, P2 x2, P2 y2, S2 sizeX2, S2 sizeY2) {
        return Math.max(x2.doubleValue(), x1.doubleValue()) < Math.min(x2.doubleValue() + sizeX2.doubleValue(), x1.doubleValue() + sizeX1.doubleValue()) && Math.max(y2.doubleValue(), y1.doubleValue()) < Math.min(y2.doubleValue() + sizeY2.doubleValue(), y1.doubleValue() + sizeY1.doubleValue());
    }

    /**
     * Prueft, ob der gegebenen Index ausserhalb der Grenzen des gegebenen Arrays ist.
     *
     * @param i
     *            Index
     * @param length
     *            Array-Laenge
     * @return true, wenn ja; false, wenn nein
     */
    public static boolean isNotInArray(int i, int length) {
        return i < 0 || i >= length;
    }

    public static double nextGaussian() {
        return RANDOM.nextGaussian();
    }

    /**
     * Zufaellige Zahl zwischen 0 (inklusiv) und der gegebenen Zahl (exklusiv).
     *
     * @param max
     *            Zahl
     * @return Zufallszahl
     */
    public static int nextInt(int max) {
        return randomInt(0, max - 1);
    }

    /**
     * Zufaellige Zahl zwischen dem gegebenen Minimum (inklusiv) und dem gegebenen Maximum (exklusiv).
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

    public static boolean randomBoolean() {
        return RANDOM.nextBoolean();
    }

    /**
     * Zufaellige Zahl zwischen 0 (inklusiv) und der gegebenen Zahl (inklusiv).
     *
     * @param max
     *            Zahl
     * @return Zufallszahl
     */
    public static int randomInt(int max) {
        return randomInt(0, max);
    }

    /**
     * Zufaellige Zahl zwischen dem gegebenen Minimum (inklusiv) und dem gegebenen Maximum (inklusiv).
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

    public static int signum(double d) {
        return d > 0 ? 1 : d < 0 ? -1 : 0;
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

    public static double sinDeg(double angle) {
        return sinRad(toRad(angle));
    }

    public static double sinRad(double rad) {
        return sin[(int) (rad * radToIndex) & SIN_MASK];
    }

    public static double toDeg(double angle) {
        return angle * RAD_TO_DEG;
    }

    public static double toRad(double angle) {
        return angle * DEG_TO_RAD;
    }

    /**
     * Nicht instanziierbar.
     */
    private MathUtil() {
    }

}
