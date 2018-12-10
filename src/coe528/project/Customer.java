package coe528.project;




/**
 * This class defines a Customer of the bank account application.
 */
public class Customer extends User {

    /**
     *
     * @param username
     * @param password
     */
    public Customer(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() {
        return "customer";
    }
}
