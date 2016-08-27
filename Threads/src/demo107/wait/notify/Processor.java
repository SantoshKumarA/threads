package demo107.wait.notify;

import java.util.Scanner;

public class Processor {

	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Thread started producing..");
			wait();
			System.out.println("Resumed..");
		}
	}

	public void consume() throws InterruptedException {

		Scanner scanner = new Scanner(System.in);
		Thread.sleep(3000);

		synchronized (this) {
			System.out.println("waiting for return key..");
			scanner.nextLine();
			scanner.close();
			System.out.println("Thread notifying a waiting thread..");
			notify();
			Thread.sleep(5000);
		}
	}
}
