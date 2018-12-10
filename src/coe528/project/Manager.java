package coe528.project;




/**
 * This class defines the Manager of the bank account application.
 */
public class Manager extends User {

    /**
     *
     * z
     */
    public Manager(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() {
        return "manager";
    }

}
