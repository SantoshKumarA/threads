package demo105.count.down;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(5);

		ExecutorService executorService = Executors.newFixedThreadPool(3);

		// change the limit to 15 and run
		for (int i = 0; i < 5; i++) {
			executorService.submit(new ProcessorThread(countDownLatch));
		}

		executorService.shutdown();

		System.out.println(countDownLatch.getCount());
		try {
			// no thread can cross this until it becomes zero.
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Completed main thread execution..");
	}
}
