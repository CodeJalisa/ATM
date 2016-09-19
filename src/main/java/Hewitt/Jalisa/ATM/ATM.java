package Hewitt.Jalisa.ATM;

import Hewitt.Jalisa.ATM.Accounts;

import java.util.ArrayList;

/**
 * Created by jalisahewitt on 9/18/16.
 */
public class ATM {
    public ArrayList<User> arrayOfUsers;
    private double newAccountBalance;
    private Accounts userSelectedBankAccount;
    private String message;
    boolean On = false;

    public ATM() {
        arrayOfUsers = new ArrayList<User>();
    }

    protected String createUser(String name, String pass) {
        User temp = new User();
        temp.setUsername(name);
        temp.setPassword(pass);
        arrayOfUsers.add(temp);
        return message = "Success! Your username is:" + "name"+".";
    }

    protected User getUser(String userName, String userPassword) {
        for(int i =0; i <arrayOfUsers.size(); i++) {
            if((userName + userPassword).equals(arrayOfUsers.get(i).toString())) {
                return arrayOfUsers.get(i);
            }
        }
        return null;
    }

    protected String createNewBankAccount(String userName, String userPassword, Accounts.Type type) { //Do you know what im doing tho?? :)
        Accounts temp = new Accounts();
        temp.setAccountType(type);
        getUser(userName,userPassword).addBankAccounts(temp);
        message = "You have created a " + type + " account.";
        temp.addToHistory(message);
        return message;
    }

    public boolean authenticate(String user, String password) {
        for(int i = 0; i < arrayOfUsers.size(); i++) {
            if((user + password).equals(arrayOfUsers.get(i).toString())) {
                return true;
            }
        }
        return false;
    }

    protected String showAccountBalance(String userName, String userPassword, Accounts.Type type) {
        return message = "Your account balance is: " + getUser(userName,userPassword).getAccountType(type).getBalance();
    }

    protected String deposit(String userName, String userPassword, Accounts.Type type, double amount) {
        userSelectedBankAccount = getUser(userName,userPassword).getAccountType(type);
        newAccountBalance = userSelectedBankAccount.getBalance() + amount;
        userSelectedBankAccount.setBalance(newAccountBalance);
        message = "Success! You deposited $" + amount + " to your account.";
        userSelectedBankAccount.addToHistory(message);
        return message;
    }

    protected String withdraw(String userName, String userPassword, Accounts.Type type, double amount) {
        userSelectedBankAccount = getUser(userName,userPassword).getAccountType(type);
        if(userSelectedBankAccount.getBalance() - amount < 0) {
            return message = "You do not have sufficient funds for this transaction.";
        }
        else if(userSelectedBankAccount.getBalance() - amount >= 0) {
            newAccountBalance = userSelectedBankAccount.getBalance() - amount;
            userSelectedBankAccount.setBalance(newAccountBalance);
            message = "Success! You withdrew $" + amount + " from your account.";
            userSelectedBankAccount.addToHistory(message);
            return message;
        }
        return null;
    }

    protected String transfer(String userName, String userPassword, Accounts.Type transferFrom, Accounts.Type transferTo, double amount) {
        Accounts transferFrom1 = getUser(userName, userPassword).getAccountType(transferFrom);
        Accounts transferTo1 = getUser(userName, userPassword).getAccountType(transferTo);
        if(transferFrom1.getBalance() - amount > 0) {
            double newTransFromBalance = transferFrom1.getBalance() - amount;
                transferFrom1.setBalance(newTransFromBalance);
            double newTransTobalance = transferTo1.getBalance() + amount;
                transferTo1.setBalance(newTransTobalance);
                message = "Success! You were ab;e to transfer $" + amount;
                return message;
            }
            else {
                return message = "You did not have sufficient funds to transfer.";
            }

    }

    protected String showTransactionHistory(String userName, String userPassword, Accounts.Type type) {
        userSelectedBankAccount = getUser(userName,userPassword).getAccountType(type);
        return message = userSelectedBankAccount.getAccountHistory().toString();
    }











}
