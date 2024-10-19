package dev.imarti.bank.account;

import dev.imarti.bank.home.HomeController;
import dev.imarti.bank.util.FetchUserDetails;
import dev.imarti.bank.util.Transactions;
import dev.imarti.bank.view.View;
import dev.imarti.bank.view.ViewSwitcher;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;

public class AccountController{
    public String alertHeading = "Banking System | Account";
    private FetchUserDetails userDetails;
    private ObservableList<Transactions> transactionsList;

    @FXML
    protected Text name;

    @FXML
    protected Text balance;

    @FXML
    protected Text accountID;

    @FXML
    protected TableColumn<Transactions, String> transactionID;

    @FXML
    protected TableColumn<Transactions, Integer> transactionAmount;

    @FXML
    protected TableColumn<Transactions, String> transactionType;

    @FXML
    protected TableColumn<Transactions, String> transactionDate;

    @FXML
    protected TableView<Transactions> transactions;

    public void setUserDetails(FetchUserDetails userDetails) {
        this.userDetails = userDetails;
        name.setText(userDetails.getName());
        balance.setText(String.valueOf(userDetails.getBalance()));
        accountID.setText("#" + userDetails.getAccountID());
        transactionsList = userDetails.getTransactions(userDetails.getUserID());
        transactions.setItems(transactionsList);
    }

    @FXML
    protected void onLogoutClick() throws IOException {
        ViewSwitcher.switchView(View.LOGIN);
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        HomeController homeController = (HomeController) ViewSwitcher.switchView(View.HOME);
        homeController.setUserDetails(userDetails.getUserID());
    }

    @FXML
    protected void initialize() {
        transactionID.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        transactionAmount.setCellValueFactory(new PropertyValueFactory<>("transactionAmount"));
        transactionType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        transactionDate.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
        transactions.setItems(transactionsList);
    }

    @FXML
    protected void onWithdrawalButtonClick() {
        // TODO
    }

    @FXML
    protected void onDepositButtonClick() {
        // TODO
    }
}
