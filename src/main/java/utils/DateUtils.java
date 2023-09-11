package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    // Define a date format string that matches your input format
    private static final String DATE_FORMAT = "yyyy-MM-dd"; // Change this format as needed

    public static Date parseStringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            // Handle parsing exception
            e.printStackTrace();
            return null; // Return null if parsing fails
        }
    }
}