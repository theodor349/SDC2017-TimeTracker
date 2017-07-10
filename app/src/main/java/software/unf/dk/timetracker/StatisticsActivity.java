package software.unf.dk.timetracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StatisticsActivity extends Activity {

    private ListView statListView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        layoutSetup();
    }

    private void layoutSetup() {
        setReferences();

        updateView();
    }

    private void setReferences(){
        // List.
        statListView = findViewById(R.id.underCategoryList);
    }

    void updateView(){
        String[] values = Action.getNames().toArray(new String[0]);
        ArrayAdapter<String> adapter = new StatisticsArrayAdapter(this, values);
        statListView.setAdapter(adapter);
    }

}

class StatisticsArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public StatisticsArrayAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        String name = values[position];

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.activity_statistics_row, parent, false);
        TextView nameText = (TextView) convertView.findViewById(R.id.nameOfChart);
        LineChart chart = (LineChart) convertView.findViewById(R.id.chart);

        nameText.setText(name);


        // Find amounts.
        DateFormat dateFormat = new SimpleDateFormat("dd/MM yyyy", Locale.ENGLISH);

        ArrayList<Integer> amounts = new ArrayList<>();
        String date = dateFormat.format(new Date());
        int amount = 0;
        for (int i = 0; i < 7; i++) {
            amount = 0;
            for (Action a : Action.getAllWithName(name)) {
                if (dateFormat.format(a.getDate()).equals(date)) {
                    // Date we look for.
                    amount++;
                }
            }

            // Increment the day looked at.
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(dateFormat.parse(date));
            } catch (ParseException e) {

            }
            c.add(Calendar.DATE, -1);  // number of days to add
            date = dateFormat.format(c.getTime());  // currShownDate is now the new date

            amounts.add(amount);
        }

        makeLineChart(name, amounts, chart);

        return convertView;
    }

    // If there is a reference to a chart, this will set all the values.
    private void makeLineChart(String title, ArrayList<Integer> amounts, LineChart lineChart) {
        Log.i("Test", "Title: " + title + ", Amounts: " + amounts + ", Chart: " + lineChart);
        // Creates the "chart" as in where it is placed. (A reference)
        lineChart.setData(Charts.createLineData(title, amounts));
        // Updates the chart with the new values.
        lineChart.invalidate();
        // A String of what labels should be on the x-axis. (in order of x1,x2,x3...)
        final String[] labels = new String[] {"D1", "D2", "D3", "D4", "D5", "D6", "D7"};
        // Sets the labels.
        Charts.getXAxisData(labels, lineChart);
    }


}
