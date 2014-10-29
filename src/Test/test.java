package Test;

import java.util.Map.Entry;

import projektkurs.item.AbstractItem;
import projektkurs.item.ItemStack;
import projektkurs.item.Items;
import projektkurs.lib.I18n;
import projektkurs.lib.Images;

/**
 * Test
 *
 */
@SuppressWarnings("all")
public class test {

	public static void main(String[] args) {

		Images.init();
		I18n.init();
		Items.init();

		ItemStack stack1 = new ItemStack(Items.ITEM_42, 10, 0);
		ItemStack stack2 = new ItemStack(Items.ITEM_42, 1, 0);
		ItemStack stack3 = new ItemStack(Items.KEY, 1, 0);

		for (Entry<String, AbstractItem> e : Items.MAPPINGS.entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue().hashCode()
					+ " - " + Integer.toBinaryString(e.getValue().hashCode()));
		}

		System.out.println(stack1 + ": " + stack1.hashCode() + " - "
				+ Integer.toBinaryString(stack1.hashCode()));
		System.out.println(stack2 + ": " + stack2.hashCode() + " - "
				+ Integer.toBinaryString(stack2.hashCode()));
		System.out.println(stack3 + ": " + stack3.hashCode() + " - "
				+ Integer.toBinaryString(stack3.hashCode()));
		System.out.println(stack1.equals(stack2));
		System.out.println(stack1.equals(stack3));
		System.out.println(stack2.equals(stack3));
	}
}
