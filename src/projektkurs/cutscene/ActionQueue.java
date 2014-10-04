package projektkurs.cutscene;

import java.util.ArrayList;

import projektkurs.cutscene.action.Action;

/**
 * Eine Schlange aus Action Objekten
 * 
 */
public class ActionQueue {

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
	 * Prüft ob die Schlange leer ist
	 * 
	 * @return
	 */
	public boolean empty() {
		return head == null;
	}

	/**
	 * Löst die oberste Action von der Schlange
	 */
	public void deQueue() {
		head = head.getPrevious();
	}

	/**
	 * Stellt eine neue Action hinten an
	 * 
	 * @param toPush
	 */
	public void enQueue(Action toPush) {
		ActionNode node = new ActionNode(toPush, null);
		if (empty()) {
			head = tail = node;
		} else {
			tail.setPrevious(node);
			tail = node;
		}

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
			currNode = currNode.getPrevious();
		}

		return "ActionQueue" + nodes.toString();
	}

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
		private ActionNode prev;

		/**
		 * Konstruktor für ActionNodes
		 * 
		 * @param _action
		 *            ist die Action
		 * @param _prev
		 *            ist die vorherige ActionNode
		 */
		public ActionNode(Action _action, ActionNode _prev) {
			action = _action;
			prev = _prev;
		}

		/**
		 * Die vorherige ActionNode
		 * 
		 * @return
		 */
		public ActionNode getPrevious() {
			return prev;
		}

		/**
		 * Setzt die vorherige ActionNode
		 * 
		 * @param _prev
		 *            ist die neue vorherige ActionNode
		 */
		public void setPrevious(ActionNode _prev) {
			prev = _prev;
		}

		/**
		 * Die mit dieser ActionNode verbundene Action
		 * 
		 * @return
		 */
		public Action getAction() {
			return action;
		}

		@Override
		public String toString() {
			return "[" + action.toString() + "]";
		}

	}

}
