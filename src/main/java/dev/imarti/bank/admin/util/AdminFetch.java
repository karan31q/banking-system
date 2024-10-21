package dev.imarti.bank.admin.util;

import dev.imarti.bank.db.DBConnection;
import dev.imarti.bank.util.FetchUserDetails;
import dev.imarti.bank.util.Transactions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminFetch extends FetchUserDetails {
    public AdminFetch(String userID) {
        super(userID);
    }

    public ObservableList<AdminTransactions> getAllTransactions() {
        ObservableList<AdminTransactions> allTransactionsList = FXCollections.observableArrayList();

        try (Connection connection = DBConnection.connectDB()) {
            String query = "SELECT * FROM transactions";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String transactionID = resultSet.getString("transaction_id");
                Integer transactionAmount = resultSet.getInt("amount");
                String transactionType = resultSet.getString("type");
                String transactionDate = resultSet.getString("transaction_date");
                String transactionUserID = resultSet.getString("user_id");
                String transactionAccountID = resultSet.getString("account_id");
                AdminTransactions transactions = new AdminTransactions(transactionID, transactionAmount, transactionType, transactionDate, transactionUserID, transactionAccountID);
                allTransactionsList.add(transactions);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allTransactionsList;
    }
}
