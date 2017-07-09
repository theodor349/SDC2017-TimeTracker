package software.unf.dk.timetracker;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/***
 * Class for handling user activities
 */
class Action implements Parcelable {
    public static ArrayList<Action> actionList = new ArrayList<>();

    private String name;
    private Classification classification;
    private Date date;

    private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.ENGLISH);

    public Action(String name, Classification classification, Date date) {
        this.name = name;
        this.classification = classification;
        this.date = date;
    }

    /**
     * Getter and setters.
     */
    // Name.
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Classification.
    public Classification getClassification() {
        return classification;
    }
    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    // Dates and time.
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public static String dateToString(Date date) {
        return DATETIME_FORMAT.format(date);
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

    // Parcel functions
    protected Action(Parcel in) {
        name = in.readString();
        classification = (Classification) in.readValue(Classification.class.getClassLoader());
        long dateLong = in.readLong();
        date = dateLong != -1 ? new Date(dateLong) : null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeValue(classification);
        out.writeLong(date != null ? date.getTime() : -1L);
    }

    public static final Parcelable.Creator<Action> CREATOR = new Parcelable.Creator<Action>() {
        @Override
        public Action createFromParcel(Parcel in) {
            return new Action(in);
        }

        @Override
        public Action[] newArray(int size) {
            return new Action[size];
        }
    };
}
