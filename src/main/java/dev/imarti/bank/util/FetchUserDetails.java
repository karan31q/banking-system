package dev.imarti.bank.util;

import dev.imarti.bank.db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class FetchUserDetails {
    public String userID;
    public String username;
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

    public String setPassword(String password) {
        try (Connection connection = DBConnection.connectDB()) {
            String query = "UPDATE users SET password = ? WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, userID);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                getUsername();
                return "updated";
            } else {
                System.out.println(userID + " not found");
                return "no user";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "some error";
        }
    }

    public String getUsername() {
        try (Connection connection = DBConnection.connectDB()) {
            String query = "SELECT username FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.username = resultSet.getString("username");
                return this.username;
            } else {
                System.out.println(userID + " not found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return username;
    }

    public String setUsername(String username) {
        try (Connection connection = DBConnection.connectDB()) {
            String query = "UPDATE users SET username = ? WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, userID);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                getUsername();
                return "updated";
            } else {
                System.out.println(userID + " not found");
                return "no user";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            return "duplicate username";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "some error";
        }
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

    public String setName(String name) {
        try (Connection connection = DBConnection.connectDB()) {
            String query = "UPDATE users SET full_name = ? WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, userID);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                getUsername();
                return "updated";
            } else {
                System.out.println(userID + " not found");
                return "no user";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "some error";
        }
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

    public String setEmail(String email) {
        try (Connection connection = DBConnection.connectDB()) {
            String query = "UPDATE users SET email = ? WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, userID);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                getUsername();
                return "updated";
            } else {
                System.out.println(userID + " not found");
                return "no user";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            return "duplicate email";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "some error";
        }
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
