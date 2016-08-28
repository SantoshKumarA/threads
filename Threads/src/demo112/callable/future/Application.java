package demo112.callable.future;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Application {

	public static void main(String[] args) {
//		olderWay();

		callableWay();

//		noReturnFuture();
	}

	public static void callableWay() {
		ExecutorService executorService = Executors.newCachedThreadPool();

		Future<Integer> future = executorService.submit(new Callable<Integer>() {

			public Integer call() throws Exception {
				Random random = new Random();
				int duration = random.nextInt(4000);

				System.out.println("Starting Callable..");

				if (duration > 2000) {
					throw new IOException("Sleeping for long period..");
				}

				Thread.sleep(duration);

				System.out.println("Finished Callable..");

				return duration;
			}
		});

		System.out.println("Does Future done ? " + future.isDone());

		// termination not needed as future.get() will block / wait for Callable
		// to complete..
		// executorService.awaitTermination(1, TimeUnit.DAYS);

		try {
			System.out.println("Value returned by Callable: " + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println(e);
			System.out.println();

			System.out.println(e.getMessage());
			System.out.println();

			IOException ex = (IOException) e.getCause();
			System.out.println(ex.getMessage());
		}

		System.out.println("Does Future done ? " + future.isDone());
	}

	public static void noReturnFuture() {
		ExecutorService executorService = Executors.newCachedThreadPool();

		Future<?> future = executorService.submit(new Callable<Void>() {
			public Void call() throws Exception {

				return null;
			}
		});

		System.out.println("Does no return future done ? " + future.isDone());

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Does no return future done ? " + future.isDone());
	}

	public static void olderWay() {
		ExecutorService executorService = Executors.newCachedThreadPool();

		executorService.submit(new Runnable() {

			@Override
			public void run() {
				Random random = new Random();
				int duration = random.nextInt(4000);

				System.out.println("Starting Runnable..");

				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("Finished Runnable..");
			}
		});

		executorService.shutdown();

	}
}
