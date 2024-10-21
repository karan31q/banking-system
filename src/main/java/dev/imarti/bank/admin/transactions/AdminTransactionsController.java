package dev.imarti.bank.admin.transactions;

import dev.imarti.bank.admin.AdminController;
import dev.imarti.bank.admin.util.AdminFetch;
import dev.imarti.bank.admin.util.AdminTransactions;
import dev.imarti.bank.view.View;
import dev.imarti.bank.view.ViewSwitcher;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;

public class AdminTransactionsController {
    public String alertHeading = "Banking System | Account";
    private AdminFetch adminFetch;
    private ObservableList<AdminTransactions> allTransactionsList;

    @FXML
    protected Text name;

    @FXML
    protected TableColumn<AdminTransactions, String> transactionID;

    @FXML
    protected TableColumn<AdminTransactions, Integer> transactionAmount;

    @FXML
    protected TableColumn<AdminTransactions, String> transactionType;

    @FXML
    protected TableColumn<AdminTransactions, String> transactionDate;

    @FXML
    protected TableColumn<AdminTransactions, String> transactionUserID;

    @FXML
    protected TableColumn<AdminTransactions, String> transactionAccountID;

    @FXML
    protected TableView<AdminTransactions> transactions;

    public void setUserDetails(AdminFetch adminFetch) {
        this.adminFetch = adminFetch;
        name.setText(adminFetch.getName());
        allTransactionsList = adminFetch.getAllTransactions();
        transactions.setItems(allTransactionsList);
    }

    @FXML
    protected void onLogoutClick() throws IOException {
        ViewSwitcher.switchView(View.LOGIN);
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        AdminController adminController = (AdminController) ViewSwitcher.switchView(View.ADMIN);
        adminController.setUserDetails(adminFetch.getUserID());
    }

    @FXML
    protected void initialize() {
        transactionID.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        transactionAmount.setCellValueFactory(new PropertyValueFactory<>("transactionAmount"));
        transactionType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
        transactionUserID.setCellValueFactory(new PropertyValueFactory<>("transactionUserID"));
        transactionAccountID.setCellValueFactory(new PropertyValueFactory<>("transactionAccountID"));
        transactions.setItems(allTransactionsList);
    }

}
