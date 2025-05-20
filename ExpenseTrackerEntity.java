package com.entity;

import java.time.LocalDate;

public class ExpenseTrackerEntity {
	private LocalDate date;
	private String type; // "Income" or "Expense"
	private String category;
	private double amount;

	public ExpenseTrackerEntity(LocalDate date, String type, String category, double amount) {
        this.date = date;
        this.type = type;
        this.category = category;
        this.amount = amount;
    }

	public LocalDate getDate() {
		return date;
	}

	public String getType() {
		return type;
	}

	public String getCategory() {
		return category;
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return date + " - " + type + " - " + category + " - " + amount;
	}
}
