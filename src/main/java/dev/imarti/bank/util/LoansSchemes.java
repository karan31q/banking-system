package dev.imarti.bank.util;

public class LoansSchemes {
    private String loanID;
    private double loanAmount;
    private float loanInterest;
    private float loanDuration;

    public String getLoanID() {
        return loanID;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public float getLoanInterest() {
        return loanInterest;
    }

    public float getLoanDuration() {
        return loanDuration;
    }

    public LoansSchemes(String loanID, double loanAmount, float loanInterest, float loanDuration) {
        this.loanID = loanID;
        this.loanAmount = loanAmount;
        this.loanInterest = loanInterest;
        this.loanDuration = loanDuration;
    }
}
