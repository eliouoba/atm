/** 
 * ATM.java
 *
 * Simulates an ATM, including withdrawals and transactions
 *
 * @author eliouoba
 * Wheaton College, CSCI 235, Fall 2020
 * 11/3/2020
 */

public class ATM {
	private double balance;
	private int twenties;	//# of twenties in account
	private int fives;		//# of fives in account	
	
	public ATM() {
		balance = 0;
		twenties = 0;
		fives = 0;
	}

	/** Adds twenties, amount must be positive */
	public void addTwenties(int amount) {
		twenties += amount;
		balance += 20.0 * amount;
	}

	/** Adds twenties, amount must be positive */
	public void addFives(int amount) {
		fives += amount;
		balance += 5.0 * amount;
	}

	/** Withdraws money. Amount must be a multiple of 5, and positive. Overdraft not allowed. */
	public void withdraw(int amount) {
		int amountWithdrawn = 0;
		while (twenties > 0 && balance >= 20 && amountWithdrawn + 20 <= amount && amount >= 20) {
			twenties--;
			balance -= 20;
			amountWithdrawn += 20;
		}
		while (fives > 0 && amountWithdrawn < amount) {
			fives--;
			balance -= 5;
			amountWithdrawn += 5;
		}
	}

	/** Gives relevant information: # of fives/twenties and the balance. */
	public String toString() {
		
		if (fives == 1 && twenties == 1) return "You now have $25.00 in your account: 1 twenty and 1 five.";

		else if (twenties == 1) return String.format("You now have $%,.2f in your account: 1 twenty and %,d fives.", balance(), fives()); 

		else if (fives == 1) return String.format("You now have $%,.2f in your account: %,d twenties and 1 five.", balance(), twenties());

		else return String.format("You now have $%,.2f in your account: %,d twenties and %,d fives.", balance(), twenties(), fives());
	}

	public int twenties() {
		return twenties;
	}

	public int fives() {
		return fives;
	}
	
	public double balance() {
		return balance;
	}
}