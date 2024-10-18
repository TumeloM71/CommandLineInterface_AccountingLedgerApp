package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

//This class handles reading and writing to transactions.csv
//It also has an ArrayList of all the transactions as an instance variable
public class Ledger {

    private final BufferedWriter bufferedWriter;
    private final BufferedReader bufferedReader;
    private Scanner input ;

    private static ArrayList<Transaction> transactions;

    /*
    In this constructor, the program reads from the csv file and makes Transaction objects for each line
    It then stores those objects in the ArrayList.
    If I were to delete something from the .csv file, the constructor reads the updated file
    when making a new instance
     */
    public Ledger() {
        input = new Scanner(System.in);
       try{
           FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);
           bufferedWriter = new BufferedWriter(fileWriter);
           FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
           bufferedReader = new BufferedReader(fileReader);
           transactions = new ArrayList<>();

           bufferedReader.readLine(); // Skip line with the column headings
           String line ;
           while( (line=bufferedReader.readLine())!=null ){
               transactions.add(new Transaction(line));
           }
           /*
           Transaction::getDate extracts the date from a Transaction object
           Comparator.comparing takes in that function and returns a comparator using the date as a sort key
           i.e it's basically saying "given a list of objects of type X, which part of X do you want sort by?"
           I decided to use it here so transactions would be sorted by date in the ArrayList
            */
           bufferedReader.close();
           transactions.sort(Comparator.comparing(Transaction::getDate));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }

    //When the user selects 'L) Ledger' in Homescreen, this method is called.
    public void display(){
        System.out.println("A) All");   //List all entries
        System.out.println("D) Deposits"); //List deposits only
        System.out.println("P) Payments"); // List payments only
        System.out.println("R) Reports"); // Brings up the reports screen
        System.out.println("H) Return to home screen"); //Returns to the previous screen
        String selection = input.nextLine();
        switch (selection){
            case "A":
            case "a":
                transactions.sort(Comparator.comparing(Transaction::getDate));
                System.out.println("All entries:");
                for( Transaction t : transactions)
                    System.out.println(t);
                break;

            //Amount is a positive number for a deposit
            case "D":
            case "d":
                transactions.sort(Comparator.comparing(Transaction::getDate));
                System.out.println("Deposits");
                for( Transaction t : transactions){
                    if( t.getAmount()>=0)
                        System.out.println(t);
                }
                break;

            //Amount is a negative number for a deposit
            case "P":
            case "p":
                transactions.sort(Comparator.comparing(Transaction::getDate));
                System.out.println("Payments:");
                for( Transaction t : transactions){
                    if (t.getAmount()<0)
                        System.out.println(t);
                }
                break;
            case "R":
            case "r":
                Reports.display(transactions);
                break;
            case "H":
            case "h":
                return;

            default:
                throw new IllegalStateException("Unexpected value: " + selection);
        }
    }

    /*This method is used when the user wants to add a transaction
      It adds the transaction to the arraylist and writes it to the .csv file
     */
    public void add(Transaction t){
        transactions.add(t);
        try {
            bufferedWriter.write(t.getDate().toString() + "|" + t.getTime().toString() + "|" + t.getDescription()
                    +"|" + t.getVendor() + "|" + t.getAmount()+"\n");
            bufferedWriter.flush();
            System.out.println("Transaction added");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
