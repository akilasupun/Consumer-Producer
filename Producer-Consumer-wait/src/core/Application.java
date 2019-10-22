package core;

import java.util.LinkedList;
import java.util.Queue;

public class Application {

	private static Queue<String> queue = new LinkedList<String>();

	public static void main(String[] args) {
		Thread producerThread = new Thread(new Producer(queue), "PROD_THRD_1");
		Thread consumerThread = new Thread(new Consumer(queue), "CONS_THRD_01");
		Thread consumerThread2 = new Thread(new Consumer(queue), "CONS_THRD_02");

		producerThread.start();
		consumerThread.start();
		consumerThread2.start();

		try {
			producerThread.join();
			consumerThread.join();
			consumerThread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
