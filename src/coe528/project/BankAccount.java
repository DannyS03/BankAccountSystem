package coe528.project;


/**
 * This class defines a bank account in the application.
 *
 * This class can be read from a customer details file and also saved back to the same file. It
 * has the associated Customer details, bank balance and a list of all purchases maintained in a List.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author owner
 */
public class BankAccount {

    /* customer of the account */
    private Customer customer;

    /* account balance */
    private double balance;

    /* list of all purchases done in this account */
    private List<Purchase> purchases;

    /**
     * Class constructor that creates a new bank account.
     *
     * @param customer the account holder
     * @param initialBalance initial balance of the account
     */
    public BankAccount(Customer customer, double initialBalance) {
        this.customer = customer;
        balance = initialBalance;
        purchases = new ArrayList<Purchase>();
    }

    /**
     * Get the balance of the account.
     *
     * @return the balance of the account
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deposit money to the account.
     *
     * @param amount the amount of money to be deposited
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Withdraw money from the account
     *
     * @param amount the amount to be withdrawn
     * @return true if account has sufficient balance and amount was withdrawn,
     * else return false
     */
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get the account holder of the account
     *
     * @return the customer that holds this account
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Get the level of the account.
     *
     * @return level of the account
     */
    public String getLevel() {
        if (balance < 10000) {
            return "silver";
        } else if (balance < 20000) {
            return "gold";
        } else {
            return "platinum";
        }
    }

    /**
     * Add a new purchase for this account.
     *
     * @param p the new purchase to be added
     */
    public void addPurchase(Purchase p) {
        purchases.add(p);
    }

    /**
     * Get the list of all purchases of this account
     *
     * @return the list of purchases
     */
    public List<Purchase> getPurchases() {
        return purchases;
    }

    /**
     * Save this bank account details to a file
     *
     * @param filename the name of the file where this account will be saved
     */
    public void saveToFile(String filename) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(new File(filename)));
            pw.printf("%s,%s,%s\n", customer.getUsername(), customer.getPassword(), customer.getRole());
            pw.println(balance);
            for (Purchase p : purchases) {
                pw.printf("%d,%d,%f\n", p.getItem().getId(), p.getQuantity(), p.getFee());
            }
            pw.close();
        } catch (IOException ex) {
            System.out.println("ERROR: could not save BankAccount details: " + ex.getMessage());
        }
    }

}
