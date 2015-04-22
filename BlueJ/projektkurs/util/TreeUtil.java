package projektkurs.util;

import java.util.Comparator;

public final class TreeUtil {

    private static class MyComparator<T> {

        private final Comparator<T> comparator;

        public MyComparator() {
            this.comparator = null;
        }

        public MyComparator(Comparator<T> comparator) {
            this.comparator = comparator;
        }

        public int compare(Comparable<T> object1, T object2) {
            return object1.compareTo(object2);
        }

        public int compare(T object1, T object2) {
            return comparator.compare(object1, object2);
        }

    }

    public static <T extends Comparable<T>> void balance(Tree<? extends T> tree) {
        doBalance(tree, new MyComparator<T>());
    }

    public static <T> void balance(Tree<? extends T> tree, Comparator<T> comparator) {
        doBalance(tree, new MyComparator<T>(comparator));
    }

    private static <T> void doBalance(Tree<? extends T> tree, MyComparator<T> comparator) {
        // TODO
    }

    private TreeUtil() {
        // NO-OP
    }

}
