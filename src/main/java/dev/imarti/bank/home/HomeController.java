package dev.imarti.bank.home;

import dev.imarti.bank.view.View;
import dev.imarti.bank.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class HomeController {
    // private final String alertHeading = "Banking System | Home";
    private String userID;

    @FXML
    protected Text name;

    @FXML
    protected Text balance;

    public void setUserDetails(String userID) {
        this.userID = userID;
    }

    @FXML
    protected void onLogoutClick() throws IOException {
        ViewSwitcher.switchView(View.LOGIN);
    }

    @FXML
    protected void onSettingsButtonClick() throws IOException {
        // TODO
    }

    @FXML
    protected void onAccountsButtonClick() throws IOException {
        // TODO
    }

    @FXML
    protected void onLoansButtonClick() throws IOException {
        // TODO
    }

    @FXML
    protected void onDepositsButtonClick() throws IOException {
        // TODO
    }

    @FXML
    protected void onStatementButtonClick() throws IOException {
        // TODO
    }
}
