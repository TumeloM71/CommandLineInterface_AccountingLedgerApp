package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Transaction {
    LocalDate date;
    LocalTime time;
    String description,vendor;
    double amount;

    //Construct a Transaction instance from a line in the .csv file
    public Transaction (String line){
        String[] lineArr = line.split("\\|");
        this.date = Utilities.getDate(lineArr[0]);
        this.time = Utilities.getTime(lineArr[1]);
        this.description = lineArr[2];
        this.vendor = lineArr[3];
        this.amount = Utilities.getDoubleValue(lineArr[4]);

    }

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
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
