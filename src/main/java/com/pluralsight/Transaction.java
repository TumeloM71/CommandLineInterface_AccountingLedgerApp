package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Transaction {
    LocalDate date;
    LocalTime time;
    String description,vendor;
    double amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public Transaction(String description, String vendor, double amount) {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", time=" + time +
                ", description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return Double.compare(getAmount(), that.getAmount()) == 0 && Objects.equals(getDate(), that.getDate()) && Objects.equals(getTime(), that.getTime()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getVendor(), that.getVendor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getTime(), getDescription(), getVendor(), getAmount());
    }
}
