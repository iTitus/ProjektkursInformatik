package projektkurs.util;

/**
 * Eine Node.
 *
 * @param <T>
 *            Inhaltstyp
 */
public class Node<T> {

    /**
     * Inhalt.
     */
    private final T content;
    /**
     * Naechste Node.
     */
    private Node<T> next;

    /**
     * Konstruktor.
     *
     * @param content
     *            ist der Inhalt
     */
    public Node(T content) {
        this.content = content;
        next = null;
    }

    /**
     * Inhalt der Node.
     *
     * @return Inhalt
     */
    public T get() {
        return content;
    }

    /**
     * Die naechste Node.
     *
     * @return naechste Node
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Setzt die naechste Node.
     *
     * @param next
     *            ist die neue naechste Node
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "[" + (content == null ? "null" : content.toString()) + "]";
    }

}
