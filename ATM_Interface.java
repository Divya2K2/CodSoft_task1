package Task2;

import java.util.Scanner;


class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Insufficient funds!");
            return false;
        }
    }
}

class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void performTransaction(int option) {
        Scanner scanner = new Scanner(System.in);
        double amount;

        switch (option) {
            case 1:
                System.out.print("Enter withdrawal amount: ");
                amount = scanner.nextDouble();
                if (amount > 0) {
                    if (userAccount.withdraw(amount)) {
                        System.out.println("Withdrawal successful. Remaining balance: " + userAccount.getBalance());
                    }
                } else {
                    System.out.println("Invalid amount for withdrawal.");
                }
                break;
            case 2:
                System.out.print("Enter deposit amount: ");
                amount = scanner.nextDouble();
                if (amount > 0) {
                    userAccount.deposit(amount);
                    System.out.println("Deposit successful. New balance: " + userAccount.getBalance());
                } else {
                    System.out.println("Invalid amount for deposit.");
                }
                break;
            case 3:
                System.out.println("Current balance: " + userAccount.getBalance());
                break;
            case 4:
                System.out.println("Exiting. Thank you!");
                System.exit(0);
            default:
                System.out.println("Invalid option.");
        }
    }
}


public class ATM_Interface 
{
	 public static void main(String[] args) {
	        BankAccount userAccount = new BankAccount(1000.0);
	        ATM atm = new ATM(userAccount);

	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            atm.displayMenu();

	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();

	            atm.performTransaction(choice);
	        }
	    }
}
