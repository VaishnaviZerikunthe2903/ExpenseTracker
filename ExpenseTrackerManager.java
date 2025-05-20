package com.services;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.entity.ExpenseTrackerEntity;

public class ExpenseTrackerManager {
    private List<ExpenseTrackerEntity> transactions = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String DEFAULT_FILE = "transactions.txt";

    public void addTransaction(ExpenseTrackerEntity tx) {
        transactions.add(tx);
        saveTransactionToFile(tx, DEFAULT_FILE);
    }

    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) continue;
                LocalDate date = LocalDate.parse(parts[0], formatter);
                String type = parts[1];
                String category = parts[2];
                double amount = Double.parseDouble(parts[3]);
                transactions.add(new ExpenseTrackerEntity(date, type, category, amount));
            }
            System.out.println(transactions);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void showMonthlySummary(int year, int month) {
        double income = 0, expense = 0;
        System.out.println("Summary for " + year + "-" + month);
        for (ExpenseTrackerEntity tx : transactions) {
            if (tx.getDate().getYear() == year && tx.getDate().getMonthValue() == month) {
                System.out.println(tx);
                if (tx.getType().equalsIgnoreCase("Income")) {
                    income += tx.getAmount();
                } else {
                    expense += tx.getAmount();
                }
            }
        }
        System.out.println("Total Income: " + income);
        System.out.println("Total Expense: " + expense);
        System.out.println("Net Savings: " + (income - expense));
    }

    private void saveTransactionToFile(ExpenseTrackerEntity tx, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(tx.getDate() + "," + tx.getType() + "," + tx.getCategory() + "," + tx.getAmount());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
