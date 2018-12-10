package coe528.project;



import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main application that starts with the login screen for customer and
 * manager login to the bank account system
 */
public class BankAccountApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.showLoginScene();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
