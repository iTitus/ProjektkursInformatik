package projektkurs.lib;

public class MathUtil {

	public static int ceilDiv(double a, double b) {
		return (int) ((a / b) + 0.5);
	}

	public static int ceilMul(double a, double b) {
		return (int) ((a * b) + 0.5);
	}

	public static int ceilAdd(double a, double b) {
		return (int) ((a + b) + 0.5);
	}

	public static int ceilSub(double a, double b) {
		return (int) ((a - b) + 0.5);
	}

}
