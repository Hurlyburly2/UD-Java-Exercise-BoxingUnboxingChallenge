package com.dsimon;

import java.util.ArrayList;

public class Branch {
    private String branchName;
    private ArrayList<Customer> customers;

    public Branch(String branchName) {
        this.branchName = branchName;
        customers = new ArrayList<Customer>();
    }

    public boolean newCustomer(String name, double initialTransaction) {
        Customer foundCustomer = findCustomer(name);
        if (foundCustomer == null) {
            customers.add(new Customer(name, initialTransaction));
            if (initialTransaction < 0) {
                System.out.println("Initial transaction cannot be less than zero, balance set to zero.");
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean makeTransaction(String customerName, double amount) {
        Customer foundCustomer = findCustomer(customerName);
        if (foundCustomer == null) {
            System.out.println("Customer does not exist at that branch");
            return false;
        } else {
            boolean wasTransactionSuccessful = foundCustomer.newTransaction(amount);
            if (!wasTransactionSuccessful) {
                System.out.println("You cannot make a subtraction larger than your balance.");
            }
            return true;
        }
    }

    public String getBranchName() {
        return branchName;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    private Customer findCustomer(String name) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equals(name)) {
                return customers.get(i);
            }
        }
        return null;
    }
 }
