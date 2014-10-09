package Test;

import java.util.HashMap;

import projektkurs.cutscene.ActionQueue;
import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.action.Action;

public class test {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		ActionQueue q = new ActionQueue();

		q.enQueue(new Action() {
			@Override
			public void doAction(CutScene cutScene) {
				// NO-OP
			}

			@Override
			public String toString() {
				return "1234";
			}
		});

		q.enQueue(new Action() {
			@Override
			public void doAction(CutScene cutScene) {
				// NO-OP
			}

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

		HashMap<String, Integer> hm1 = new HashMap<String, Integer>();
		HashMap<String, Integer> hm2;

		hm1.put("number one", 1);
		hm1.put("number two", 2);
		hm1.put("1", 42);

		hm2 = (HashMap<String, Integer>) hm1.clone();

		System.out.println("hm1: " + hm1.toString());
		System.out.println("hm2: " + hm2.toString());

		hm2.remove("1");

		System.out.println("hm1: " + hm1.toString());
		System.out.println("hm2: " + hm2.toString());

		System.err.println("Test!");

	}
}
