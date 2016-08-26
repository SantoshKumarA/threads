package demo111.semaphore;

import java.util.concurrent.Semaphore;

public class Connection {
	private Connection() {

	}

	private static Connection connection = new Connection();

	// to LIMIT number of connections..
	private Semaphore semaphore = new Semaphore(10);

	// to ensure the order the threads accessed semaphore.acquire - gets permit
	// first when available..
	// private Semaphore semaphore = new Semaphore(10, true);

	public static Connection getInstance() {
		return connection;
	}

	private int connections = 0;

	public void connect() {
		try {
			semaphore.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			doConnect();
		} finally {
			semaphore.release();
		}
	}

	public void doConnect() {

		synchronized (this) {
			connections++;
			System.out.println("Current Connections: " + connections);
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		synchronized (this) {
			connections--;
		}
	}
}
