package exercise02;

import java.util.Date;

public class Transaction {

	@Override
	public String toString() {
		return "\nTransaction [date=" + date + ", type=" + type + ", amount=" + amount + ", balance=" + balance
				+ ", description=" + description + "]";
	}

	private Date date = new Date();	// getter/setter
	private char type;				// getter/setter
	private double amount;			// getter/setter
	private double balance;			// getter/setter
	private String description;		// getter/setter
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Transaction(char type, double amount, double balance, String description) {
		super();
		this.type = type;
		this.amount = amount;
		this.balance = balance;
		this.description = description;
	}
	
}
