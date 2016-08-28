package demo102.synchronization;

import java.util.concurrent.atomic.AtomicInteger;

public class Application {

	private int count = 0;
	private AtomicInteger atomicInteger = new AtomicInteger(0);
	
	public static void main(String[] args) {
		new Application().doWork();
	}

	public synchronized void incrementCount() {
//	public void incrementCount() {
		count++; // count = count + 1
	}

	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					incrementCount();
					atomicInteger.getAndIncrement();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					incrementCount();
					atomicInteger.getAndIncrement();
				}
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
		}

		System.out.println("Value of count " + count);
		System.out.println("Value of Atomic integer w/t synchronized k/w " + atomicInteger.get());
	}
}
