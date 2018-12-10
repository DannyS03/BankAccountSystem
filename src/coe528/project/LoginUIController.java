package coe528.project;



import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class for LoginUI screen
 */
public class LoginUIController implements Initializable {

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private ComboBox<String> cmbxRole;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnCancel;

    private SceneManager sceneManager;

    /* list of shopping items */
    private List<Item> shoppingItems = new ArrayList<Item>();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbxRole.getSelectionModel().selectFirst();
        readItemsFromFile("items.txt");
    }

    /**
     * Load details of shopping items from a file
     */
    private void readItemsFromFile(String filename) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2]);
                Item item = new Item(id, name, price);
                shoppingItems.add(item);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: failed to load items from file items.txt");
        }
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String role = cmbxRole.getSelectionModel().getSelectedItem();
        if (username.length() == 0) {
            Dialog.showError("Login Error", "Please enter username !");
            return;
        }
        if (password.length() == 0) {
            Dialog.showError("Login Error", "Please enter password !");
            return;
        }
        boolean loginValid = AccountManager.checkLogin(username, password, role);
        if (loginValid) {
            if (username.equals("admin")) {
                sceneManager.showAdminHomeScene();
            } else {
                BankAccount custAcc = AccountManager.readAccount(username + ".txt", shoppingItems);
                sceneManager.showCustomerHomeScene(custAcc, shoppingItems);
            }
        } else {
            Dialog.showError("Login Error", "Invalid username/password !");
        }
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        Platform.exit();
    }

    /**
     *
     * @param sceneManager
     */
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

}
