package software.unf.dk.timetracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static software.unf.dk.timetracker.WhatToShow.DAY;

enum WhatToShow {ALL, DAY, WEEK, MONTH, YEAR}
public class HistoryActivity extends Activity{

    /**
     * UI.
     */
    // List.
    private ListView actionListView;
    // Buttons.
    private Button oneLessDayB;
    private Button oneExtraDayB;
    private Button oneLessMonthB;
    private Button oneExtraMonthB;
    private Button todayB;
    // Text view.
    private TextView showDate;

    /**
     * Data.
     */
    private String currShownDate;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_view);
        layoutSetup();
    }

    private void layoutSetup() {
        setRefernces();

        final Context context = this;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM yyyy", Locale.ENGLISH);
        // TODO: use the date class, to get the data.
        //       Then set the new date as currShownDate. (As of yet it ain't working)
        currShownDate = "08/07 2017";





        upDateView();
    }

    private  void upDateView(){
        showDate.setText(currShownDate);
        Action[] values = setValues(WhatToShow.DAY, currShownDate);

        ArrayAdapter<Action> adapter = new ActionArrayAdapter(this, values);
        actionListView.setAdapter(adapter);

        actionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Action action = (Action) actionListView.getItemAtPosition(position);
                Intent intent = new Intent(HistoryActivity.this, ActionViewerActivity.class);
                intent.putExtra(ActionViewerActivity.ACTION_EXTRA, action);
            }
        });
    }

    private void setRefernces(){
        // List.
        actionListView = findViewById(R.id.actionList);

        // Buttons.
        oneExtraDayB = findViewById(R.id.oneExtraDayB);
        oneLessDayB = findViewById(R.id.onelessDayB);
        oneExtraMonthB = findViewById(R.id.oneExtraMonthB);
        oneLessMonthB = findViewById(R.id.oneLessMonthB);

        // Text view.
        showDate = findViewById(R.id.showDate);
    }

    private Action[] setValues(WhatToShow whatToShow, String date){
        ArrayList<Action> values = new ArrayList<>();

        // Find out what values to find.
        switch (whatToShow){
            case ALL:
                values = Action.actionList;
                break;
            case DAY:
                values = getDayData(date);
                break;
            case WEEK:
                values = getWeekData(date);
                break;
            case MONTH:
                values = getMonthData(date);
                break;
            case YEAR:
                values = getYearData(date);
                break;
        }

        // Make the list a array.
        Action[] result = values.toArray(new Action[0]);
        //  Reverse the array.
        for(int i = 0; i < result.length / 2; i++)
        {
            Action temp = result[i];
            result[i] = result[result.length - i - 1];
            result[result.length - i - 1] = temp;
        }
        return result;
    }

    private ArrayList<Action> getDayData(String date){
        ArrayList<Action> result = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM yyyy", Locale.ENGLISH);

        for (Action a : Action.actionList) {
            // Get the date of the acton.
            String aDate = dateFormat.format(a.getDate());
            // Is it the day we are looking after? Yes, then add it.
            if(aDate.equals(date)){
                result.add(a);
            }
        }
        return result;
    }

    private ArrayList<Action> getWeekData(String date){
        ArrayList<Action> result = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM yyyy", Locale.ENGLISH);

        /**
         * Not Yet working.
         */

        return result;
    }

    private ArrayList<Action> getMonthData(String month){
        ArrayList<Action> result = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("MM yyyy", Locale.ENGLISH);

        for (Action a : Action.actionList) {
            // Get the date of the acton.
            String aDate = dateFormat.format(a.getDate());
            // Is it the day we are looking after? Yes, then add it.
            if(aDate.equals(month)){
                result.add(a);
            }
        }
        return result;
    }


    private ArrayList<Action> getYearData(String year){
        ArrayList<Action> result = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);

        for (Action a : Action.actionList) {
            // Get the date of the acton.
            String aDate = dateFormat.format(a.getDate());
            // Is it the day we are looking after? Yes, then add it.
            if(aDate.equals(year)){
                result.add(a);
            }
        }
        return result;
    }

    /**
     *
     * For Button Input.
     *
     */

    public void oneExtraDay(View view){
        // Add a day to the current date displayed.
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM yyyy");
        Calendar c = Calendar.getInstance();
        try{
            c.setTime(sdf.parse(currShownDate));
        }
        catch (ParseException e){
            return;
        }

        c.add(Calendar.DATE, 1);  // number of days to add
        currShownDate = sdf.format(c.getTime());  // currShownDate is now the new date
        // Update display.
        upDateView();
    }
    public void oneLessDay(View view){
        // Add a day to the current date displayed.
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM yyyy");
        Calendar c = Calendar.getInstance();
        try{
            c.setTime(sdf.parse(currShownDate));
        }
        catch (ParseException e){
            return;
        }

        c.add(Calendar.DATE, -1);  // number of days to add
        currShownDate = sdf.format(c.getTime());  // currShownDate is now the new date
        // Update display.
        upDateView();
    }

    public void oneExtraMonth(View view){
        // Add a month to the current date displayed.
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM yyyy");
        Calendar c = Calendar.getInstance();
        try{
            c.setTime(sdf.parse(currShownDate));
        }
        catch (ParseException e){
            return;
        }

        c.add(Calendar.DATE, 30);  // number of days to add
        currShownDate = sdf.format(c.getTime());  // dt is now the new date
        // Update display.
        upDateView();
    }
    public void oneLessMonth(View view){
        // Add a day to the current date displayed.
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM yyyy");
        Calendar c = Calendar.getInstance();
        try{
            c.setTime(sdf.parse(currShownDate));
        }
        catch (ParseException e){
            return;
        }

        c.add(Calendar.DATE, -30);  // number of days to add
        currShownDate = sdf.format(c.getTime());  // currShownDate is now the new date
        // Update display.
        upDateView();
    }

    public void setDateToday(View view){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM yyyy", Locale.ENGLISH);
        currShownDate = dateFormat.format(new Date());
        Log.e("datetest",currShownDate);
        upDateView();
    }

}

class ActionArrayAdapter extends ArrayAdapter<Action> {
    private final Context context;
    private final Action[] values;

    public ActionArrayAdapter(Context context, Action[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Action a = values[position];

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.history_action_row, parent, false);
        TextView nameText = (TextView) convertView.findViewById(R.id.nameText);
        TextView timeText = (TextView) convertView.findViewById(R.id.timeText);
        TextView dateText = (TextView) convertView.findViewById(R.id.dateText);
        TextView classificationText = (TextView) convertView.findViewById(R.id.classificationText);

        DateFormat dateFormat = new SimpleDateFormat("EEE, dd/MM yyyy", Locale.ENGLISH);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        nameText.setText(a.getName());
        classificationText.setText(a.getClassification().getName());
        timeText.setText(timeFormat.format(a.getDate()));
        dateText.setText(dateFormat.format(a.getDate()));

        return convertView;
    }



}