package dev.imarti.bank.home;

import dev.imarti.bank.account.AccountController;
import dev.imarti.bank.deposits.DepositsController;
import dev.imarti.bank.settings.SettingsController;
import dev.imarti.bank.util.FetchUserDetails;
import dev.imarti.bank.view.View;
import dev.imarti.bank.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class HomeController {
    // private final String alertHeading = "Banking System | Home";
    private String userID;
    private FetchUserDetails userDetails;

    @FXML
    protected Text name;

    @FXML
    protected Text balance;

    public void setUserDetails(String userID) {
        this.userID = userID;
        userDetails = new FetchUserDetails(userID);
        name.setText(userDetails.getName());
        balance.setText(String.valueOf(userDetails.getBalance()));
    }

    @FXML
    protected void onLogoutClick() throws IOException {
        ViewSwitcher.switchView(View.LOGIN);
    }

    @FXML
    protected void onSettingsButtonClick() throws IOException {
        SettingsController settingsController = (SettingsController) ViewSwitcher.switchView(View.SETTINGS);
        settingsController.setUserDetails(userDetails);
    }

    @FXML
    protected void onAccountsButtonClick() throws IOException {
        AccountController accountController = (AccountController) ViewSwitcher.switchView(View.ACCOUNT);
        accountController.setUserDetails(userDetails);
    }

    @FXML
    protected void onDepositsButtonClick() throws IOException {
        DepositsController depositsController = (DepositsController) ViewSwitcher.switchView(View.DEPOSITS);
        depositsController.setUserDetails(userDetails);
    }

    @FXML
    protected void onLoansButtonClick() throws IOException {
        // TODO
    }
}
