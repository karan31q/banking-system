package dev.imarti.bank.register;

import dev.imarti.bank.view.View;
import dev.imarti.bank.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterController {
    // public String alertHeading = "Banking System | Register";

    public TextField username;
    public PasswordField password;
    public TextField name;
    public TextField email;

    @FXML
    protected void onSignUpButtonClick() {
        // TODO
    }

    @FXML
    protected void onLoginClick() throws IOException {
        ViewSwitcher.switchView(View.LOGIN);
    }

}
