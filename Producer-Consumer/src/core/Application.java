package core;

import java.util.ArrayList;
import java.util.List;

public class Application {
	public static List<String> table = new ArrayList<>();

	public static void main(String[] args) {
		// new Producer().run();
		// new Consumer().run();

		Thread prodcucerThread = new Thread(new Producer(), "PROD_THRD");
		Thread consumerThread = new Thread(new Consumer(), "CONS_THRD");

		prodcucerThread.start();
		consumerThread.start();

		new Consumer().run();
	}
}
