package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Reports {
    static Scanner input;
    public static void display(ArrayList<Transaction> transactions){

        input = new Scanner(System.in);
        int userInput = Integer.MIN_VALUE;
        while (true) {
            System.out.println("1) Month to Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year to Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");

            userInput =  Utilities.getIntValue(input.nextLine().trim());
            switch (userInput){

                case 1:
                    monthToDate(transactions);
                    break;
                case 2:
                    previousMonth(transactions);
                    break;
                case 3:
                    yearToDate(transactions);
                    break;
                case 4:
                    previousYear(transactions);
                    break;
                case 5:
                    searchByVendor(transactions);
                    break;
                case 0:
                    return;
            }
        }

    }

    public static void monthToDate(ArrayList<Transaction> transactions){
        for( Transaction t : transactions){
            if(t.getDate().getMonthValue() == LocalDate.now().getMonthValue())
                System.out.println(t);
        }
    }

    public static void yearToDate(ArrayList<Transaction> transactions){
        for( Transaction t : transactions){
            if( t.getDate().getYear() == LocalDate.now().getYear())
                System.out.println(t);
        }
    }

    public static void searchByVendor(ArrayList<Transaction> transactions){
        input = new Scanner(System.in);
        System.out.print("Enter the vendor name: ");
        String vendor = input.nextLine().trim();
        for( Transaction t : transactions){
            if( t.getVendor().equalsIgnoreCase(vendor))
                System.out.println(t);
        }
    }

    public static void previousYear(ArrayList<Transaction> transactions){
        LocalDate now = LocalDate.now();
        LocalDate prev = now.minusYears(1);

        for( Transaction t : transactions){
            if( t.getDate().isAfter(prev) && t.getDate().isBefore(now))
                System.out.println(t);
        }
    }

    public static void previousMonth(ArrayList<Transaction> transactions){
        LocalDate now = LocalDate.now();
        LocalDate prev = now.minusMonths(1);

        for( Transaction t : transactions){
            if( t.getDate().isAfter(prev) && t.getDate().isBefore(now))
                System.out.println(t);
        }
    }

}
