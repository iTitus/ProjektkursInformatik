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

    public static void main(String[] args) {
        Tree<Integer> testTree = new Tree<Integer>(10);

        testTree.forceAdd(true, 5);
        testTree.next(true);
        testTree.forceAdd(true, 2);
        testTree.forceAdd(false, 7);

        testTree.toRoot();
        testTree.forceAdd(false, 11);
        testTree.next(false);
        testTree.forceAdd(false, 12);
        testTree.next(false);
        testTree.forceAdd(false, 13);
        testTree.next(false);
        testTree.forceAdd(false, 14);
    }

    private static <T> void doBalance(Tree<? extends T> tree, MyComparator<T> comparator) {
        // TODO
    }

    private TreeUtil() {
        // NO-OP
    }

}
