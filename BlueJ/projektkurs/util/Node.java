package projektkurs.util;

/**
 * Ein Knoten - zB für eine Schlange
 * 
 */
public class Node<T> {

	/**
	 * Interne Referenz zur Action
	 */
	private final T content;
	/**
	 * Interne Referenz zur vorherigen ActionNode
	 */
	private Node<T> next;

	/**
	 * Konstruktor für ActionNodes
	 * 
	 * @param _action
	 *            ist die Action
	 */
	public Node(T _content) {
		content = _content;
		next = null;
	}

	/**
	 * Inhalt der Node
	 * 
	 * @return
	 */
	public T get() {
		return content;
	}

	/**
	 * Die vorherige Node
	 * 
	 * @return
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * Setzt die vorherige Node
	 * 
	 * @param _next
	 *            ist die neue vorherige Node
	 */
	public void setNext(Node<T> _next) {
		next = _next;
	}

	@Override
	public String toString() {
		return "[" + (content == null ? "null" : content.toString()) + "]";
	}

}