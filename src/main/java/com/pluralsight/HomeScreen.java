package com.pluralsight;

import java.util.Scanner;

public class HomeScreen {

    static Scanner input = new Scanner(System.in);
    static Ledger ledger = new Ledger();
    public static void main(String[] args) {

        ledger.writeColumns();

        System.out.println("D) Add deposit");
        System.out.println("P) Make payment(debit)");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        String selection = input.nextLine();
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
            case "x":
            case "X":
                System.exit(0);

        }
    }
    public static void  addDeposit(){
        System.out.println("Enter the description");
        String description = input.nextLine();
        System.out.println("Enter the vendor");
        String vendor = input.nextLine();
        System.out.println("Enter the amount");
        double amount = input.nextDouble();
        Transaction t = new Transaction(description,vendor,amount);
        ledger.add(t);
    }

    public static void makePayment(){
        System.out.println("Enter the description");
        String description = input.nextLine();
        System.out.println("Enter the vendor");
        String vendor = input.nextLine();
        System.out.println("Enter the amount");
        double amount = input.nextDouble() * -1;
        Transaction t = new Transaction(description,vendor,amount);
        ledger.add(t);
    }
}
