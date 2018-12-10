package coe528.project;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class for manager
 */
public class AdminUIController implements Initializable {

    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnDeleteCustomer;

    @FXML
    private Button btnLogout;

    private SceneManager sceneManager;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleButtonAdd(ActionEvent event) {
        String message = "Enter username, password and initial balance\n";
        message += "separated by a space(e.g guest guest123 1000):";
        String details = Dialog.showInputDialog("Add Customer", message);
        if (details != null) {
            String[] data = details.split(" ");
            String username = data[0];
            String password = data[1];
            double initialBalance = Double.parseDouble(data[2]);
            boolean ok = AccountManager.createAccount(username, password, initialBalance);
            if (ok) {
                message = "Following customer created successfully.\n";
                message += "Username: " + username + "\n";
                message += "Password: " + password + "\n";
                message += "Balance: $" + initialBalance;
                Dialog.showMessage("Add Customer", message);
            } else {
                Dialog.showError("Error", "Customer with same username already exists!");
            }
        }
    }

    @FXML
    private void handleButtonDelete(ActionEvent event) {
        String username = Dialog.showInputDialog("Delete Customer", "Enter username of customer to delete:");
        if (username != null) {
            boolean ok = AccountManager.deleteAccount(username);
            if (ok) {
                Dialog.showMessage("Delete Customer", "Customer account successfully deleted.");
            } else {
                Dialog.showError("Error", "Customer with given username was not found!");
            }
        }
    }

    @FXML
    private void handleButtonLogout(ActionEvent event) {
        sceneManager.showLoginScene();
    }

    /**
     *
     * @param sceneManager
     */
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
