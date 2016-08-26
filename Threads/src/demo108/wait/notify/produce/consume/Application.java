package demo108.wait.notify.produce.consume;

import java.util.LinkedList;
import java.util.Random;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		
		final Processor processor = new Processor();
		
		Thread t1 = new Thread ( new Runnable () {
			public void run () {
				try {
					processor.produce();	
				} catch (InterruptedException e) {}
			}
		});
		
		Thread t2 = new Thread ( new Runnable () {
			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
}

class Processor {

	private LinkedList<Integer> list = new LinkedList<>();
	private final int LIMIT = 10;
	private Object lock = new Object();
	
	public void produce() throws InterruptedException {
		int value = 0;
		while (true) {
			synchronized (lock) {
				while (list.size() == LIMIT) {
					lock.wait();
				}
				
				list.add(value++);
				lock.notify();
			}
		}
	}
	
	public void consume() throws InterruptedException {

		Random random = new Random();
		while (true) {
			synchronized (lock) {
				
				while (list.size() == 0) {
					lock.wait();
				}
				
				System.out.print("Size of list: " + list.size());
				int value = list.removeFirst();
				System.out.println("; removed value: " + value);
				
				lock.notify();
			}
			
			Thread.sleep(random.nextInt(1000));
		}
	}
}