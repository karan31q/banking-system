package dev.imarti.bank.util;

import java.util.Date;

public class Deposits {
    private String depositID;
    private Date depositDate;
    private double depositAmount;
    private float depositInterest;
    private Date depositDueDate;

    public String getDepositID() {
        return depositID;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public float getDepositInterest() {
        return depositInterest;
    }

    public Date getDepositDueDate() {
        return depositDueDate;
    }

    public Deposits(String depositID, Date depositDate, double depositAmount, float depositInterest, Date depositDueDate) {
        this.depositID = depositID;
        this.depositDate = depositDate;
        this.depositAmount = depositAmount;
        this.depositInterest = depositInterest;
        this.depositDueDate = depositDueDate;
    }
}
