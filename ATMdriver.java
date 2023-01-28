/** 
 * ATMdriver.java
 *
 * Driver program to implement ATM class.
 *
 * @author eliouoba
 * Wheaton College, CSCI 235, Fall 2020
 * 11/3/2020
 */

import java.util.Scanner;					
import java.lang.Exception;

public class ATMdriver {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);

		ATM account = new ATM();

		double totalAmountAdded = 0;         //these are used later for the receipt
		double totalAmountWithdrawn = 0;		

		System.out.println("\nWelcome to my program to model an ATM. \nThis machine accepts $5 and $20 dollar bills. \nPlease make a transaction: \n");

		for(;;) {
		
			System.out.println("\t Press T to deposit twenty-dollar bills into your account.");
			System.out.println("\t Press F to deposit five-dollar bill into your account.");
			System.out.println("\t Press W to make a withdrawal from your account.");
			System.out.println("\t Press Q if you're finished.");

			try {
				String input = keyboard.nextLine();		//Used to store user input
				switch(input.toLowerCase()) {
					
					case "t":
						for(;;) {
							System.out.print("How many twenties would you like to add? \t");
							try {
								int num = Integer.parseInt(keyboard.nextLine());
								if (num <= 0) throw new Exception();
								account.addTwenties(num);
								totalAmountAdded += 20*num;
								break;
							} catch (Exception e) {
								System.out.print("Enter a positive integer, please. ");
							}
						}
						break;

					case "f":
						for(;;) {
							System.out.print("How many fives would you like to add? \t");
							try {
								int number = Integer.parseInt(keyboard.nextLine());	
								if (number <= 0) throw new Exception();
								account.addFives(number);
								totalAmountAdded += 5*number;
								break;
							} catch (Exception e) {
								System.out.print("Enter a positive integer, please. ");
							}
						}
						break;

					case "w":
						for(;;) {
							try {
								System.out.print("How much do you want to withdraw? Please enter a multiple of 5. \t");
								int amount = Integer.parseInt(keyboard.nextLine());			
								if (amount > account.balance()) 
									System.out.println("This amount is greater than the current balance. \n");
								else if (amount <= 0)
									System.out.println("Please request a positive number to withdraw. \n");
								else if (amount % 5 != 0) 
									System.out.println("Please request a multiple of 5. \n");
								else if (amount % 20 != 0 && (amount % 20)/5 > account.fives())
									System.out.println("Sorry, you don't have enough fives left. \n");
								else {
									account.withdraw(amount);
									totalAmountWithdrawn += amount;
									System.out.printf("%n\tYou have withdrawn $%.2f.%n%n", (double)amount);
									break;
								}
							} catch (Exception e) {
								System.out.println("Enter an integer please. \n");
							}
						}
						break;
					case "q":
						for(;;) {
							System.out.print("Would you like a receipt? \t");
							String receipt = keyboard.nextLine();
							
							if (receipt.trim().equalsIgnoreCase("Yes")) {
								System.out.println("\nOk. Here is your receipt:");
								System.out.println(" ________________________________________________");
								System.out.println("|                                                |");
								System.out.println("|                                                |");
								System.out.printf("|  You deposited a total of $%,-20.2f|%n", totalAmountAdded);
								System.out.printf("|  You withdrew a total of $%,-21.2f|%n", totalAmountWithdrawn);
								System.out.println("|                                                |");
								System.out.printf("|  Your current balance is $%,-21.2f|%n", (double)account.balance());
								System.out.printf("|       --Twenty-dollar bills: $%,-17d|%n", account.twenties());
								System.out.printf("|       --Five-dollar bills: $%,-19d|%n", account.fives());
								for (int i = 0; i < 8; i++)
										System.out.println("|                                                |");
								System.out.println("|________________________________________________|\n\n");
								break;
							} else if (receipt.trim().equalsIgnoreCase("No")) {
								System.out.print("Ok. ");
								break;
							} else System.out.print("Please enter \"yes\" or \"no.\" ");
						}
						System.out.println("Thank you, and have a nice day!\n\n");
						System.exit(0);
					default:
						throw new Exception();
				}
				System.out.println("\n" + account.toString() + "\n \n");
			} catch (Exception e) {
				System.out.println("Please choose a valid option: \n");
			}
		}
	}
} 