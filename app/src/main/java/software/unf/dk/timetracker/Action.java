package software.unf.dk.timetracker;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Class for handling user activities
 */

class Action {
    public static ArrayList<Action> actionList = new ArrayList<>();

    private String name;
    private Classification classification;
    private Date date;

    private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.ENGLISH);
    private static final DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    private static final DateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    private static final DateFormat MONTH_FORMAT = new SimpleDateFormat("MM", Locale.ENGLISH);
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd", Locale.ENGLISH);
    private static final DateFormat WEEKDAY_FORMAT = new SimpleDateFormat("EEE", Locale.ENGLISH);


    public Action(String name, Classification classification, Date date) {
        Log.e("Test", "Action constructed with name " + name);
        this.name = name;
        this.classification = classification;
        this.date = date;
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
        return DATETIME_FORMAT.format(date);
    }

    public static String getDateYear(Date date) {
        return YEAR_FORMAT.format(date);
    }

    public static String getDateMonth(Date date) {
        return MONTH_FORMAT.format(date);
    }

    public static Date stringToDate(String string) {
        try{
            return DATETIME_FORMAT.parse(string);
        }
        catch (ParseException e){
            Log.e("Error", "Failed to parse date string.");
            return null;
        }
    }
}
