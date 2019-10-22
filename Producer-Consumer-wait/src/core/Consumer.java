package core;

import java.util.Queue;

public class Consumer implements Runnable {
	private static Queue<String> queue;

	public Consumer(Queue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		synchronized (queue) {
			while (!Producer.isBakingDone) {
				if (!queue.isEmpty()) {
					System.out.println(Thread.currentThread().getName() + " consumed " + queue.remove());
					queue.notifyAll();
				} else {
					try {
						System.out.println(Thread.currentThread().getName() + " calling wait because queue is empty!");
						queue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
