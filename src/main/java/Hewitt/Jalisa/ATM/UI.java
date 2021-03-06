package Hewitt.Jalisa.ATM; /**
 * Created by jalisahewitt on 9/18/16.
 */
import Hewitt.Jalisa.ATM.ATM;
import Hewitt.Jalisa.ATM.Accounts;

import java.util.Scanner;
public class UI {
        private String userName;
        private String userPassword;
        private Accounts.Type userAccountType;
        private Accounts.Type userTransferToAccountType;
        private double userAmount;
        private String message;
        public Scanner scan = new Scanner(System.in);
        private int userFirstChoice;
        private int userAccountChoice;
        private int userTransferToAccountChoice;
        private int userTransactionChoice;
        private String newUserName;
        private String newUserPassword;
        private boolean userSession = false;
        Hewitt.Jalisa.ATM.ATM ATM = new ATM();

        public void Start() {
            ATM.createUser("Jalisa", "Password1");
            ATM.createNewBankAccount("Jalisa", "Password1", Accounts.Type.CHECKING);
            ATM.createNewBankAccount("Jalisa", "Password1", Accounts.Type.SAVINGS);
            ATM.createNewBankAccount("Jalisa", "Password1", Accounts.Type.INVESTMENT);
            ATM.deposit("Jalisa", "Password1", Accounts.Type.CHECKING, 100);
            ATM.deposit("Jalisa", "Password1", Accounts.Type.SAVINGS, 500);
            ATM.deposit("Jalisa", "Password1", Accounts.Type.INVESTMENT, 1000);
            ATM.On = true;

            while (ATM.On) {

                homeMenu();

                if (userFirstChoice == 1) {

                    getLoginInfo();

                    if (authenticateUser(userName, userPassword) == true) {
                        userSession = true;
                        while (userSession) {
                            menuOptionsScreen();
                            switch (userTransactionChoice) {
                                case 1:
                                    selectAccount();
                                    selectAmount();
                                    System.out.println(ATM.withdraw(userName, userPassword, userAccountType, userAmount));
                                    break;

                                case 2:
                                    selectAccount();
                                    selectAmount();
                                    System.out.println(ATM.deposit(userName, userPassword, userAccountType, userAmount));
                                    break;

                                case 3:
                                    selectAccount();
                                    selectAccountTransferTo();
                                    selectAmount();
                                    System.out.println(ATM.transfer(userName, userPassword, userAccountType, userTransferToAccountType, userAmount));
                                    break;

                                case 4:
                                    selectAccount();
                                    System.out.println(ATM.showAccountBalance(userName, userPassword, userAccountType));
                                    break;

                                case 5:
                                    selectAccount();
                                    System.out.println(ATM.showTransactionHistory(userName, userPassword, userAccountType));
                                    break;

                                case 6:
                                    createBankAccount();
                                    break;

                                case 7:
                                    userSession = false;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Your account name and password did not match our records. Please try again!");
                    }
                } else if (userFirstChoice == 2) {
                    System.out.println(createNewUser());
                    createBankAccount();
                } else if (userFirstChoice == 100) { // 99 - Secret command to turn off
                    System.out.println("Goodbye.");
                    ATM.On = false;
                }
            }
        }

        public int homeMenu() {
            message = "\nWelcome to Lovely Bank! \nPlease select a number to indicate what you would like to do: \n  1: Login   \n  2: Create User ";
            System.out.println(message);
            userFirstChoice = scan.nextInt();
            return userFirstChoice;
        }

        public int menuOptionsScreen() {
            System.out.println("\nWhat would you like to do? \n 1: Withdraw \n 2: Deposit \n 3: Transfer \n 4: Check Balance \n " +
                    "5: Print Transaction History \n 6: Create New Account \n 7: Log out");
            return userTransactionChoice = scan.nextInt();
        }

        public String createNewUser() {
            System.out.println("Please enter a username.");
            newUserName = scan.next();
            System.out.println("Please enter a password.");
            newUserPassword = scan.next();
            if (!userNameAndPassWordAlreadyTaken()) {
                return ATM.createUser(newUserName, newUserPassword);
            }
            return message = "This account is already taken. Try again!";
        }

        public boolean userNameAndPassWordAlreadyTaken() {
            for (int i = 0; i < ATM.arrayOfUsers.size(); i++) {
                if (ATM.arrayOfUsers.get(i).toString().equals(newUserName + newUserPassword + "")) {
                    System.out.println("This account already exist!");
                    return true;
                }
            }
            return false;
        }

        public void createBankAccount() {
            System.out.println("What kind of account would you like to create? \n 1: Checking \n 2: Savings \n 3: Investment");
            userAccountChoice = scan.nextInt();

            switch (userAccountChoice) {
                case 1:
                    ATM.createNewBankAccount(newUserName, newUserPassword, Accounts.Type.CHECKING);
                    break;
                case 2:
                    ATM.createNewBankAccount(newUserName, newUserPassword, Accounts.Type.SAVINGS);
                    break;
                case 3:
                    ATM.createNewBankAccount(newUserName, newUserPassword, Accounts.Type.INVESTMENT);
                    break;

                default:
                    System.out.println("Please try again.");
            }
        }

        public Accounts.Type selectAccount() {
            System.out.println("Which account would to use? \n 1: Checking \n 2: Savings \n 3: Investment");
            userAccountChoice = scan.nextInt();

            switch (userAccountChoice) {
                case 1:
                    userAccountType = ATM.getUser(userName, userPassword).getAccountType(Accounts.Type.CHECKING).getAccountType();
                    break;
                case 2:
                    userAccountType = ATM.getUser(userName, userPassword).getAccountType(Accounts.Type.SAVINGS).getAccountType();
                    break;
                case 3:
                    userAccountType = ATM.getUser(userName, userPassword).getAccountType(Accounts.Type.INVESTMENT).getAccountType();
                    break;

                default:
                    System.out.println("That's not a choice...");
            }
            return userAccountType;
        }

        public Accounts.Type selectAccountTransferTo() {
            System.out.println("Which account would to transfer to? \n 1: Checking \n 2: Savings \n 3: Investment");
            userTransferToAccountChoice = scan.nextInt();

            switch (userTransferToAccountChoice) {
                case 1:
                    userTransferToAccountType = ATM.getUser(userName, userPassword).getAccountType(Accounts.Type.CHECKING).getAccountType();
                    break;
                case 2:
                    userTransferToAccountType = ATM.getUser(userName, userPassword).getAccountType(Accounts.Type.SAVINGS).getAccountType();
                    break;
                case 3:
                    userTransferToAccountType = ATM.getUser(userName, userPassword).getAccountType(Accounts.Type.INVESTMENT).getAccountType();
                    break;

                default:
                    System.out.println("That's not a choice....");
            }
            return userTransferToAccountType;
        }

        public double selectAmount() {
            System.out.println("Enter an amount: ");
            return userAmount = scan.nextDouble();
        }

        public void getLoginInfo() {
            System.out.println("Please enter your: ");
            System.out.print("Username: ");
            userName = scan.next();
            System.out.print("Password: ");
            userPassword = scan.next();
        }

        public boolean authenticateUser(String user, String pass) {
            for (int i = 0; i < ATM.arrayOfUsers.size(); i++) {
                if ((user + pass).equals(ATM.arrayOfUsers.get(i).toString())) {
                    return true;
                }
            }
            return false;
        }

}



