package sendrovitz.vendingmachine;

public class VendingMachine {

	private Inventory inventory;
	private Money bank;

	/**
	 * The amount of money the person has put into the Vending Machine so far
	 */
	private Money paid;

	public VendingMachine(Inventory inventory, Money bank) {
		this.inventory = inventory;
		this.bank = bank;
		paid = new Money();
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Money getBank() {
		return bank;
	}

	public Money getPaid() {
		return paid;
	}

	public void resetPaid() {
		this.paid.setNumDollars(0);
		this.paid.setNumQuarters(0);
		this.paid.setNumDimes(0);
		this.paid.setNumNickles(0);
	}

	/**
	 * Add additional Money to the machine
	 * 
	 * @param additional
	 * @return the amount that the person has put into the machine
	 */
	public double pay(Money additional) {
		paid.add(additional);
		return Math.round(paid.getTotal() * 100.0) / 100.0;
	}

	/**
	 * 
	 * @param code
	 * @return the amount of change as a Money object
	 * @throws CodeNotFoundException
	 *             if there is no item with that code
	 * @throws NotEnoughPaidException
	 *             if paid is not enough to buy the item
	 * @throws NotEnoughChangeException
	 *             if the transaction cannot be completed because there isn't
	 *             enough money in the vending machine for the change
	 */
	public Money buy(String code) throws CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException {
		if (inventory.get(code) == null) {
			throw new CodeNotFoundException();
		}
		if (this.paid.getTotal() < inventory.get(code).getPrice()) {
			throw new NotEnoughPaidException();
		}
	
		bank.add(paid);
		
		if(inventory.get(code).getQuantity() == 0){
			Money money = new Money(0,0,0,0);
			return money;
		}
		double change = Math.round((paid.getTotal() - inventory.get(code).getPrice()) * 100.0) / 100.0;

		Money changeOwed = bank.remove(change);
		// set paid to 0
		resetPaid();
		// remove one in quantity
		inventory.removeOne(code);

		

		return changeOwed;
	}

}
