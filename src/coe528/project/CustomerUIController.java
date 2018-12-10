package coe528.project;



import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
//import javax.swing.table.TableColumn;

/**
 * FXML Controller class for customer
 */
public class CustomerUIController implements Initializable {

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblBalance;

    @FXML
    private Button btnDeposit;

    @FXML
    private Button btnWithdraw;

    @FXML
    private Label lblLevel;

    @FXML
    private Button btnLogout;

    @FXML
    private TableView table;

    @FXML
    private TableColumn colItem;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colFee;

    @FXML
    private TableColumn colTotal;

    /* BankAccount associated with this customer */
    private BankAccount account;

    /* the scene manager */
    private SceneManager sceneManager;

    /* list of items that can be purchased */
    private List<Item> shoppingItems;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     *
     * @param account
     */
    public void setBankAccount(BankAccount account) {
        this.account = account;
        updateAccountDetails();
    }

    /**
     *
     * @param sceneManager
     */
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     *
     * @param shoppingItems
     */
    public void setShoppingItems(List<Item> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }

    @FXML
    private void handleDepositButtonClicked(ActionEvent event) {
        String value = Dialog.showInputDialog("Deposit Money", "Please enter amount in dollars:");
        if (value != null) {
            double amount = Double.parseDouble(value);
            if (amount <= 0) {
                Dialog.showError("Error", "Invalid amount! Must be positive number.");
            } else {
                account.deposit(amount);
                updateAccountDetails();
                String message = String.format("$%.3f successfully depsoited to account.", amount);
                Dialog.showMessage("Deposit", message);
            }
        }
    }

    @FXML
    private void handleWithdrawButtonClicked(ActionEvent event) {
        String value = Dialog.showInputDialog("Withdraw Money", "Please enter amount in dollars:");
        if (value != null) {
            double amount = Double.parseDouble(value);
            if (amount <= 0) {
                Dialog.showError("Error", "Invalid amount! Must be positive number.");
            } else {
                boolean ok = account.withdraw(amount);
                if (ok) {
                    String message = String.format("$%.3f successfully withdrawn from account.", amount);
                    Dialog.showMessage("Deposit", message);
                    updateAccountDetails();
                } else {
                    Dialog.showError("Withdraw", "Insufficient account balance for specified withdrawal!");
                }
            }
        }
    }

    @FXML
    private void handlePurchaseButtonClicked(ActionEvent event) {
        javafx.scene.control.Dialog<Pair<Item, String>> dialog = new javafx.scene.control.Dialog<>();
        dialog.setTitle("Purchase an Item");
        dialog.setHeaderText("Select item and quantity to purchase");
        ButtonType submitBtnType = new ButtonType("Submit", ButtonBar.ButtonData.APPLY.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(submitBtnType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<Item> items = new ComboBox<Item>(FXCollections.observableList(shoppingItems));
        items.getSelectionModel().selectFirst();
        TextField txtQuantity = new TextField("0");

        grid.add(new Label("Choose Item:"), 0, 0);
        grid.add(items, 1, 0);
        grid.add(new Label("Quantity:"), 0, 1);
        grid.add(txtQuantity, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == submitBtnType) {
                Item item = items.getSelectionModel().getSelectedItem();
                String qtyStr = txtQuantity.getText();
                return new Pair<>(item, qtyStr);
            }
            return null;
        });

        Optional<Pair<Item, String>> input = dialog.showAndWait();
        if (input.isPresent()) {
            Pair<Item, String> data = input.get();
            Item item = data.getKey();
            int qty = Integer.parseInt(data.getValue());
            double price = item.getPrice();
            double fee = 0;
            double subtotalCost = price * qty;
            if (subtotalCost < 50) {
                Dialog.showError("Error", "Total item cost must be at least $50.0!");
                return;
            }
            String custLevel = account.getLevel();
            if (custLevel.equals("silver")) {
                fee = 20;
            } else if (custLevel.equals("gold")) {
                fee = 10;
            }
            double totalCost = subtotalCost + fee;
            if (account.getBalance() < totalCost) {
                String message = "You do not have sufficient account balance!\n";
                message += "Account Balance: $" + account.getBalance() + "\n";
                message += "Item Cost: $" + subtotalCost + "\n";
                message += "Fee: $" + fee + "\n";
                message += "Total Cost: $" + totalCost;
                Dialog.showError("Error", message);
                return;
            }
            Purchase p = new Purchase(item, qty, fee);
            account.addPurchase(p);
            account.withdraw(totalCost);
            String message = "Item purchased successfully.\n";
            //message += "Account Balance: $" + account.getBalance() + "\n";
            message += "Item Cost: $" + subtotalCost + "\n";
            message += "Fee: $" + fee + "\n";
            message += "Total Cost: $" + totalCost;
            Dialog.showMessage("Purchase Summary", message);
            updateAccountDetails();
        }
    }

    @FXML
    private void handleLogoutButtonClicked(ActionEvent event) {
        AccountManager.saveAccount(account);
        sceneManager.showLoginScene();
    }

    private void updateAccountDetails() {
        lblBalance.setText(String.format("$%.2f", account.getBalance()));
        lblLevel.setText(account.getLevel().toUpperCase());
        lblUsername.setText(account.getCustomer().getUsername());

        ObservableList<Purchase> purchases = FXCollections.observableArrayList(account.getPurchases());
        colItem.setCellValueFactory(new PropertyValueFactory<Purchase, String>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<Purchase, Integer>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Purchase, Double>("itemPrice"));
        colFee.setCellValueFactory(new PropertyValueFactory<Purchase, Double>("fee"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Purchase, String>("totalCost"));
        table.setItems(purchases);
    }
}
