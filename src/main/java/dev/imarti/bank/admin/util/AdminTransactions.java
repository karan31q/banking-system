package dev.imarti.bank.admin.util;

import dev.imarti.bank.util.Transactions;

public class AdminTransactions extends Transactions {
    private String transactionUserID;
    private String transactionAccountID;

    public String getTransactionUserID() {
        return transactionUserID;
    }

    public String getTransactionAccountID() {
        return transactionAccountID;
    }

    public AdminTransactions(String transactionId, Integer transactionAmount, String transactionType, String transactionDate, String transactionUserID, String transactionAccountID) {
        super(transactionId, transactionAmount, transactionType, transactionDate);
        this.transactionUserID = transactionUserID;
        this.transactionAccountID = transactionAccountID;
    }
}
