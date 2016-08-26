package demo101.volatiles;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		Processor processor = new Processor();
		
		processor.start();
		
		System.out.println("Press return key to stop..");
		
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		scanner.close();
		
		processor.shutDown();
		
	}
	
}

class Processor extends Thread {
	
	private volatile boolean flag = true;
	
	public void run() {
		while (flag) {
			System.out.println("Welcome");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutDown() {
		flag = false;
	}
}
