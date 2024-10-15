package com.pluralsight;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;

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

    public static LocalDate getDate (int year, int month, int day){
        LocalDate date;
        try{
            date = LocalDate.of(year,month,day);
        }
        catch (DateTimeException e){
            throw new IllegalArgumentException( year+":"+month+":"+day+" cannot be converted into a valid date. Exiting program.");
        }
        return date;
    }

    public static LocalTime getTime (String input){
        LocalTime time;
        try{
            String[] inputArr = input.split(Pattern.quote(":"));
            int hours = Integer.parseInt(inputArr[0]);
            int minutes = Integer.parseInt(inputArr[1]);
            int seconds = Integer.parseInt(inputArr[2]);
            time = LocalTime.of(hours, minutes, seconds);
        }
        catch (Exception e){
            throw new IllegalArgumentException( input+" cannot be converted into a valid time. Exiting program.");
        }
        return time;
    }

}