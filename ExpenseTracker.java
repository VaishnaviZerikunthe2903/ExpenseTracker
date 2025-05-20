package com.main;

import java.time.LocalDate;
import java.util.Scanner;

import com.entity.ExpenseTrackerEntity;
import com.services.ExpenseTrackerManager;

public class ExpenseTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTrackerManager manager = new ExpenseTrackerManager();

        while (true) {
            System.out.println("\n--- Expense Tracker ---");
            System.out.println("1. Add Transaction");
            System.out.println("2. Load Transactions from File");
            System.out.println("3. Show Monthly Summary");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter type (Income/Expense): ");
                    String type = scanner.nextLine();

                    String category = "";
                    if (type.equalsIgnoreCase("Income")) {
                        System.out.print("Enter category (Salary/Business): ");
                        category = scanner.nextLine();
                    } else {
                        System.out.print("Enter category (Food/Rent/Travel): ");
                        category = scanner.nextLine();
                    }

                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    ExpenseTrackerEntity tx = new ExpenseTrackerEntity(LocalDate.now(), type, category, amount);
                    manager.addTransaction(tx);
                    System.out.println("Transaction added.");
                    break;

                case 2:
                    System.out.print("Enter file path: ");
                    String filename = scanner.nextLine();
                    manager.loadFromFile(filename);
                    System.out.println("Transactions loaded from file.");
                    break;

                case 3:
                    System.out.print("Enter year (e.g. 2025): ");
                    int year = scanner.nextInt();
                    System.out.print("Enter month (1-12): ");
                    int month = scanner.nextInt();
                    manager.showMonthlySummary(year, month);
                    break;

                case 4:
                    System.out.println("Program Exit");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
