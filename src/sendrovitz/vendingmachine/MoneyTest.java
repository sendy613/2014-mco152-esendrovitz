package sendrovitz.vendingmachine;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class MoneyTest {

	@Test
	public void testAdd() {
		Money money = new Money();
		Money money2 = new Money(2, 2, 2, 2);
		money.add(money2);
		Assert.assertEquals(2.80, money.getTotal(), .001);
	}

	@Test
	public void testRemove() throws NotEnoughChangeException, IOException {
		Inventory inventory = new Inventory();
		inventory.load("./inventory.txt");
		Money money = new Money(2, 2, 2, 2);
		VendingMachine vm = new VendingMachine(inventory, money);
		money.remove(2.00);
		Assert.assertEquals(.80, money.getTotal(), .001);

	}

	@Test
	public void testException() throws NotEnoughChangeException {
		Money money = new Money(0, 0, 0, 0);
		try {
			money.remove(1.00);
			Assert.fail("NotEnoughChangeException should be thrown here");
		} catch (NotEnoughChangeException e) {
			// it should go here
			// if it does then it passes the test bec means exception ws thrown
		}
	}
}
