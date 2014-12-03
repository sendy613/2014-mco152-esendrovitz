package sendrovitz.vendingmachine;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {

	@Test
	public void testPay() throws IOException {
		Inventory inventory = new Inventory();
		inventory.load("./inventory.txt");
		Money bank = new Money(10, 10, 10, 10);
		VendingMachine vm = new VendingMachine(inventory, bank);
		Money pay = new Money(2, 0, 0, 0);
		Assert.assertEquals(2.00, vm.pay(pay), 0);

	}

	@Test
	public void testBuy() throws CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException, IOException {
		Inventory inventory = new Inventory();
		inventory.load("./inventory.txt");
		Money bank = new Money(10, 10, 10, 10);
		Money paid = new Money(2, 0, 0, 0);
		VendingMachine vm = new VendingMachine(inventory, bank);
		vm.pay(paid);
		int oldQuantity = inventory.get("C03").getQuantity();
		Money change = vm.buy("C03");
		// correct change
		Assert.assertEquals(1.00, change.getTotal(), 0);
		// test if quantity reduces by 1
		Assert.assertEquals(oldQuantity - 1, inventory.get("C03").getQuantity(), 0);
		// test if paid=0
		Assert.assertEquals(0, vm.getPaid().getTotal(), 0);
		Assert.assertEquals(15.00, vm.getBank().getTotal(), 0);
	}

	


	@Test
	public void testCodeNotFoundException() throws CodeNotFoundException, IOException, NotEnoughPaidException,
			NotEnoughChangeException {
		Inventory inventory = new Inventory();
		inventory.load("./inventory.txt");
		Money bank = new Money(10, 10, 10, 10);
		VendingMachine vm = new VendingMachine(inventory, bank);
		try {
			vm.buy("DEF");
			Assert.fail("CodeNotFound should be thrown here");

		} catch (CodeNotFoundException e) {

		}
	}

	@Test
	public void testNotEnoughPaid() throws CodeNotFoundException, IOException, NotEnoughPaidException,
			NotEnoughChangeException {
		Inventory inventory = new Inventory();
		inventory.load("./inventory.txt");
		Money bank = new Money(10, 10, 10, 10);
		VendingMachine vm = new VendingMachine(inventory, bank);
		Money paid = new Money(0, 0, 1, 0);
		vm.pay(paid);
		try {
			vm.buy("C03");
			Assert.fail("NotEnoughPaid exception thrown here");
		} catch (NotEnoughPaidException e) {

		}
	}

	@Test
	public void NotEnoughChangeException() throws CodeNotFoundException, IOException, NotEnoughPaidException,
			NotEnoughChangeException {
		Inventory inventory = new Inventory();
		inventory.load("./inventory.txt");
		Money bank = new Money(1, 4, 0, 1);
		VendingMachine vm = new VendingMachine(inventory, bank);
		Money paid = new Money(2, 0, 0, 0);
		vm.pay(paid);
		try {
			vm.buy("B02");
			Assert.fail("NotEnoughChange exception thrown here");
		} catch (NotEnoughChangeException e) {

		}
	}

	@Test
	public void NotEnoughChangeException2() throws CodeNotFoundException, IOException, NotEnoughPaidException,
			NotEnoughChangeException {
		Inventory inventory = new Inventory();
		inventory.load("./inventory.txt");
		Money bank = new Money(10, 0, 0, 0);
		VendingMachine vm = new VendingMachine(inventory, bank);
		Money paid = new Money(2, 0, 1, 0);
		vm.pay(paid);
		try {
			vm.buy("B02");
			Assert.fail("NotEnoughChange exception thrown here");
		} catch (NotEnoughChangeException e) {

		}

	}
}
