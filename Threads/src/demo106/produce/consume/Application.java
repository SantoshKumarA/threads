package demo106.produce.consume;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Application {

	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread( new Runnable() {
			public void run() {
				producer();
			}
		});
		
		Thread t2 = new Thread( new Runnable() {
			public void run() {
				consumer();
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}

	public static void producer() {
		Random random = new Random();

		while (true) {
			try {
				queue.put(random.nextInt(100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void consumer() {
		Random random = new Random();

		while (true) {
			try {
				Thread.sleep(100);

				if (random.nextInt(10) == 0) {
					System.out.println("Taken value " + queue.take() + ", queue size " + queue.size());
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
