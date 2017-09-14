package exercise03;

public class SavingsAccount extends BankAccount {

	@Override
	public void withdraw(double amount) {
		if( balance >= amount) {
			balance -= amount;
		}
	}

}
