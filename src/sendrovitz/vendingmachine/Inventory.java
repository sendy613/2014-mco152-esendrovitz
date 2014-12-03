package sendrovitz.vendingmachine;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
	private ArrayList<Item> list;
	private Map<String, Item> map;

	public Inventory() {
		ArrayList<Item> list = new ArrayList<Item>();
		this.list = list;
		Map<String, Item> map = new HashMap<String, Item>();
		this.map = map;
	}

	public void load(String inventoryFilename) throws IOException {
		Scanner file = new Scanner(new File(inventoryFilename));
		while (file.hasNext()) {
			String line = file.nextLine();
			String temp[] = line.split(",");
			String code = temp[0];
			Item item = new Item(code, temp[1], Double.parseDouble(temp[2]), Integer.parseInt(temp[3]));
			this.add(item);
			map.put(code, item);

		}
		file.close();
	}

	/**
	 * 
	 * @param code
	 * @return the item or null if an item with that code doesn't exist
	 */
	public Item get(String code) {
		if (map.get(code) != null) {
			return map.get(code);
		}
		return null;
	}

	/**
	 * 
	 * @param item
	 *            to add
	 */
	public void add(Item item) {
		list.add(item);
		String code = item.getCode();
		map.put(code, item);
	}

	/**
	 * Removes one from quantity of the specified item
	 * 
	 * @param code
	 */

	public void removeOne(String code) {
		map.get(code).setQuantity(map.get(code).getQuantity() - 1);

	}

	/**
	 * 
	 * @param code
	 * @return false if the Item exists and there is at least one quantity,
	 *         otherwise true.
	 */
	public boolean isEmpty(String code) {
		if (map.get(code) != null && map.get(code).getQuantity() > 0) {
			return false;
		}

		return true;
	}

	/**
	 * Lists the items in the inventory one per line in the format code name @
	 * price x quantity\n
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		DecimalFormat formatter = new DecimalFormat("$0.00");
		for (int i = 0; i < list.size(); i++) {
			builder.append(list.get(i).getCode());
			builder.append(" ");
			builder.append(list.get(i).getName());
			builder.append(" @ ");
			builder.append(formatter.format(list.get(i).getPrice()));
			builder.append(" x ");
			builder.append(list.get(i).getQuantity());
			builder.append("\n");
		}
		return builder.toString();
	}
}
