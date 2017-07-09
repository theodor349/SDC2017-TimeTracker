package software.unf.dk.timetracker;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class lineChart extends AppCompatActivity {

    private com.github.mikephil.charting.charts.LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        setReference();

        createDummyChart();
    }

    private void createDummyChart(){
        ArrayList<Integer> amounts = new ArrayList<>();

        amounts.add(10);
        amounts.add(5);
        amounts.add(8);
        amounts.add(6);

        makeLineChart("Football", amounts, lineChart);
    }

    private void setReference(){
        lineChart = (com.github.mikephil.charting.charts.LineChart) findViewById(R.id.chart);
        LineChart lineChart = new LineChart(this);
    }

    // This code can be place in an Instance to create a lineChart.
    private void makeLineChart(String title, ArrayList<Integer> amounts, LineChart lineChart) {
        // Creates the "chart" as in where it is placed. (A reference)
        lineChart.setData(Charts.createLineData(title, amounts));
        // Updates the chart with the new values.
        lineChart.invalidate();
        // A String of what labels should be on the x-axis. (in order of x1,x2,x3...)
        final String[] labels = new String[] {"D1", "D2", "D3", "D4"};
        // Sets the labels.
        Charts.getXAxisData(labels, lineChart);
    }


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
