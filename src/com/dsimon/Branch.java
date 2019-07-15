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
        int customerIndex = findCustomer(name);
        if (customerIndex == -1) {
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
        int customerIndex = findCustomer(customerName);
        if (customerIndex == -1) {
            System.out.println("Customer does not exist at that branch");
            return false;
        } else {
            boolean wasTransactionSuccessful = customers.get(customerIndex).newTransaction(amount);
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

    private int findCustomer(String name) {
        int customerIndex = -1;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equals(name)) {
                customerIndex = i;
                break;
            }
        }
        return customerIndex;
    }
 }
