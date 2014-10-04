package Test;

import projektkurs.cutscene.ActionQueue;
import projektkurs.cutscene.action.Action;

public class test {

	public static void main(String[] args) {

		ActionQueue q = new ActionQueue();

		q.enQueue(new Action() {
			@Override
			public String toString() {
				return "1234";
			}
		});

		q.enQueue(new Action() {
			@Override
			public String toString() {
				return "4321";
			}
		});

		System.out.println(q);
		System.out.println(q.front());
		q.deQueue();
		System.out.println(q);
		System.out.println(q.front());
		q.deQueue();
		System.out.println(q);
		System.out.println(q.front());

	}

}
