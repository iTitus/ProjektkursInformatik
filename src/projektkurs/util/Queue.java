package projektkurs.util;

import java.util.ArrayList;

/**
 * Eine Schlange
 */
public class Queue<T> {

	/**
	 * Interne Referenzen zum Kopf und Schwanz der Schlange
	 */
	private Node<T> head, tail;

	/**
	 * Konstruktor für eine Queue
	 */
	public Queue() {
		head = tail = null;
	}

	/**
	 * Leert die Queue
	 */
	public void clear() {
		head = tail = null;
	}

	/**
	 * Löst das oberste Objekt von der Schlange
	 */
	public void deQueue() {
		if (empty())
			return;
		head = head.getNext();
		if (empty())
			tail = null;
	}

	/**
	 * Prüft ob die Schlange leer ist
	 *
	 * @return
	 */
	public boolean empty() {
		return head == null;
	}

	/**
	 * Stellt ein neues Objekt hinten an
	 *
	 * @param toPush
	 */
	public void enQueue(T toPush) {
		Node<T> node = new Node<T>(toPush);
		if (empty())
			head = node;
		else
			tail.setNext(node);
		tail = node;
	}

	/**
	 * Vorderstes Objekt
	 *
	 * @return
	 */
	public T front() {
		if (empty())
			return null;
		return head.get();
	}

	/**
	 * Vorderstes Objekt & Lösen des ersten Objektes von der Schlange
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
		ArrayList<Node<T>> nodes = new ArrayList<Node<T>>();

		Node<T> currNode = head;
		while (currNode != null) {
			nodes.add(currNode);
			currNode = currNode.getNext();
		}

		return "Queue" + nodes.toString();
	}

}
