package com.dsimon;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter name for new bank:");
        String bankName = scanner.nextLine();

        Bank bank = new Bank(bankName);
        System.out.println(bank.getName() + "has been created!");

        boolean done = false;
        while (!done) {
            System.out.println("\nPick an option:");
            System.out.println(" 1. View branches");
            System.out.println(" 2. Add a branch");
            System.out.println(" 3. Add a customer to a branch");
            System.out.println(" 4. Add a transaction to a customer");
            System.out.println(" 5. View a branch's details");
            System.out.println(" 6. Done");

            if (scanner.hasNextInt()) {
                int selection = scanner.nextInt();
                scanner.nextLine();

                switch (selection) {
                    case 1:
                        viewBranches(bank);
                        break;
                    case 2:
                        addBranch(bank);
                        break;
                    case 3:
                        addCustomer(bank);
                        break;
                    case 4:
                        addTransaction(bank);
                        break;
                    case 5:
                        viewBranchDetails(bank);
                        break;
                    case 6:
                        done = true;
                        break;
                    default:
                        System.out.println("That is not a valid number");
                        break;
                }
            } else {
                System.out.println("That is not a number.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    public static void viewBranches(Bank bank) {
        ArrayList<Branch> branches = bank.getBranches();
        if (branches.size() == 0) {
            System.out.println("There are no branches yet!");
        } else {
            for (int i = 0; i < branches.size(); i++) {
                System.out.println(" * " + branches.get(i).getBranchName());
            }
        }
    }

    public static void addBranch(Bank bank) {
        System.out.println("Enter the name of the new branch:");
        String branchName = scanner.nextLine();
        boolean successfullyAdded = bank.addBranch(branchName);
        if (successfullyAdded) {
            System.out.println("Branch created!");
        } else {
            System.out.println("Branch already exists");
        }
    }

    public static void addCustomer(Bank bank) {
        System.out.println("Enter the name of the branch to add to:");
        String whichBranch = scanner.nextLine();
        System.out.println("Enter the name of the new customer:");
        String customerName = scanner.nextLine();
        System.out.println("Enter your first transaction:");

        while(!scanner.hasNextDouble()) {
            System.out.println("Invalid input, enter a dollar amount:");
            scanner.nextLine();
        }
        double transaction = scanner.nextDouble();
        scanner.nextLine();

        boolean successfullyAdded = bank.addCustomer(whichBranch, customerName, transaction);
        if (successfullyAdded) {
            System.out.println("Customer successfully added.");
        } else {
            System.out.println("Could not create customer.");
        }
    }

    public static void addTransaction(Bank bank) {
        System.out.println("Enter the name of the branch:");
        String branchName = scanner.nextLine();
        System.out.println("Enter the name of the customer at the branch:");
        String customerName = scanner.nextLine();
        System.out.println("Enter their transaction amount:");

        while(!scanner.hasNextDouble()) {
            System.out.println("Please enter a valid dollar amount:");
            scanner.nextLine();
        }
        double transaction = scanner.nextDouble();

        boolean successful = bank.addTransaction(branchName, customerName, transaction);
        if (successful) {
            System.out.println("Transaction added.");
        } else {
            System.out.println("Transaction was not able to be added.");
        }
    }

    public static void viewBranchDetails(Bank bank) {
        System.out.println("Enter the name of the branch:");
        String branchName = scanner.nextLine();
        boolean successful = bank.outputBranchCustomers(branchName);
        if (!successful) {
            System.out.println("Branch doesn't exist.");
        }
    }
}
