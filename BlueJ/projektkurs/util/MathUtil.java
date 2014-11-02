package projektkurs.util;

public final class MathUtil {

	public static int ceil(double d) {
		return (int) (d + .5);
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

	private MathUtil() {
	}
}
