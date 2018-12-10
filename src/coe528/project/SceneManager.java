package coe528.project;



import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This class is responsible for showing all scenes in the stage and moving from
 * one scene to another.
 */
public class SceneManager {

    /* primary stage of the application */
    private Stage primaryStage;

    /**
     * Class constructor that initializes the primary stage of the application
     *
     * @param primaryStage primary stage of all scenes
     */
    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Load and display the customer UI screen to the primary stage.
     *
     * @param custAccount reference to BankAccount instance associated with the
     * customer
     * @param shoppingItems list of shopping items for purchase
     */
    public void showCustomerHomeScene(BankAccount custAccount, List<Item> shoppingItems) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coe528/project/CustomerUI.fxml"));
        //AnchorPane root = new AnchorPane();
        //loader.setRoot(root);
        //loader.setController(new LoginUIController());        
        BorderPane parent = null;
        try {
            parent = (BorderPane) loader.load();
            CustomerUIController controller = loader.getController();
            controller.setSceneManager(this);
            controller.setBankAccount(custAccount);
            controller.setShoppingItems(shoppingItems);
        } catch (IOException ex) {
            //Logger.getLogger(SceneManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(parent, 600, 400);
        primaryStage.setTitle("Bank Account Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Load and display the admin UI screen
     */
    public void showAdminHomeScene() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coe528/project/AdminUI.fxml"));
        AnchorPane root = new AnchorPane();
        //loader.setRoot(root);
        //loader.setController(new LoginUIController());        
        BorderPane parent = null;
        try {
            parent = (BorderPane) loader.load();
            AdminUIController controller = loader.getController();
            controller.setSceneManager(this);
        } catch (IOException ex) {
            ex.printStackTrace();
            //Logger.getLogger(SceneManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(parent, 434, 258);
        primaryStage.setTitle("Bank Account Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Load and display the Login UI screen
     */
    public void showLoginScene() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/coe528/project/LoginUI.fxml"));
        AnchorPane root = new AnchorPane();
        loader.setRoot(root);
        //loader.setController(new LoginUIController());        
        AnchorPane parent = null;
        try {
            parent = (AnchorPane) loader.load();
            LoginUIController controller = loader.getController();
            controller.setSceneManager(this);
        } catch (IOException ex) {
            ex.printStackTrace();
            //Logger.getLogger(SceneManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(parent, 450, 272);
        primaryStage.setTitle("Bank Account Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
