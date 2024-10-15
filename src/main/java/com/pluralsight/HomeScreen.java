package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class HomeScreen {

    static Scanner input = new Scanner(System.in);
    static Ledger ledger = new Ledger();
    static FileReader fileReader ;
    public static void main(String[] args) {
        try {
            fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader bufferedReader =new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    //    ledger.writeColumns();
        String selection = "";
        while (true) {
            System.out.println("D) Add deposit");
            System.out.println("P) Make payment(debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            selection = input.nextLine();

            switch (selection) {
                case "D":
                case "d":
                    addDeposit();
                    break;
                case "P":
                case "p":
                    makePayment();
                    break;
                case "L":
                case "l":
                    ledger.display();
                    break;
                case "x":
                case "X":
                    System.exit(0);
                default:
                    throw new IllegalStateException("Unexpected value: " + selection);

            }
        }
    }
    public static void  addDeposit(){
        System.out.print("Enter the description: ");
        String description = input.nextLine().trim();
        System.out.print("Enter the vendor: ");
        String vendor = input.nextLine().trim();
        System.out.print("Enter the amount: ");
        double amount = Utilities.getDoubleValue(input.nextLine().trim());

        System.out.print("Enter the year(e.g 2023): ");
        int year = Utilities.getIntValue(input.nextLine().trim());
        System.out.println("Enter the month(e.g 12): ");
        int month = Utilities.getIntValue(input.nextLine().trim());
        System.out.print("Enter the day(e.g 31): ");
        int day = Utilities.getIntValue(input.nextLine().trim());

        LocalDate date = Utilities.getDate(year,month,day);
        System.out.print("Enter the time ( HH : mm : ss): ");
        String timeAsString = input.nextLine().trim();
        LocalTime time = Utilities.getTime(timeAsString);

        Transaction t = new Transaction(date,time,description,vendor,amount);
        ledger.add(t);
    }

    public static void makePayment(){
        System.out.println("Enter the description");
        String description = input.nextLine();
        System.out.println("Enter the vendor");
        String vendor = input.nextLine();
        System.out.println("Enter the amount");
        double amount = Utilities.getDoubleValue(input.nextLine().trim()) * -1;

        System.out.print("Enter the year(e.g 2023): ");
        int year = Utilities.getIntValue(input.nextLine().trim());
        System.out.print("Enter the month(e.g 12): ");
        int month = Utilities.getIntValue(input.nextLine().trim());
        System.out.print("Enter the day(e.g 31): ");
        int day = Utilities.getIntValue(input.nextLine().trim());

        LocalDate date = Utilities.getDate(year,month,day);
        System.out.print("Enter the time ( HH : mm : ss): ");
        String timeAsString = input.nextLine().trim();
        LocalTime time = Utilities.getTime(timeAsString);

        Transaction t = new Transaction(date,time,description,vendor,amount);
        ledger.add(t);
    }
}
