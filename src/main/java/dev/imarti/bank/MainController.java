package dev.imarti.bank;

import dev.imarti.bank.view.View;
import dev.imarti.bank.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainController {
    // public String alertHeading = "Banking System | Login";

    public TextField username;
    public PasswordField password;

    @FXML
    protected void onLoginButtonClick() {
        // TODO
    }

    @FXML
    protected void onSignUpClick() throws IOException {
        ViewSwitcher.switchView(View.REGISTER);
    }
}
