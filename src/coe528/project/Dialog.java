package coe528.project;



import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

/**
 * Utility class to display pop up dialog with error or information text.
 */
public class Dialog {

    /**
     * Show a alert dialog with given error message
     *
     * @param title
     * @param message the error message to display
     */
    public static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(message);
        alert.setTitle(title);
        alert.showAndWait();
    }

    /**
     * Show a message dialog with given error message
     *
     * @param message the error message to display
     * @param title the title of the dialog
     */
    public static void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(message);
        alert.setTitle(title);
        alert.showAndWait();
    }

    /**
     * Show text input dialog for user to enter some string.
     *
     * @param title
     * @param message the message to display in the dialog
     * @return the String entered by user or else null if dialog canceled
     */
    public static String showInputDialog(String title, String message) {
        TextInputDialog inputDialog = new TextInputDialog("0");
        inputDialog.setTitle(title);
        inputDialog.setHeaderText(null);
        inputDialog.setContentText(message);
        Optional<String> value = inputDialog.showAndWait();
        if (value.isPresent()) {
            return value.get();
        } else {
            return null;
        }
    }

}
