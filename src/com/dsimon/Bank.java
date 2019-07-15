package com.dsimon;

import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        branches = new ArrayList<Branch>();
    }

    public boolean addBranch(String branchName) {
        Branch foundBranch = findBranch(branchName);
        if (foundBranch == null) {
            branches.add(new Branch(branchName));
            return true;
        } else {
            return false;
        }
    }

    public boolean addCustomer(String branchName, String customerName, double initialTransaction) {
        Branch foundBranch = findBranch(branchName);
        if (foundBranch == null) {
            System.out.println("Branch does not exist.");
            return false;
        } else {
            boolean successful = foundBranch.newCustomer(customerName, initialTransaction);
            if (!successful) {
                System.out.println("Customer already exists at that branch.");
                return false;
            }
            return true;
        }
    }

    public boolean addTransaction(String branchName, String customerName, double transaction) {
        Branch foundBranch = findBranch(branchName);
        if (foundBranch == null) {
            System.out.println("Branch not found.");
            return false;
        }
        boolean successful = foundBranch.makeTransaction(customerName, transaction);
        if (successful) {
            return true;
        } else {
            return false;
        }
    }

    public boolean outputBranchCustomers(String branchName) {
        Branch foundBranch = findBranch(branchName);
        if (foundBranch == null) {
            return false;
        } else {
            ArrayList<Customer> customers = foundBranch.getCustomers();
            System.out.println("Customers at " + foundBranch.getBranchName() + " branch:");
            if (customers.size() == 0) {
                System.out.println(" * No customers have been added yet for this branch.");
            } else {
                for (int i = 0; i < customers.size(); i++) {
                    System.out.println(" * " + customers.get(i).getName() + "'s transactions: " + customers.get(i).getTransactions().toString());
                }
            }
            return true;
        }
    }

    private Branch findBranch(String branchName) {
        for (int i = 0; i < branches.size(); i++) {
            if (branches.get(i).getBranchName().equals(branchName)) {
                return branches.get(i);
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }
}
