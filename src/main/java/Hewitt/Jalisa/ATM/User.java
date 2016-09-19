package Hewitt.Jalisa.ATM;

import Hewitt.Jalisa.ATM.Accounts;

import java.util.ArrayList;

/**
 * Created by jalisahewitt on 9/18/16.
 */
public class User {
    public ArrayList<Accounts> bankAccounts;
    private String username;
    private String password;

    User() {
        bankAccounts = new ArrayList<Accounts>();
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void addBankAccounts(Accounts bankAccount) {
        bankAccounts.add(bankAccount);
    }

    public Accounts getAccountType(Accounts.Type type){
        for(int i=0; i<bankAccounts.size();i++) {
            if(type == bankAccounts.get(i).getAccountType()) {
                return bankAccounts.get(i);
            }
        }
        return null;


    }
}
