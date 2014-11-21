package projektkurs.util;

import java.util.Random;

public final class MathUtil {

    private static final Random rand = new Random();

    public static int ceil(double d) {
        return (int) (d + 1);
    }

    public static int ceilAdd(double a, double b) {
        return ceil(a + b);
    }

    public static int ceilDiv(double a, double b) {
        return ceil(a / b);
    }

    public static int ceilMul(double a, double b) {
        return ceil(a * b);
    }

    public static int ceilSub(double a, double b) {
        return ceil(a - b);
    }

    public static int floor(double d) {
        return (int) d;
    }

    public static int floorAdd(double a, double b) {
        return floor(a + b);
    }

    public static int floorDiv(double a, double b) {
        return floor(a / b);
    }

    public static int floorMul(double a, double b) {
        return floor(a * b);
    }

    public static int floorSub(double a, double b) {
        return floor(a - b);
    }

    public static int randomInt(int max) {
        return randomInt(0, max);
    }

    public static int randomInt(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }

    public static int round(double d) {
        return (int) (d + .5);
    }

    public static int roundAdd(double a, double b) {
        return round(a + b);
    }

    public static int roundDiv(double a, double b) {
        return round(a / b);
    }

    public static int roundMul(double a, double b) {
        return round(a * b);
    }

    public static int roundSub(double a, double b) {
        return round(a - b);
    }

    public static int signum(int i) {
        if (i > 0) {
            return 1;
        }
        if (i < 0) {
            return -1;
        }
        return 0;
    }

    private MathUtil() {
    }
}
