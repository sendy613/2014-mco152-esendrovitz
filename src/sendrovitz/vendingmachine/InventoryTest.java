package sendrovitz.vendingmachine;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class InventoryTest {

	@Test
	public void testGet() throws IOException {
		Inventory inventory = new Inventory();
		inventory.load("./inventory.txt");
		Assert.assertEquals("Gum", inventory.get("E05").getName());
	}

	@Test
	public void testAdd() throws IOException {
		Inventory inventory = new Inventory();
		inventory.load("./inventory.txt");
		Item add = new Item("ABC", "soda", 1.75, 15);
		inventory.add(add);
		Assert.assertEquals(add.getName(), inventory.get("ABC").getName());
	}

	@Test
	public void testRemoveOne() throws IOException {
		Inventory inventory = new Inventory();
		inventory.load("./inventory.txt");
		int oldQuantity = inventory.get("E05").getQuantity();
		inventory.removeOne("E05");
		Assert.assertEquals(oldQuantity - 1, inventory.get("E05").getQuantity());
	}

	@Test
	public void testIsEmpty() throws IOException {
		Inventory inventory = new Inventory();
		inventory.load("./inventory.txt");
		Item add = new Item("ABC", "soda", 1.75, 1);
		inventory.add(add);
		Assert.assertEquals(false, inventory.isEmpty("ABC"));
		inventory.removeOne("ABC");
		Assert.assertEquals(true, inventory.isEmpty("ABC"));

	}
}
