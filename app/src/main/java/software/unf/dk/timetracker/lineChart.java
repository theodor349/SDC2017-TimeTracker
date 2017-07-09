package software.unf.dk.timetracker;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.File;
import java.util.ArrayList;

public class lineChart extends AppCompatActivity {

   private com.github.mikephil.charting.charts.LineChart lineChart;

    //variables
    //Strings
    //Strings Arrays
    private static String[] paths;
    private String classificationString;
    private final String ACTIONS_FILENAME = "actions.xml";
    private final String CLASSIFICATIONS_FILENAME = "classifications.xml";
    private Spinner spinLinechart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        setReference();
    }

    private void setReference(){
        lineChart = (com.github.mikephil.charting.charts.LineChart) findViewById(R.id.chart);
        LineChart lineChart = new LineChart(this);
    }

    /**private void makePieChart(String title, ArrayList<String> dates, ArrayList<Integer> amounts) {
        LineData dataSet = new LineDataSet();
        dataSet.setColors(new int[] { R.color.neonpink, R.color.green, R.color.blue, R.color.lightgreen, R.color.red, R.color.yellow, R.color.lightblue, R.color.magenza, R.color.orange, R.color.turqoise, R.color.pumpkin, R.color.palepink, R.color.svump, R.color.darkpurple }, getApplicationContext());
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh

    }**/


    /** Sets Spinner**/
  /**  private void setSpinner(){
        paths = Classification.mapToStringList(Classification.classificationMap).toArray(new String[0]);

        spinLinechart = (Spinner) findViewById(R.id.spinLinechart);

        // Doing so the Array can be put into the Spinner
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinLinechart.setAdapter(adapter);
        // Listen to things happens on the Spinner
        spinLinechart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                 @Override
                                                 public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                     // Set string to selected spinner value
                                                     classificationString = paths[i];
                                                 }

                                                 @Override
                                                 public void onNothingSelected(AdapterView<?> adapterView) { }
                                             }
        );
    }**/


}
