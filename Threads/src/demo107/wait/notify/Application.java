package demo107.wait.notify;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		
		final Processor processor = new Processor();
		
		Thread t1 = new Thread( new Runnable () {
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) { }
			}
		});
		
		Thread t2 = new Thread( new Runnable() {
			public void run() {
				try {
				processor.consume();
				} catch(InterruptedException e) {}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}

}

class Processor {
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