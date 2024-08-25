package dev.imarti.bank.register;

import dev.imarti.bank.db.DBConnection;
import dev.imarti.bank.view.View;
import dev.imarti.bank.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class RegisterController {
    public String alertHeading = "Banking System | Register";

    public TextField username;
    public PasswordField password;
    public TextField name;
    public TextField email;

    @FXML
    protected void onSignUpButtonClick() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(alertHeading);
        if (username.getText().isEmpty() || password.getText().isEmpty() || name.getText().isEmpty() || email.getText().isEmpty()) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText(alertHeading);
            alert.setContentText("Please enter all the details.");
            alert.show();
            return;
        }
        try (Connection connection = DBConnection.connectDB()) {
            if (connection == null) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText(alertHeading);
                alert.setContentText("Connection to the database failed, Please try again later.");
                alert.show();
                return;
            }
            String query = "INSERT INTO users (username, password, full_name, email) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username.getText());
            preparedStatement.setString(2, password.getText());
            preparedStatement.setString(3, name.getText());
            preparedStatement.setString(4, email.getText());
            try {
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(alertHeading);
                    alert.setContentText("Successfully registered, Please login now.");
                    alert.showAndWait();
                    ViewSwitcher.switchView(View.LOGIN);
                } else {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setHeaderText(alertHeading);
                    alert.setContentText("Failed to register, Please try again.");
                    alert.show();
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText(alertHeading);
                alert.setContentText("A user with similar details already exists.");
                alert.show();
            }
        } catch (SQLTimeoutException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText(alertHeading);
            alert.setContentText("Connection to database timed out.");
            alert.show();
        } catch (SQLException | IOException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText(alertHeading);
            alert.setContentText("An error occurred, Please try again.\n" + e.getMessage());
            alert.show();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void onLoginClick() throws IOException {
        ViewSwitcher.switchView(View.LOGIN);
    }
}
