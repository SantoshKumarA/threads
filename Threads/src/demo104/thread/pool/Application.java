package demo104.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		for (int i = 0; i < 5; i++) {
			executorService.submit(new Processor(i));
		}
		
		executorService.shutdown();
		
		System.out.println("All the tasks as submitted..");
		
		try {
			executorService.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All Threads completed..");
	}

}

class Processor extends Thread {
	
	private int id;
	public Processor(int id) {
		this.id = id;
	}
	public void run() {
		
		System.out.println("Thread " + this.id + " started..");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread " + this.id + " completed..");
		
	}
	
}