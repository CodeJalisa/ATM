package Hewitt.Jalisa.ATM;

import java.util.ArrayList;

/**
 * Created by jalisahewitt on 9/18/16.
 */
public class Accounts {
    private double balance;
    private ArrayList<String> accountHistory;
    private Type accountType;

    enum Type{
        CHECKING, SAVINGS, INVESTMENT
    }

    protected void addToHistory(String x){
        accountHistory.add(x);

    }

    public ArrayList<String> getAccountHistory(){
        return accountHistory;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Type getAccountType() {
        return accountType;
    }

    void setAccountType(Type type) {
        accountType = type;
    }

}
