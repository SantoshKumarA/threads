package demo103.multiple.locks;

public class Application {

	public static void main(String[] args) {
		Worker worker = new Worker();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				worker.process();
			}
		});

		long start = System.currentTimeMillis();

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				worker.process();
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();

		System.out.println("Time taken : " + (end - start));

		System.out.println("list1 size : " + worker.list1.size() + ", list2 size: " + worker.list2.size());
	}

}
