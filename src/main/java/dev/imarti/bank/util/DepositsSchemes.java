package dev.imarti.bank.util;

public class DepositsSchemes {
    private String depositID;
    private double depositAmount;
    private float depositInterest;
    private float depositMaturity;

    public String getDepositID() {
        return depositID;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public float getDepositInterest() {
        return depositInterest;
    }

    public float getDepositMaturity() {
        return depositMaturity;
    }

    public DepositsSchemes(String depositID, double depositAmount, float depositInterest, float depositMaturity) {
        this.depositID = depositID;
        this.depositAmount = depositAmount;
        this.depositInterest = depositInterest;
        this.depositMaturity = depositMaturity;
    }
}
