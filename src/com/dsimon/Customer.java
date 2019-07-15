package com.dsimon;

import java.util.ArrayList;

public class Customer {
    private String name;
    private ArrayList<Double> transactions;
    private double balance;

    public Customer (String name, double initialTransaction){
        transactions = new ArrayList<Double>();
        balance = 0.0;

        if (checkTransactionValidity(initialTransaction)) {
            updateTransactions(initialTransaction);
        } else {
            transactions.add(0.0);
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }

    public boolean newTransaction(double transactionAmount) {
        if (checkTransactionValidity(transactionAmount)) {
            updateTransactions(transactionAmount);
            return true;
        } else {
            return false;
        }
    }

    private boolean checkTransactionValidity(double transactionAmount) {
        if (balance + transactionAmount >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private void updateTransactions(double transactionAmount) {
        transactions.add(transactionAmount);
        balance += transactionAmount;
    }
}
