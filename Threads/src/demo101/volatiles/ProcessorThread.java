package demo101.volatiles;

public class ProcessorThread extends Thread {
	
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
