package demo109.reentrant;

public class Application {

	public static void main(String[] args) throws InterruptedException {

		final Processor processor = new Processor();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.firstThread();
				} catch (InterruptedException e) {
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.secondThread();
				} catch (InterruptedException e) {
				}
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		processor.finished();
	}
}
