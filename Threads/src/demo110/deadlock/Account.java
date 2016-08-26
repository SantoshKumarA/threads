package demo110.deadlock;

public class Account {

	private int balance = 10000;
	
	public int getBalance() {
		return this.balance;
	}
	
	public void deposit(int amount) {
		this.balance += amount;
	}
	
	public void withdraw(int amount) {
		this.balance -= amount;
	}
	
	public static void transfer(Account accountFrom, Account accountTo, int amount) {
		accountFrom.withdraw(amount);
		accountTo.deposit(amount);
	}
}
