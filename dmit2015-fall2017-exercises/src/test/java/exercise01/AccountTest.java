package exercise01;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class AccountTest {

	@Test
	public void testAll() {
		Account account1 = new Account(1122, 20000);
		account1.setAnnualInterestRate(4.5);
		account1.withdraw(2500);
		account1.deposit(3000);
		// check that the balance is 20500
		assertEquals(20500, account1.getBalance(), 0);
		// check the monthly interest is $73.12
		assertEquals(76.88, account1.getMonthlyInterest(), 0.005);
		// check the dateCreated is today
		Date today = new Date();
		assertEquals(0, account1.getDateCreated().compareTo( today));
	}

	
	
	
	
	
	
	
	
}
