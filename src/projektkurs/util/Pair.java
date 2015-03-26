package projektkurs.util;

/**
 * Ein gleichwertiges Paar aus zwei Elementen.
 *
 * @param <A>
 *            Wert 1
 * @param <B>
 *            Wert 2
 */
public class Pair<A, B> {

    /**
     * Wert 1.
     */
    private final A valueA;
    /**
     * Wert 2.
     */
    private final B valueB;

    /**
     * Konstruktor.
     *
     * @param valueA
     *            Wert 1
     * @param valueB
     *            Wert 2
     */
    public Pair(A valueA, B valueB) {
        this.valueA = valueA;
        this.valueB = valueB;
    }

    /**
     * Der 1. Wert.
     *
     * @return Wert 1
     */
    public A getValueA() {
        return valueA;
    }

    /**
     * Der 2. Wert.
     *
     * @return Wert 2
     */
    public B getValueB() {
        return valueB;
    }

    @Override
    public String toString() {
        return "Pair[" + valueA + "|" + valueB + "]";
    }

}
