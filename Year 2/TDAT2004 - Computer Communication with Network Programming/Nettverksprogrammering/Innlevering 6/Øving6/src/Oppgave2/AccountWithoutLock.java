package Oppgave2;

import java.util.*;
import javax.persistence.*;
import java.io.*;

@Entity
@NamedQuery(name="findAllAccounts", query="SELECT COUNT(o) from AccountWithoutLock o")

public class AccountWithoutLock implements Serializable{

    @Id
    private long accountNR;
    private double balance;
    private String owner;

    public AccountWithoutLock(){}

    public AccountWithoutLock(long accountNR, double balance, String owner){
        this.accountNR = accountNR;
        this.balance = balance;
        this.owner = owner;

    }

    public long getAccountNR() {
        return accountNR;
    }

    public void setAccountNR(long accountNR) {
        this.accountNR = accountNR;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }


    public String getOwner(){
        return owner;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }

    public void withdraw(double amount){
        double newBalance = this.balance-amount;
        setBalance(newBalance);

    }

    public void deposit(double amount){
        double newBalance = this.balance+amount;
        setBalance(newBalance);

    }

    public String toString(){
        return "Account: nr: " + accountNR + " .Balance: " + balance + " .Owner " + owner;
    }
}
