package demo105.count.down;

import java.util.concurrent.CountDownLatch;

public class ProcessorThread extends Thread {
	private CountDownLatch countDownLatch;

	public ProcessorThread(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	public void run() {

		System.out.println("Thread started.. ");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.countDownLatch.countDown();
		System.out.println(countDownLatch.getCount());
	}
}
