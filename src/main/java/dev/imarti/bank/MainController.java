package dev.imarti.bank;

import dev.imarti.bank.db.DBConnection;
import dev.imarti.bank.view.View;
import dev.imarti.bank.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class MainController {
    public String alertHeading = "Banking System | Login";

    public TextField username;
    public PasswordField password;

    @FXML
    protected void onLoginButtonClick() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(alertHeading);
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText(alertHeading);
            alert.setContentText("Please enter both username and password.");
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
            String query = "SELECT * FROM users WHERE username = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username.getText());
            preparedStatement.setString(2, password.getText());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText(alertHeading);
                alert.setContentText("Login successful, redirecting to Home Page.");
                alert.showAndWait();
                // TODO(Switch to homepage)
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText(alertHeading);
                alert.setContentText("Login Failed, Please check your username and password.");
                alert.show();
            }
        } catch (SQLTimeoutException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText(alertHeading);
            alert.setContentText("Connection to database timed out.");
            alert.show();
        } catch (SQLException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText(alertHeading);
            alert.setContentText("An error occurred, Please try again.\n" + e.getMessage());
            alert.show();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void onSignUpClick() throws IOException {
        ViewSwitcher.switchView(View.REGISTER);
    }
}
