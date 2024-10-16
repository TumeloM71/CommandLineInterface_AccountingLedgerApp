package com.pluralsight;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Reports {
    static Scanner input;
    public static void display(ArrayList<Transaction> transactions){

        input = new Scanner(System.in);
        int userInput ;
        while (true) {
            System.out.println("1) Month to Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year to Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search");
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
                case 6:
                    customSearch(transactions);
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
            if( t.getDate().compareTo(prev)>=0 && t.getDate().compareTo(now)<=0)
                System.out.println(t);
        }
    }

    public static void previousMonth(ArrayList<Transaction> transactions){
        LocalDate now = LocalDate.now();
        LocalDate prev = now.minusMonths(1);

        for( Transaction t : transactions){
            if( t.getDate().compareTo(prev)>=0 && t.getDate().compareTo(now)<=0)
                System.out.println(t);
        }
    }

    public static void customSearch(ArrayList<Transaction> transactions){
        ArrayList<Transaction> filteredList = new ArrayList<>(transactions); //Stored filtered elements
        ArrayList<Transaction> filter = new ArrayList<>(); //Stores elements removed by filters

        System.out.print("Start Date:");
        String startDate = input.nextLine().trim();
        System.out.print("End date: ");
        String endDate = input.nextLine().trim();
        System.out.print("Description: ");
        String description = input.nextLine().trim();
        System.out.print("Vendor: ");
        String vendor = input.nextLine().trim();
        System.out.println("Amount" );
        String amount = input.nextLine().trim();

        //If the user enters a field, add all elements which don't match to the filter list
        if (!startDate.isEmpty()){
            for(Transaction t : transactions){
                if ( t.getDate().compareTo(Utilities.getDate(startDate)) < 0)
                    filter.add(t);
            }
        }
        if (!endDate.isEmpty()){
            for(Transaction t : transactions){
                if( t.getDate().compareTo(Utilities.getDate(endDate))>0)
                    filter.add(t);
            }
        }

        if(!description.isEmpty()){
            /*
            List.sort() is used again but with a different sort key
            This is to improve the performance of the for loop because of branch prediction
            i.e if the computer knows descripitons starts with an E, descriptions from A to D
            will be skipped by the if statement.
            This will only make a noticable difference for very large lists, but I wanted to try it
             */
            transactions.sort(Comparator.comparing(Transaction::getDescription));
            for( Transaction t : transactions){
                if( !t.getDescription().equalsIgnoreCase(description))
                    filter.add(t);
            }
        }

        if(!vendor.isEmpty()){
            transactions.sort(Comparator.comparing(Transaction::getVendor));
            for( Transaction t : transactions){
                if( !t.getVendor().equalsIgnoreCase(vendor))
                    filter.add(t);
            }
        }

        if(!amount.isEmpty()){
            transactions.sort(Comparator.comparing(Transaction::getAmount));
            for( Transaction t : transactions){
                if( !(t.getAmount() == Utilities.getDoubleValue(amount)) )
                    filter.add(t);
            }
        }
        //Remove all elements which don't match the given filters by calling removeAll()
        filteredList.removeAll(filter);
        //Filtered ist is sorted by the date like the original unfiltered one
        filteredList.sort(Comparator.comparing(Transaction::getDate));
        for( Transaction t : filteredList)
            System.out.println(t);

    }

}
