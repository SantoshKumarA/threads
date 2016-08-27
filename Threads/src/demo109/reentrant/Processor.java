package demo109.reentrant;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {

	private int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	private void increment() {
		for (int i = 0; i < 10000; i++)
			count++;
	}

	public void firstThread() throws InterruptedException {
		lock.lock();

		System.out.println("Waiting..");
		condition.await();
		System.out.println("Woken up..");

		try {
			increment();
		} finally {
			lock.unlock();
		}
	}

	public void secondThread() throws InterruptedException {

		Thread.sleep(1000);

		lock.lock();

		System.out.println("Press return key..");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		scanner.close();
		System.out.println("Got return key..");

		condition.signal();

		try {
			increment();
		} finally {
			System.out.println("second thread..");
			lock.unlock();
		}
	}

	public void finished() {
		System.out.println("Value of Count: " + count);
	}
}