package projektkurs.util;

import java.util.ArrayList;

/**
 * Eine Queue.
 *
 * @param <T>
 *            Inhaltstyp
 */
public class Queue<T> {

    /**
     * Kopf der Queue.
     */
    private QueueNode<T> head;
    /**
     * Schwanz der Queue.
     */
    private QueueNode<T> tail;

    /**
     * Konstruktor.
     */
    public Queue() {
        clear();
    }

    /**
     * Leert die Queue.
     */
    public void clear() {
        head = null;
        tail = null;
    }

    /**
     * Loest das oberste Objekt von der Queue.
     */
    public void deQueue() {
        if (empty()) {
            return;
        }
        head = head.getNext();
        if (empty()) {
            tail = null;
        }
    }

    /**
     * Ist die Queue leer.
     *
     * @return true, wenn ja; false, wenn nein
     */
    public boolean empty() {
        return head == null;
    }

    /**
     * Stellt ein neues Objekt hinten an.
     *
     * @param toEnq
     *            neues Objekt
     */
    public void enQueue(T toEnq) {
        QueueNode<T> node = new QueueNode<T>(toEnq);
        if (empty()) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
    }

    /**
     * Vorderstes Objekt.
     *
     * @return vorderstes Objekt.
     */
    public T front() {
        if (empty()) {
            return null;
        }
        return head.get();
    }

    /**
     * Vorderstes Objekt & Loesen des ersten Objektes von der Queue.
     *
     * @return vorderstes Objekt
     */
    public T frontDeQueue() {
        T front = front();
        deQueue();
        return front;
    }

    @Override
    public String toString() {
        ArrayList<QueueNode<T>> nodes = new ArrayList<QueueNode<T>>();

        QueueNode<T> node = head;
        while (node != null) {
            nodes.add(node);
            node = node.getNext();
        }

        return "Queue" + nodes.toString();
    }

}
