package exercise03;

import java.util.ArrayList;

public class Bank {

	private ArrayList<BankAccount> accountList = new ArrayList<>();	// getter
	private ArrayList<Customer> customerList = new ArrayList<>();	// getter
	
	public void addCustomer(Customer newCustomer) {
		customerList.add(newCustomer);
	}
	
	public void addAccount(BankAccount newAccount) {
		accountList.add(newAccount);
	}
	
	
}
