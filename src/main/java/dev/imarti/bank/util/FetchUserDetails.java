package dev.imarti.bank.util;

import dev.imarti.bank.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FetchUserDetails {
    public String userID;
    public String name;
    public String email;
    public int balance;

    public FetchUserDetails(String userID) {
        this.userID = userID;
        try (Connection connection = DBConnection.connectDB()) {
            String query = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.name = resultSet.getString("full_name");
                this.email = resultSet.getString("email");
                try {
                    String accountsQuery = "SELECT * from accounts where user_id = ?";
                    PreparedStatement accountsPreparedStatement = connection.prepareStatement(accountsQuery);
                    accountsPreparedStatement.setString(1, userID);
                    ResultSet accountsResultSet = accountsPreparedStatement.executeQuery();
                    if (accountsResultSet.next()) {
                        this.balance = accountsResultSet.getInt("balance");
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println(userID + " not found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getBalance() {
        return balance;
    }
}
