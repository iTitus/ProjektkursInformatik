package Test;

import projektkurs.entity.Entity;

/**
 * Test
 *
 */
@SuppressWarnings("all")
public class test {

	public static void main(String[] args) {

		Entity e = new Entity(0, 0, 101, 101, null);

		System.out.println(e.isInside(100, 100, 20, 20));

	}
}
