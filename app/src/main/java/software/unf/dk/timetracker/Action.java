package software.unf.dk.timetracker;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Class for handling user activities
 */

enum Classification { ENTERTAINMENT, EDUCATION, WORK }

public class Action {

    private String name;
    private Classification classification;
    private Date date;

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.ENGLISH);

    public Action(String name, Classification classification, Date date) {
        this.name = name;
        this.classification = classification;
        this.date = date;
    }

    /**
     * Convert Classification to a String
     * @param string String to be converted
     * @return Converted string
     */
    public static Classification getClassificationFromString(String string) {
        // TODO: user-generated classifications
        if (string.equals("Education")) {
            return Classification.EDUCATION;
        } else if (string.equals("Entertainment")) {
            return Classification.ENTERTAINMENT;
        } else if (string.equals("Work")) {
            return Classification.WORK;
        } else {
            Log.e("Error", "Action: Unrecognized classification");
            return null;
        }
    }

    public static String getStringFromClassification(Classification classification) {
        switch (classification) {
            case ENTERTAINMENT:
                return "Entertainment";
            case EDUCATION:
                return "Education";
            case WORK:
                return "Work";
            default:
                return null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static String dateToString(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static Date stringToDate(String string) {
        try{
            return DATE_FORMAT.parse(string);
        }
        catch (ParseException e){
            Log.e("Error", "Failed to parse date string.");
            return null;
        }
    }
}
