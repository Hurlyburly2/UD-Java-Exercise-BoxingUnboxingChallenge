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
        int doesBranchExist = findBranch(branchName);
        if (doesBranchExist == -1) {
            branches.add(new Branch(branchName));
            return true;
        } else {
            return false;
        }
    }

    public boolean addCustomer(String branchName, String customerName, double initialTransaction) {
        int branchIndex = findBranch(branchName);
        if (branchIndex == -1) {
            System.out.println("Branch does not exist.");
            return false;
        } else {
            boolean successful = branches.get(branchIndex).newCustomer(customerName, initialTransaction);
            if (!successful) {
                System.out.println("Customer already exists at that branch.");
                return false;
            }
            return true;
        }
    }

    public boolean addTransaction(String branchName, String customerName, double transaction) {
        int branchIndex = findBranch(branchName);
        if (branchIndex == -1) {
            System.out.println("Branch not found.");
            return false;
        }
        boolean successful = branches.get(branchIndex).makeTransaction(customerName, transaction);
        return false;
    }

    public boolean outputBranchCustomers(String branchName) {
        int branchIndex = findBranch(branchName);
        if (branchIndex == -1) {
            return false;
        } else {
            ArrayList<Customer> customers = branches.get(branchIndex).getCustomers();
            System.out.println("Customers at " + branches.get(branchIndex).getBranchName() + " branch:");
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

    public int findBranch(String branchName) {
        int branchIndex = -1;
        for (int i = 0; i < branches.size(); i++) {
            if (branches.get(i).getBranchName().equals(branchName)) {
                branchIndex = i;
                break;
            }
        }
        return branchIndex;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }
}
