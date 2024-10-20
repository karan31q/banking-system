package dev.imarti.bank.util;

import java.util.Date;

public class Loans {
    private String loanID;
    private Date loanDate;
    private double loanAmount;
    private float loanInterest;
    private Date loanDueDate;

    public String getLoanID() {
        return loanID;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public float getLoanInterest() {
        return loanInterest;
    }

    public Date getLoanDueDate() {
        return loanDueDate;
    }

    public Loans(String loanID, Date loanDate, double loanAmount, float loanInterest, Date loanDueDate) {
        this.loanID = loanID;
        this.loanDate = loanDate;
        this.loanAmount = loanAmount;
        this.loanInterest = loanInterest;
        this.loanDueDate = loanDueDate;
    }
}
