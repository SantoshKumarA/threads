package demo104.thread.pool;

public class ProcessorThread extends Thread {

	private int id;

	public ProcessorThread(int id) {
			this.id = id;
		}

	public void run() {

		System.out.println("Thread " + this.id + " started..");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread " + this.id + " completed..");

	}

}
