package dev.imarti.bank.admin;

import dev.imarti.bank.admin.transactions.AdminTransactionsController;
import dev.imarti.bank.admin.util.AdminFetch;
import dev.imarti.bank.settings.SettingsController;
import dev.imarti.bank.util.FetchUserDetails;
import dev.imarti.bank.view.View;
import dev.imarti.bank.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class AdminController {
    private final String alertHeading = "Banking System | Admin";
    private String userID;
    private FetchUserDetails userDetails;
    private AdminFetch adminFetch;

    @FXML
    protected Text name;

    public void setUserDetails(String userID) {
        this.userID = userID;
        userDetails = new FetchUserDetails(userID);
        adminFetch = new AdminFetch(userID);
        name.setText(adminFetch.getName());
    }

    @FXML
    protected void onSettingsButtonClick() throws IOException {
        SettingsController settingsController = (SettingsController) ViewSwitcher.switchView(View.SETTINGS);
        settingsController.setUserDetails(userDetails);
    }

    @FXML
    protected void onLogoutClick() throws IOException {
        ViewSwitcher.switchView(View.LOGIN);
    }

    @FXML
    protected void onTransactionsButtonClick() throws IOException {
        AdminTransactionsController adminTransactionsController = (AdminTransactionsController) ViewSwitcher.switchView(View.ADMIN_TRANSACTIONS);
        adminTransactionsController.setUserDetails(adminFetch);
    }
}
