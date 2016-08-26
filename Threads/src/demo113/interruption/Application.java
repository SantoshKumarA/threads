package demo113.interruption;

import java.util.Random;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		
		Thread t1 = new Thread( new Runnable() {
			
			@Override
			public void run() {
				Random random = new Random();
				
				for ( int i = 0; i < 1E8; i++) {
					
					/*if (Thread.currentThread().isInterrupted()) {
						System.out.println("Interrupted at " + i);
						break;
					}*/
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						System.out.println("Interrupted at " + i);
						break;
					}
					
					Math.sin(random.nextDouble());
				}
				
			}
		});
		
		System.out.println("Starting..");
		t1.start();
		
		Thread.sleep(500);
		
		// sets only the flag
		t1.interrupt();
		
		t1.join();
		System.out.println("Finished..");
	}

}
