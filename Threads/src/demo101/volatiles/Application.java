package demo101.volatiles;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		
		ProcessorThread processor = new ProcessorThread();
		
		processor.start();
		
		System.out.println("Press return key to stop..");
		
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		scanner.close();
		
		processor.shutDown();
	}
	
}
