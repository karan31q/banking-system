package dev.imarti.bank.settings;

import dev.imarti.bank.home.HomeController;
import dev.imarti.bank.util.FetchUserDetails;
import dev.imarti.bank.view.View;
import dev.imarti.bank.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Optional;

public class SettingsController {
    private final String alertHeading = "Banking System | Settings";
    private FetchUserDetails userDetails;

    @FXML
    protected Text name;

    @FXML
    protected Text email;

    @FXML
    protected Text username;

    public void setUserDetails(FetchUserDetails userDetails) {
        this.userDetails = userDetails;
        name.setText(userDetails.getName());
        email.setText(userDetails.getEmail());
        username.setText(userDetails.getUsername());
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        HomeController homeController = (HomeController) ViewSwitcher.switchView(View.HOME);
        homeController.setUserDetails(userDetails.getUserID());
    }

    @FXML
    protected void onNameUpdateButtonClick() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(alertHeading);
        dialog.setHeaderText("Update name");
        dialog.setContentText("Enter updated name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            String updateResult = userDetails.setName(name);
            if ("updated".equals(updateResult)) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle(alertHeading);
                successAlert.setHeaderText("Name Updated");
                successAlert.setContentText("Your name has been successfully updated to: " + name);
                successAlert.showAndWait();
                setUserDetails(userDetails);
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle(alertHeading);
                errorAlert.setHeaderText("Update Failed");
                errorAlert.setContentText("There was an error updating your name, Please try again.");
                errorAlert.showAndWait();
            }
        });
    }

    @FXML
    protected void onEmailUpdateButtonClick() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(alertHeading);
        dialog.setHeaderText("Update email");
        dialog.setContentText("Enter new email:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(email -> {
            String updateResult = userDetails.setEmail(email);
            if ("updated".equals(updateResult)) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle(alertHeading);
                successAlert.setHeaderText("Email Updated");
                successAlert.setContentText("Email has been successfully updated to: " + email);
                successAlert.showAndWait();
                setUserDetails(userDetails);
            } else if ("duplicate email".equals(updateResult)) {
                Alert duplicateAlert = new Alert(Alert.AlertType.ERROR);
                duplicateAlert.setTitle(alertHeading);
                duplicateAlert.setHeaderText("Duplicate email address");
                duplicateAlert.setContentText("Email already in use, Please choose a different email address.");
                duplicateAlert.showAndWait();
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle(alertHeading);
                errorAlert.setHeaderText("Update Failed");
                errorAlert.setContentText("There was an error updating your email address, Please try again.");
                errorAlert.showAndWait();
            }
        });
    }

    @FXML
    protected void onUsernameUpdateButtonClick() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(alertHeading);
        dialog.setHeaderText("Update username");
        dialog.setContentText("Enter new username:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(username -> {
            String updateResult = userDetails.setUsername(username);
            if ("updated".equals(updateResult)) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle(alertHeading);
                successAlert.setHeaderText("Username Updated");
                successAlert.setContentText("Username has been successfully updated to: " + username);
                successAlert.showAndWait();
                setUserDetails(userDetails);
            } else if ("duplicate username".equals(updateResult)) {
                Alert duplicateAlert = new Alert(Alert.AlertType.ERROR);
                duplicateAlert.setTitle(alertHeading);
                duplicateAlert.setHeaderText("Duplicate Username");
                duplicateAlert.setContentText("Username already taken, please enter a different username.");
                duplicateAlert.showAndWait();
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle(alertHeading);
                errorAlert.setHeaderText("Update Failed");
                errorAlert.setContentText("There was an error updating your username, Please try again.");
                errorAlert.showAndWait();
            }
        });
    }

    @FXML
    protected void onPasswordUpdateButtonClick() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(alertHeading);
        dialog.setHeaderText("Update password");
        dialog.setContentText("Enter updated password:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(password -> {
            String updateResult = userDetails.setPassword(password);
            if ("updated".equals(updateResult)) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle(alertHeading);
                successAlert.setHeaderText("Name Updated");
                successAlert.setContentText("Your password has been successfully updated.");
                successAlert.showAndWait();
                setUserDetails(userDetails);
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle(alertHeading);
                errorAlert.setHeaderText("Update Failed");
                errorAlert.setContentText("There was an error updating your password, Please try again.");
                errorAlert.showAndWait();
            }
        });
    }
}
