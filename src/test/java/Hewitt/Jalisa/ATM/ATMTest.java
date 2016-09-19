package Hewitt.Jalisa.ATM;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by jalisahewitt on 9/19/16.
 */
public class ATMTest {
    @Before
    public void setUpTest() {
        ATM x = new ATM();
        x.createUser("Shea", "Butter");
    }

    @Test
    public void createUserTest() {
        ATM atm = new ATM();
        atm.createUser("Shea", "Butter");
        int expected = 1;
        int actual = atm.arrayOfUsers.size();
        assertEquals("Should be 1", expected, actual);
    }

    @Test
    public void authenticateTest() {
        ATM x = new ATM();
        x.createUser("Shea", "Butter");
        boolean expected = true;
        boolean actual = x.authenticate("Shea", "Butter");
        assertEquals(expected, actual);
    }

    @Test
    public void showAccountBalanceTest() {
        ATM x = new ATM();
        x.createUser("Shea", "Butter");
        x.createNewBankAccount("Shea", "Butter", Accounts.Type.CHECKING);
        x.deposit("Shea", "Butter", Accounts.Type.CHECKING, 5);
        String expected = "Your account balance is: 5.0";
        String actual = x.showAccountBalance("Shea", "Butter", Accounts.Type.CHECKING);
        assertEquals("5", expected, actual);
    }

    @Test
    public void depositTest() {
        ATM x = new ATM();
        x.createUser("Shea", "Butter");
        x.createNewBankAccount("Shea", "Butter", Accounts.Type.CHECKING);
        x.deposit("Shea", "Butter", Accounts.Type.CHECKING, 500);
        double expected = 500;
        double actual = x.getUser("Shea", "Butter").getAccountType(Accounts.Type.CHECKING).getBalance();
        assertEquals("Should deposit", expected, actual);
    }

    @Test
    public void withdrawTest() {
        ATM x = new ATM();
        x.createUser("Shea", "Butter");
        x.createNewBankAccount("Shea", "Butter", Accounts.Type.CHECKING);
        x.deposit("Shea", "Butter", Accounts.Type.CHECKING, 500);
        String expected = "Success! You withdrew $50 from your account.";
        String actual = x.withdraw("Shea", "Butter", Accounts.Type.CHECKING, 50);
        assertEquals("Withdraw 50 from account with 500 - should approve.", expected, actual);

    }
}

