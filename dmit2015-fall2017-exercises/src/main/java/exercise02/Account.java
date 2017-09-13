package exercise02;

import java.util.ArrayList;
import java.util.Date;

public class Account {
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", annualInterestRate=" + annualInterestRate
				+ ", dateCreated=" + dateCreated + ", name=" + name + ", transactions=" + transactions
				+ ", getMonthlyInterestRate()=" + getMonthlyInterestRate() + ", getMonthlyInterest()="
				+ getMonthlyInterest() + "]";
	}

	public double getMonthlyInterestRate() {
		return annualInterestRate / 12 / 100;
	}
	
	public double getMonthlyInterest() {
		return balance * getMonthlyInterestRate();
	}
	
	public void withdraw(double amount, String description) {
		if (amount > 0) {		
			balance -= amount;
			Transaction currentTransaction = new Transaction(
					'W', amount, balance, description);
			transactions.add(currentTransaction);			
		}
	}
	
	public void deposit(double amount, String description) {
		if (amount > 0) {
			balance += amount;
			Transaction currentTransaction = new Transaction(
					'D', amount, balance, description);
			transactions.add(currentTransaction);
		}
	}

	private int id;						// getter/setter
	private double balance;				// getter/setter
	private double annualInterestRate;	// getter/setter
	private Date dateCreated = new Date();	// getter
	private String name;				// getter/setter
	private ArrayList<Transaction> transactions = new ArrayList<>();	// getter

	public Account() {
	}

	public Account(int id, double balance) {
		this.id = id;
		this.balance = balance;
	}

	public Account(int id, double balance, String name) {
		super();
		this.id = id;
		this.balance = balance;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;

	}

	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
}
