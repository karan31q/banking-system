package dev.imarti.bank.util;

import dev.imarti.bank.db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FetchUserDetails {
    public String userID;
    public String name;
    public String email;
    public int balance;
    public String accountID;

    public FetchUserDetails(String userID) {
        this.userID = userID;
        getName();
        getEmail();
        getBalance();
        getAccountID();
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        try (Connection connection = DBConnection.connectDB()) {
            String query = "SELECT full_name FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.name = resultSet.getString("full_name");
                return this.name;
            } else {
                System.out.println(userID + " not found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return name;
    }

    public String getEmail() {
        try (Connection connection = DBConnection.connectDB()) {
            String query = "SELECT email FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.email = resultSet.getString("email");
                return this.email;
            } else {
                System.out.println(userID + " not found");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return email;
    }

    public int getBalance() {
        try (Connection connection = DBConnection.connectDB()) {
            String accountsQuery = "SELECT balance from accounts where user_id = ?";
            PreparedStatement accountsPreparedStatement = connection.prepareStatement(accountsQuery);
            accountsPreparedStatement.setString(1, userID);
            ResultSet accountsResultSet = accountsPreparedStatement.executeQuery();
            if (accountsResultSet.next()) {
                this.balance = accountsResultSet.getInt("balance");
                return this.balance;
            } else {
                System.out.println(userID + " not found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return balance;
    }

    public String getAccountID() {
        try (Connection connection = DBConnection.connectDB()) {
            String accountsQuery = "SELECT account_id from accounts where user_id = ?";
            PreparedStatement accountsPreparedStatement = connection.prepareStatement(accountsQuery);
            accountsPreparedStatement.setString(1, userID);
            ResultSet accountsResultSet = accountsPreparedStatement.executeQuery();
            if (accountsResultSet.next()) {
                this.accountID = accountsResultSet.getString("account_id");
                return this.accountID;
            } else {
                System.out.println(userID + " not found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return accountID;
    }

    public ObservableList<Transactions> getTransactions(String userID) {
        ObservableList<Transactions> transactionsList = FXCollections.observableArrayList();

        try (Connection connection = DBConnection.connectDB()) {
            String query = "SELECT * FROM transactions WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String transactionID = resultSet.getString("transaction_id");
                Integer transactionAmount = resultSet.getInt("amount");
                String transactionType = resultSet.getString("type");
                String transactionDate = resultSet.getString("transaction_date");
                Transactions transactions = new Transactions(transactionID, transactionAmount, transactionType, transactionDate);
                transactionsList.add(transactions);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return transactionsList;
    }
}
