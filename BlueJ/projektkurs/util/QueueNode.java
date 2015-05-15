package projektkurs.util;

/**
 * Eine Node.
 *
 * @param <T>
 *            Inhaltstyp
 */
public class QueueNode<T> {

    /**
     * Inhalt.
     */
    private final T content;
    /**
     * Naechste Node.
     */
    private QueueNode<T> next;

    /**
     * Konstruktor.
     *
     * @param content
     *            ist der Inhalt
     */
    public QueueNode(T content) {
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
    public QueueNode<T> getNext() {
        return next;
    }

    /**
     * Setzt die naechste Node.
     *
     * @param next
     *            ist die neue naechste Node
     */
    public void setNext(QueueNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "[" + (content == null ? "null" : content.toString()) + "]";
    }

}
