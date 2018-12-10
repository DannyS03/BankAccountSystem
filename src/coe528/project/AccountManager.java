package coe528.project;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * This class implements functionality to validate manager/customer login, add a
 * customer, delete a customer, save a customer to a file and read a customer
 * from a file.
 */
public class AccountManager {

    /**
     * Check login of an user of the bank application.
     *
     * 
     */
    public static boolean checkLogin(String username, String password, String role) {
        if (role.equals("manager") && username.equals("admin") && password.equals("admin")) {
            return true;
        }

        File file = new File(username + ".txt");
        if (!file.exists()) {
            return false;
        }

        try {
            Scanner fileScanner = new Scanner(file);
            String line = fileScanner.nextLine();
            fileScanner.close();
            String[] data = line.split(",");
            if (data[0].equals(username) && data[1].equals(password) && data[2].equals(role)) {
                return true;
            } else {
                return false;
            }
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(AccountManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Read a bank account from a file.
     *
     * 
     */
    public static BankAccount readAccount(String filename, List<Item> shoppingList) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            String line = fileScanner.nextLine();
            String[] data = line.split(",");
            String username = data[0];
            String password = data[1];
            line = fileScanner.nextLine();
            double balance = Double.parseDouble(line);
            Customer c = new Customer(username, password);
            BankAccount cAcc = new BankAccount(c, balance);
            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                data = line.split(",");
                int itemId = Integer.parseInt(data[0]);
                int quantity = Integer.parseInt(data[1]);
                double fee = Double.parseDouble(data[2]);
                Item item = null;
                for (Item _item : shoppingList) {
                    if (_item.getId() == itemId) {
                        item = _item;
                        break;
                    }
                }
                Purchase p = new Purchase(item, quantity, fee);
                cAcc.addPurchase(p);
            }
            fileScanner.close();
            return cAcc;
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return null;
        }
    }

    /**
     * Save a bank account to file.
     *
     * 
     */
    public static void saveAccount(BankAccount acc) {
        Customer customer = acc.getCustomer();
        String username = customer.getUsername();
        String filename = username + ".txt";
        acc.saveToFile(filename);
    }

    /**
     * Create a new customer account in the bank.
     *
     * 
     */
    public static boolean createAccount(String username, String password, double initialBalance) {
        File file = new File(username + ".txt");
        if (file.exists()) {
            return false;
        }
        try {
            file.createNewFile();
            Customer customer = new Customer(username, password);
            BankAccount acc = new BankAccount(customer, initialBalance);
            acc.saveToFile(file.getAbsolutePath());
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Delete a customer account.
     *
     * 
     */
    public static boolean deleteAccount(String username) {
        File file = new File(username + ".txt");
        if (!file.exists()) {
            return false;
        } else {
            file.delete();
            return true;
        }
    }
}
