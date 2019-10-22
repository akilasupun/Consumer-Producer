package core;

public class Consumer implements Runnable {

	@Override
	public void run() {
		System.out.println("****Consumer Run!");
		synchronized (this) {
			while (!Producer.isMenuFinished) {
				if (!Application.table.isEmpty()) {
					System.out
							.println(Thread.currentThread().getName() + " Consumer eat " + Application.table.remove(0));

				} else {
					try {
						System.out.println("Consumer calling wait() cause table is empty!");

						// this.notify();
						this.wait();
						// Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

		}
	}
}