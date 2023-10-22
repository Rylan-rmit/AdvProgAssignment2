package com.rmit.advprog.assignment2;

public class util {

    public static boolean isInt(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            System.out.println("Your input: " + strNum + " must be an integer");
            return false;
        }
        return true;
    }
    
    public static String validateString(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null; // Content is empty or only white spaces
        }
        return input; // Content is valid
    }

    public static int validateNonNegativeNumber(String input) {
        try {
            int number = Integer.parseInt(input);
            
            if (number < 0) {
                return -2; // Number is negative
            }
            
            return number; // Number is valid (non-negative)
        } catch (NumberFormatException e) {
            return -1; // Input string is not a valid number
        }
    }

    public static boolean isValidDate(String dateTime) {
        String[] dateTimeArray;
        String[] dateArray;
        String[] timeArray;
        if (!dateTime.contains(" "))
        {
            return false;
        }
        dateTimeArray = dateTime.split(" ");
        if (dateTimeArray.length != 2)
        {
            return false;
        }

        if (dateTimeArray[0].chars().filter(ch -> ch == '/').count() != 2)
        {
            return false;
        }
        else if (dateTimeArray[1].chars().filter(ch -> ch == ':').count() != 1)
        {
            return false;
        }
        dateArray = dateTimeArray[0].split("/");
        timeArray = dateTimeArray[1].split(":");
        if (dateArray.length != 3 || timeArray.length != 2)
        {
            return false;
        }

        if (!isInt(dateArray[0]) || !isInt(dateArray[1]) || !isInt(dateArray[2]) || !isInt(timeArray[0]) || !isInt(timeArray[1]))
        {
            return false;
        }
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);
        int hour = Integer.parseInt(timeArray[0]);
        int minuite = Integer.parseInt(timeArray[1]);

        if (day > 31 || day < 1)
        {
            return false;
        }
        if (month > 12 || month < 1)
        {
            return false;
        }
        if (year < 0)
        {
            return false;
        }
        if (hour > 23 || hour < 0)
        {
            return false;
        }
        if (minuite > 59 || minuite < 0)
        {
            return false;
        }
        return true;
    }
}
