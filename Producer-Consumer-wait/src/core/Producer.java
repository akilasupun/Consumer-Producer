package core;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Producer implements Runnable {
	private static List<String> menu = new LinkedList<String>(Arrays.asList("Chocolate", "Butter", "Strawbery",
			"Pineapple", "Coffee", "Fruitcake", "Coconut", "Sponge", "Apple", "Banana", "Chestnut", "Cup Cake",
			"Gingerbread", "Lemon", "Pancake", "Plumcake", "Raisin", "Rum", "Swissroll"));

	private static Queue<String> queue;

	public static boolean isBakingDone = false;

	public Producer(Queue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		synchronized (queue) {
			while (!menu.isEmpty()) {
				if (queue.size() < 5) {
					String bakedCake = menu.remove(0);
					System.out.println(Thread.currentThread().getName() + " baked " + bakedCake);
					queue.add(bakedCake);

					queue.notifyAll();
				} else {
					try {
						System.out.println(Thread.currentThread().getName() + " calling wait because queue is full!");
						queue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			isBakingDone = true;
		}
	}
}
