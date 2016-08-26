package demo111.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		// playAround();

		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 200; i++) {
			executorService.submit(new Runnable() {
				public void run() {
					Connection.getInstance().connect();
				}
			});
		}

		executorService.shutdown();

		executorService.awaitTermination(1, TimeUnit.DAYS);
	}

	public static void playAround() throws InterruptedException {
		// maintains the count..
		Semaphore semaphore = new Semaphore(1);

		semaphore.acquire();
		// semaphore.release();
		semaphore.tryAcquire(3, TimeUnit.SECONDS);

		System.out.println("Available permits: " + semaphore.availablePermits());
	}

}
