package software.unf.dk.timetracker;

import android.media.tv.TvView;

import java.security.Timestamp;
import java.sql.Time;
import java.util.Date;

/**
 * Class for handling user activities
 */

enum Classification { ENTERTAINMENT, EDUCATION, WORK }

public class Action {

    private String name;
    private Classification classification;
    private Date date;

    public Action(String name, Classification classification, Date date) {
        this.name = name;
        this.classification = classification;
        this.date = date;
    }
}
