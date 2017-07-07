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

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.ENGLISH);

    public Action(String name, Classification classification, Date date) {
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
