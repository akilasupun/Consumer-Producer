package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Producer implements Runnable {
	public static volatile boolean isMenuFinished = false;

	private List<String> menu = new ArrayList<>(Arrays.asList("Chocolate", "Butter", "Strawbery", "Pineapple", "Coffee",
			"Fruitcake", "Coconut", "Sponge", "Apple", "Banana", "Chestnut", "Cup Cake", "Gingerbread", "Lemon",
			"Pancake", "Plumcake", "Raisin", "Rum", "Swissroll"));

	@Override
	public void run() {
		System.out.println("****Producer Run!");
		synchronized (this) {
			while (!menu.isEmpty()) {
				if (Application.table.size() < 5) {
					String bakedCake = menu.remove(0);
					System.out.println(
							Thread.currentThread().getName() + " Producer adding cake " + bakedCake + " to the table!");
					Application.table.add(bakedCake);

				} else {
					System.out.println("Producer calling wait() cause table is full!");
					try {
						this.notify();
						// Thread.sleep(100);
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			isMenuFinished = true;
		}
	}

}
