package sendrovitz.vendingmachine;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class RunVendingMachine {

	// print inventory
	// make selection
	// input money
	// input selection
	// output change
	public static void main(String[] args) {
		DecimalFormat formatter = new DecimalFormat("$#0.00");
		Inventory inventory = new Inventory();
		Scanner keyboard = new Scanner(System.in);
		String filename = "inventory.txt";
		try {
			inventory.load(filename);
			System.out.println(inventory.toString());

			System.out
					.println("Add Money/Make Selection?\n1. Dollar\n2. Quarter\n3. Dime\n4. Nickle\nor enter in the Item Code");
			System.out.println("\nBalance $0.00");
			Money m = new Money(10, 10, 10, 10);
			VendingMachine vm = new VendingMachine(inventory, m);

			double balance = 0.0;
			boolean dispensed = false;

			do {
				String choice = keyboard.nextLine();

				switch (choice) {
				case "1":
					Money temp = new Money(1, 0, 0, 0);
					//vm.getBank().add(temp);
					balance = vm.pay(temp);
					System.out.println("Balance " + formatter.format(balance));
					break;

				case "2":
					temp = new Money(0, 1, 0, 0);
					//vm.getBank().add(temp);
					balance = vm.pay(temp);
					System.out.println("Balance " + formatter.format(balance));
					break;

				case "3":
					temp = new Money(0, 0, 1, 0);
					//vm.getBank().add(temp);
					balance = vm.pay(temp);
					System.out.println("Balance " + formatter.format(balance));
					break;
				case "4":
					temp = new Money(0, 0, 0, 1);
					//vm.getBank().add(temp);
					balance = vm.pay(temp);
					System.out.println("Balance " + formatter.format(balance));
					break;
				default:
					try {
						String code = choice.toUpperCase();
					
						Money change = vm.buy(code);
						if (change.getTotal()==0) {
							System.out.println("No more of that item");
							break;
						}


						System.out.println("Dispensing " + inventory.get(code).getName());
						System.out.println("Change "
								+ formatter.format((Math.round((change.getTotal()) * 100.0)) / 100.0));
						dispensed = true;
						break;
					} catch (CodeNotFoundException e) {
						System.out.println("Code Not Found");
					} catch (NotEnoughPaidException e) {
						System.out.println("Not Enough Paid");
					} catch (NotEnoughChangeException e) {
						System.out.println("Not enough change in machine");
					}

				}
			} while (!dispensed);
		} catch (IOException e) {
			System.out.println("IO exception");
		}
		keyboard.close();
	}

}
