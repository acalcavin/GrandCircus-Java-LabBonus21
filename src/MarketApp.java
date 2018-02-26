import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 */

/**
 * @author Andrew Calabro-Cavin
 * Shopping list application. Use hashtable to store a menu. 
 * Allow users to add items to a shopping list.
 * Methods to calc. ave, max, and min values on the list. 
 */
public class MarketApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Hashtable<String, Object> menuItems = new Hashtable<>();
		menuItems.put("nails", new Double(3.99));
		menuItems.put("screws", new Double(2.59));
		menuItems.put("hammer", new Double(7.69));
		menuItems.put("screwdriver", new Double(3.89));
		menuItems.put("wrench", new Double(3.59));
		menuItems.put("drill", new Double(22.69));
		menuItems.put("funnel", new Double(1.55));
		menuItems.put("duck tape", new Double(2.05));

		ArrayList<String> orderItems = new ArrayList<String>();
		ArrayList<Double> orderPrices = new ArrayList<Double>();

		String str;

		System.out.println("Welcome to Guenther's Hardware!\n");

		/*
		 * public Set<K> keySet(): Returns a Set view of the keys contained in this map.
		 * The set is backed by the map, so changes to the map are reflected in the set,
		 * and vice-versa.
		 */
		Set<String> keys = menuItems.keySet();

		String contProgram = "a";
		
		while (!contProgram.isEmpty()) {
			String cont = "Y";
			while (cont.equalsIgnoreCase("y")) {
				// display menu 
				displayMenu(keys, menuItems);

				System.out.println("\nWhat item would you like to order?");
				String userChoice = scan.nextLine();

				if (menuItems.containsKey(userChoice)) {
					System.out.printf("\nAdding %s to cart at $%s\n", userChoice, menuItems.get(userChoice)); // FIXME
					orderItems.add(userChoice);
					orderPrices.add((double) menuItems.get(userChoice));
					// go to cont question

				} else {
					System.out.println("Sorry, we don't stock that item. Please try again.\n");
					// go back to display menu
					continue;
				}

				System.out.println("\nWould you like to order anything else? (y/n)");
				cont = scan.nextLine();
			}
			System.out.println("\nThanks for your order!\nHere's what you got:\n");
			for (int i = 0; i < orderItems.size(); i++) {
				String item = orderItems.get(i);
				Double price = orderPrices.get(i);
				System.out.printf("%-20s $%s\n", item, price);
			}
			double ave = findAve(orderPrices);
			
			System.out.println("Average price per item in order was $" + ave);
			double max = findMax(orderPrices);
			System.out.println("Max price of order was $" + max);
			double min = findMin(orderPrices);
			System.out.println("Min price of order was $" + min);

			
			System.out.println("\nPress any key to continue.");
			contProgram = scan.nextLine();
			System.out.println("\n");
		}
		scan.close();
		
	}



	public static void displayMenu(Set<String> keys, Hashtable<String, Object> menuItems) {
		System.out.printf("%-20s %s\n=================================\n", "Item", "Price");
		// Obtaining iterator over set entries
		Iterator<String> itr = keys.iterator();		
		while (itr.hasNext()) {
			String str = itr.next();
			System.out.printf("%-20s $%s\n", str, menuItems.get(str));
		}
	}

	public static double findAve(ArrayList<Double> orderPrices) {

		double total = 0;
		for (int i = 0; i < orderPrices.size(); i++) {
			total = total + orderPrices.get(i);
		}
		double ave = total/orderPrices.size();
		
		return ave;
	}

	public static double findMax(ArrayList<Double> orderPrices) {

		double max = Collections.max(orderPrices);
		return max;
	}
	
	public static double findMin(ArrayList<Double> orderPrices) {
		
		double min = Collections.min(orderPrices);
		return min;
	}
	
}
