package projektkurs.util;

public final class MathUtil {

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
		return (int) (d);
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
		if (i > 0)
			return 1;
		if (i < 0)
			return -1;
		return 0;
	}

	private MathUtil() {
	}
}
