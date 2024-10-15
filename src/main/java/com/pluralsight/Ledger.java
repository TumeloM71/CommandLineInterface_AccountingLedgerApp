package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {

    private final BufferedWriter bufferedWriter;
    Scanner input ;

    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Ledger() {
       input = new Scanner(System.in);
       try{
           FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);
           bufferedWriter = new BufferedWriter(fileWriter);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }

    public void writeColumns() {
        try {
            bufferedWriter.write("date|time|description|vendor|amount\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void display(){
        System.out.println("A) All");
        System.out.println("D) Deposits");
        System.out.println("P) Payments");
        System.out.println("R) Reports");
        String selection = input.nextLine();
        switch (selection){
            case "A":
            case "a":
                for( Transaction t : transactions)
                    System.out.println(t);
                break;

            case "D":
            case "d":
                for( Transaction t : transactions){
                    if( t.getAmount()>=0)
                        System.out.println(t);
                }
                break;

            case "P":
            case "p":
                for( Transaction t : transactions){
                    if (t.getAmount()<0)
                        System.out.println(t);
                }
                break;
        }
    }

    public void add(Transaction t){
        transactions.add(t);
        try {
            bufferedWriter.write(t.getDate().toString() + "|" + t.getTime().toString() + "|" + t.getDescription()
                    + t.getVendor() + "|" + t.getAmount()+"\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
