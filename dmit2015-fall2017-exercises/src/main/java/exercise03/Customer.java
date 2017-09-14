package exercise03;

public class Customer {

	private int id;								// getter/setter
	private BankAccount savingAccount;		// getter/setter
	private BankAccount checkingAccount;	// getter/setter
	
	public void addSavingAccount() {
		savingAccount = new SavingsAccount();
	}
	
	public void addCheckingAcount( ) {
		checkingAccount = new CheckingAccount();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BankAccount getSavingAccount() {
		return savingAccount;
	}

	public void setSavingAccount(BankAccount savingAccount) {
		this.savingAccount = savingAccount;
	}

	public BankAccount getCheckingAccount() {
		return checkingAccount;
	}

	public void setCheckingAccount(BankAccount checkingAccount) {
		this.checkingAccount = checkingAccount;
	}
	
	
}
