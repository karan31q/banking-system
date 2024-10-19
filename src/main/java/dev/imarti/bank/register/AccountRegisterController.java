package dev.imarti.bank.register;

import dev.imarti.bank.db.DBConnection;
import dev.imarti.bank.home.HomeController;
import dev.imarti.bank.util.FetchUserDetails;
import dev.imarti.bank.view.View;
import dev.imarti.bank.view.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;
import java.util.function.UnaryOperator;

public class AccountRegisterController {
    public String alertHeading = "Banking System | Account Registration";
    public String userID;
    private FetchUserDetails userDetails;

    public void setUserDetails(String userID) {
        this.userID = userID;
        userDetails = new FetchUserDetails(userID);
    }

    @FXML
    protected TextField initialDeposit;

    // https://coderanch.com/t/723329/java/accept-characters-integers
    UnaryOperator<TextFormatter.Change> filter = change -> {
        String text = change.getText();
        if (text.matches("\\d?")) {
            return change;
        }
        return null;
    };

    @FXML
    protected void initialize() {
        initialDeposit.setTextFormatter(new TextFormatter<String>(filter)); // only allow integer in deposit text field
    }

    @FXML
    protected void onSetupAccountButtonClick() throws IOException {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(alertHeading);
        if (initialDeposit.getText().isEmpty()) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText(alertHeading);
            alert.setContentText("Please enter a deposit amount");
            alert.show();
            return;
        }
        try (Connection connection = DBConnection.connectDB()) {
            String query;
            PreparedStatement preparedStatement;
            if (connection == null) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText(alertHeading);
                alert.setContentText("Connection to the database failed, Please try again later.");
                alert.show();
                return;
            }
            query = "INSERT INTO accounts (user_id, balance) values (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userID);
            preparedStatement.setDouble(2, Double.parseDouble(initialDeposit.getText()));
            try {
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    query = "INSERT INTO transactions (user_id, account_id, amount, type) VALUES (?, ?, ?, ?)";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, userDetails.getUserID());
                    preparedStatement.setString(2, userDetails.getAccountID());
                    preparedStatement.setDouble(3, Double.parseDouble(initialDeposit.getText()));
                    preparedStatement.setString(4, "CREDIT");
                    int transactionResult = preparedStatement.executeUpdate();
                    if (transactionResult > 0) {
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(alertHeading);
                        alert.setContentText("Account successfully registered, Redirecting to home page");
                        alert.showAndWait();
                        HomeController homeController = (HomeController) ViewSwitcher.switchView(View.HOME);
                        homeController.setUserDetails(userID);
                    }
                } else {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setHeaderText(alertHeading);
                    alert.setContentText("Failed to register account, Please try again.");
                    alert.show();
                }
            } catch (SQLException e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText(alertHeading);
                alert.setContentText("An error occured, Please try again.\n" + e.getMessage());
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
}
