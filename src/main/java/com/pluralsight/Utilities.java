package com.pluralsight;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

//Class for handling user input with try-catch blocks
public class Utilities{

    public static long getLongValue(String in)
    {
        long out = Long.MIN_VALUE;
        try
        {
            out = Long.parseLong(in);
        }
        catch(NumberFormatException e)
        {
            throw new IllegalArgumentException(in + " cannot be converted into a 'long' value. Exiting program.");
        }
        return out;
    }

    public static int getIntValue(String in)
    {
        int out = Integer.MIN_VALUE;
        try
        {
            out = Integer.parseInt(in);
        }
        catch(NumberFormatException e)
        {
            throw new IllegalArgumentException(in + " cannot be converted into a 'int' value. Exiting program.");
        }
        return out;
    }

    public static double getDoubleValue(String in)
    {
        double out = Double.MIN_VALUE;
        try
        {
            out = Double.parseDouble(in);
        }
        catch(NumberFormatException e)
        {
            throw new IllegalArgumentException(in + " cannot be converted into a 'double' value. Exiting program.");
        }
        return out;
    }


    public static LocalDate getDate (String input) {
        LocalDate date;
        try{
            date = LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException( input+" cannot be converted into a valid date. Exiting program.");
        }
        return date;
    }
    public static LocalTime getTime (String input) {
        LocalTime time;
        try{
            time = LocalTime.parse(input);
        }
        catch (DateTimeParseException e){
            throw new IllegalArgumentException( input+" cannot be converted into a valid time. Exiting program.");
        }
        return time;
    }


}