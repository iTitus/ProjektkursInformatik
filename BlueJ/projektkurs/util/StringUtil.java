package projektkurs.util;

public final class StringUtil {

    public static boolean isNotNullOrEmpty(String s) {
        return s != null && !s.isEmpty();
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static String join(String glue, String[] strings) {
        if (strings == null || strings.length == 0) {
            return null;
        }
        String out = strings[0];
        if (strings.length > 1) {
            for (int i = 1; i < strings.length; i++) {
                out += glue + strings[i];
            }
        }
        return out;
    }

    private StringUtil() {
        // NO-OP
    }

}
