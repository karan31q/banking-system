package dev.imarti.bank.util;

import dev.imarti.bank.db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Calendar;

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

    public ObservableList<Deposits> getDeposits(String userID) {
        ObservableList<Deposits> depositsList = FXCollections.observableArrayList();

        try (Connection connection = DBConnection.connectDB()) {
            String query = "SELECT deposit_id, deposit_date, amount, interest, due_date FROM deposits where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String depositID = resultSet.getString("deposit_id");
                Date depositDate = resultSet.getDate("deposit_date");
                double depositAmount = resultSet.getDouble("amount");
                float depositInterest = resultSet.getFloat("interest");
                Date depositDueDate = resultSet.getDate("due_date");
                Deposits deposits = new Deposits(depositID, depositDate, depositAmount, depositInterest, depositDueDate);
                depositsList.add(deposits);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return depositsList;
    }

    public ObservableList<Loans> getLoans(String userID) {
        ObservableList<Loans> loansList = FXCollections.observableArrayList();

        try (Connection connection = DBConnection.connectDB()) {
            String query = "SELECT loan_id, loan_date, amount, interest, due_date FROM loans where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String loanID = resultSet.getString("loan_id");
                Date loanDate = resultSet.getDate("loan_date");
                double loanAmount = resultSet.getDouble("amount");
                float loanInterest = resultSet.getFloat("interest");
                Date loanDueDate = resultSet.getDate("due_date");
                Loans loans = new Loans(loanID, loanDate, loanAmount, loanInterest, loanDueDate);
                loansList.add(loans);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return loansList;
    }

    public String makeDeposit(String userID, String depositID) {
        try (Connection connection = DBConnection.connectDB()) {
            // first fetch deposit scheme info
            String depositInfo = "SELECT * FROM deposits_list WHERE deposit_id = ?";
            PreparedStatement preparedStatementScheme = connection.prepareStatement(depositInfo);
            preparedStatementScheme.setString(1, depositID);
            ResultSet resultSetScheme = preparedStatementScheme.executeQuery();
            if (resultSetScheme.next()) {
                double depositAmount = resultSetScheme.getDouble("amount");
                float depositInterest = resultSetScheme.getFloat("interest");
                int depositMaturity = resultSetScheme.getInt("maturity_months");

                Calendar calendar = Calendar.getInstance();
                Date depositDate = new Date(calendar.getTimeInMillis());
                calendar.setTime(depositDate);
                calendar.add(Calendar.MONTH, depositMaturity);
                Date dueDate = new Date(calendar.getTimeInMillis());

                String accountID = getAccountID();

                String query = "INSERT INTO deposits (deposit_date, user_id, account_id, amount, interest, due_date) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setDate(1, depositDate);
                preparedStatement.setString(2, userID);
                preparedStatement.setString(3, accountID);
                preparedStatement.setDouble(4, depositAmount);
                preparedStatement.setFloat(5, depositInterest);
                preparedStatement.setDate(6, dueDate);
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    int balance = getBalance();
                    double updatedBalance = balance - depositAmount;

                    String updateBalance = "UPDATE accounts SET balance = ? WHERE account_id = ?";
                    PreparedStatement preparedStatementBalance = connection.prepareStatement(updateBalance);
                    preparedStatementBalance.setDouble(1, updatedBalance);
                    preparedStatementBalance.setString(2, accountID);
                    int resultBalance = preparedStatementBalance.executeUpdate();
                    if (resultBalance > 0) {
                        String updateTransaction = "INSERT INTO transactions (user_id, account_id, amount, type) VALUES (?, ?, ?, ?)";
                        PreparedStatement preparedStatementTransaction = connection.prepareStatement(updateTransaction);
                        preparedStatementTransaction.setString(1, userID);
                        preparedStatementTransaction.setString(2, accountID);
                        preparedStatementTransaction.setDouble(3, depositAmount);
                        preparedStatementTransaction.setString(4, "DEBIT");
                        int transactionResult = preparedStatementTransaction.executeUpdate();
                        if (transactionResult > 0) {
                            return "deposit successful";
                        } else {
                            return "deposit not successful";
                        }
                    } else {
                        return "deposit not successful";
                    }
                } else {
                    return "deposit not successful";
                }
            } else {
                return "deposit not successful";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "some error occurred";
        }
    }

    public String takeLoan(String userID, String loanID) {
        try (Connection connection = DBConnection.connectDB()) {
            // first fetch deposit scheme info
            String loanInfo = "SELECT * FROM loans_list WHERE loan_id = ?";
            PreparedStatement preparedStatementScheme = connection.prepareStatement(loanInfo);
            preparedStatementScheme.setString(1, loanID);
            ResultSet resultSetScheme = preparedStatementScheme.executeQuery();
            if (resultSetScheme.next()) {
                double loanAmount = resultSetScheme.getDouble("amount");
                float loanInterest = resultSetScheme.getFloat("interest");
                int loanDuration = resultSetScheme.getInt("duration");

                Calendar calendar = Calendar.getInstance();
                Date loanDate = new Date(calendar.getTimeInMillis());
                calendar.setTime(loanDate);
                calendar.add(Calendar.MONTH, loanDuration);
                Date dueDate = new Date(calendar.getTimeInMillis());

                String accountID = getAccountID();

                String query = "INSERT INTO loans (loan_date, user_id, account_id, amount, interest, due_date) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setDate(1, loanDate);
                preparedStatement.setString(2, userID);
                preparedStatement.setString(3, accountID);
                preparedStatement.setDouble(4, loanAmount);
                preparedStatement.setFloat(5, loanInterest);
                preparedStatement.setDate(6, dueDate);
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    int balance = getBalance();
                    double updatedBalance = balance + loanAmount;

                    String updateBalance = "UPDATE accounts SET balance = ? WHERE account_id = ?";
                    PreparedStatement preparedStatementBalance = connection.prepareStatement(updateBalance);
                    preparedStatementBalance.setDouble(1, updatedBalance);
                    preparedStatementBalance.setString(2, accountID);
                    int resultBalance = preparedStatementBalance.executeUpdate();
                    if (resultBalance > 0) {
                        String updateTransaction = "INSERT INTO transactions (user_id, account_id, amount, type) VALUES (?, ?, ?, ?)";
                        PreparedStatement preparedStatementTransaction = connection.prepareStatement(updateTransaction);
                        preparedStatementTransaction.setString(1, userID);
                        preparedStatementTransaction.setString(2, accountID);
                        preparedStatementTransaction.setDouble(3, loanAmount);
                        preparedStatementTransaction.setString(4, "CREDIT");
                        int transactionResult = preparedStatementTransaction.executeUpdate();
                        if (transactionResult > 0) {
                            return "loan successful";
                        } else {
                            return "loan not successful";
                        }
                    } else {
                        return "loan not successful";
                    }
                } else {
                    return "loan not successful";
                }
            } else {
                return "loan not successful";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "some error occurred";
        }
    }

    public ObservableList<DepositsSchemes> getDepositsSchemes() {
        ObservableList<DepositsSchemes> depositsSchemesList = FXCollections.observableArrayList();

        try (Connection connection = DBConnection.connectDB()) {
            String query = "SELECT * FROM deposits_list";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String depositID = resultSet.getString("deposit_id");
                double depositAmount = resultSet.getDouble("amount");
                float depositInterest = resultSet.getFloat("interest");
                float depositMaturity = resultSet.getFloat("maturity_months");
                depositsSchemesList.add(new DepositsSchemes(depositID, depositAmount, depositInterest, depositMaturity));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return depositsSchemesList;
    }


    public ObservableList<LoansSchemes> getLoanSchemes() {
        ObservableList<LoansSchemes> loansSchemesList = FXCollections.observableArrayList();

        try (Connection connection = DBConnection.connectDB()) {
            String query = "SELECT * FROM loans_list";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String loanID = resultSet.getString("loan_id");
                double loanAmount = resultSet.getDouble("amount");
                float loanInterest = resultSet.getFloat("interest");
                float loanDuration = resultSet.getFloat("duration");
                loansSchemesList.add(new LoansSchemes(loanID, loanAmount, loanInterest, loanDuration));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return loansSchemesList;
    }
}
