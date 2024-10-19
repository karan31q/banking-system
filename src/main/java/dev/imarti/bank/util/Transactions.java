package dev.imarti.bank.util;

public class Transactions {
    private String transactionId;
    private Integer transactionAmount;
    private String transactionType;
    private String transactionDate;

    public String getTransactionID() {
        return transactionId;
    }

    public Integer getTransactionAmount() {
        return transactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public Transactions(String transactionId, Integer transactionAmount, String transactionType, String transactionDate) {
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }
}
