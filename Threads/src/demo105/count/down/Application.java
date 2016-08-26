package demo105.count.down;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(5);

		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		for (int i = 0; i < 5; i++) {
			executorService.submit(new Processor(countDownLatch));
		}
		
		executorService.shutdown();
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Completed..");
	}
}

class Processor extends Thread {
	private CountDownLatch countDownLatch;

	public Processor(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	public void run() {

		System.out.println("Thread started.. ");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.countDownLatch.countDown();
	}
}
