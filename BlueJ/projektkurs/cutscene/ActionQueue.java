package projektkurs.cutscene;

import java.util.ArrayList;

import projektkurs.cutscene.action.Action;

/**
 * Eine Schlange aus Action Objekten
 * 
 */
public class ActionQueue {

	/**
	 * Hilfsklasse für die Schlange
	 * 
	 */
	public static class ActionNode {

		/**
		 * Interne Referenz zur Action
		 */
		private final Action action;
		/**
		 * Interne Referenz zur vorherigen ActionNode
		 */
		private ActionNode next;

		/**
		 * Konstruktor für ActionNodes
		 * 
		 * @param _action
		 *            ist die Action
		 */
		public ActionNode(Action _action) {
			action = _action;
			next = null;
		}

		/**
		 * Die mit dieser ActionNode verbundene Action
		 * 
		 * @return
		 */
		public Action getAction() {
			return action;
		}

		/**
		 * Die vorherige ActionNode
		 * 
		 * @return
		 */
		public ActionNode getNext() {
			return next;
		}

		/**
		 * Setzt die vorherige ActionNode
		 * 
		 * @param _next
		 *            ist die neue vorherige ActionNode
		 */
		public void setNext(ActionNode _next) {
			next = _next;
		}

		@Override
		public String toString() {
			return "[" + action.toString() + "]";
		}

	}

	/**
	 * Interne Referenzen zum Kopf und Schwanz der Schlange
	 */
	private ActionNode head, tail;

	/**
	 * Konstruktor für eine ActionQueue
	 */
	public ActionQueue() {
		head = tail = null;
	}

	/**
	 * Löst die oberste Action von der Schlange
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
	 * Stellt eine neue Action hinten an
	 * 
	 * @param toPush
	 */
	public void enQueue(Action toPush) {
		ActionNode node = new ActionNode(toPush);
		if (empty())
			head = node;
		else
			tail.setNext(node);
		tail = node;
	}

	/**
	 * Vorderste Action
	 * 
	 * @return
	 */
	public Action front() {
		if (empty())
			return null;
		return head.getAction();
	}

	@Override
	public String toString() {
		ArrayList<ActionNode> nodes = new ArrayList<ActionNode>(0);

		ActionNode currNode = head;
		while (currNode != null) {
			nodes.add(currNode);
			currNode = currNode.getNext();
		}

		return "ActionQueue" + nodes.toString();
	}

}
