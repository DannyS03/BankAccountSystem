package coe528.project;

/**
 * @author Danny Salman - 500754576
 * Overview 
 *   This abstract class defines a user of the bank account application which can be the
 *   manager or any customer. This is immutable class and promotes object proliferation. 
 *  
 *   AF(c): c.getRole()
 *   RI(c): c.username.isEmpty() and c.username == null
 *          c.password.isEmpty and c.password == null returns false, 
 *          otherwise true

 *   REQUIRES: string parameters username and password defined for customer or manager
 *   MODIFIES: None
 *   EFFECTS: returns string parameters, and also incorporates user information 
 *   in the getRole() which is saved in a file and used in order for the user to 
 *   login as customer or manager
 
 */
public abstract class User {

    /* username of the user */
    private String username;

    /* password of the user */
    private String password;

    /**
     * Class constructor that initializes username and password
     *
     * @param username username of the user
     * @param password password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /* get the role of the user */

    /**
     *
     * @return role
     */

    public abstract String getRole();
    
   @Override
    public String toString(){
        return "Username " + getUsername() + "," + "Password " + getPassword();
        
    }
    
    public boolean RepOk(){
    // EF : Returns false if the rep invariant holds for this; if string null and void.
       if((username.isEmpty() && username == null) && (password.isEmpty() && password == null)){
        return false;
    }
        else  {
        return true;
                }
    }
        
}
