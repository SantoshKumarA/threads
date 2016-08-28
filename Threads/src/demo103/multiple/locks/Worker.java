package demo103.multiple.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

	private Random random = new Random();

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	public List<Integer> list1 = new ArrayList<>();
	public List<Integer> list2 = new ArrayList<>();

	public void process() {
		for (int i = 0; i < 1000; i++) {
			stepOne();
			stepTwo();
		}
	}

	public synchronized void stepOne() {
		synchronized (this) {
//		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			list1.add(random.nextInt(100));
		}
	}

	public void stepTwo() {
		synchronized (this) {
//		synchronized (lock2) {

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			list2.add(random.nextInt(100));
		}
	}
}
