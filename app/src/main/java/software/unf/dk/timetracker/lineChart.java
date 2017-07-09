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

        makeLineChart("Football", amounts);
    }

    private void setReference(){
        lineChart = (com.github.mikephil.charting.charts.LineChart) findViewById(R.id.chart);
        LineChart lineChart = new LineChart(this);
    }

    final String[] quarters = new String[] {"Q1", "Q2", "Q3", "Q4"};

    private void makeLineChart(String title, ArrayList<Integer> amounts) {
        lineChart.setData(Charts.createLineData(title, amounts));
        lineChart.invalidate();

        Charts.getXAxisData(quarters, lineChart);

    }

    private void makeOtherLineChart(){
        List<Entry> valsComp1 = new ArrayList<Entry>();

        Entry c1e1 = new Entry(0f, 100000f); // 0 == quarter 1
        valsComp1.add(c1e1);
        Entry c1e2 = new Entry(1f, 140000f); // 1 == quarter 2 ...
        valsComp1.add(c1e2);
        Entry c1e3 = new Entry(2f, 120000f); // 1 == quarter 2 ...
        valsComp1.add(c1e3);
        Entry c1e4 = new Entry(3f, 140000f); // 1 == quarter 2 ...
        valsComp1.add(c1e4);
        // and so on ...

        LineDataSet setComp1 = new LineDataSet(valsComp1, "Company 1");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);

        // use the interface ILineDataSet
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setComp1);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate(); // refresh

        // the labels that should be drawn on the XAxis
        final String[] quarters = new String[] { "Q1", "Q2", "Q3", "Q4" };

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }

            // we don't draw numbers, so no decimal digits needed
            public int getDecimalDigits() {  return 0; }
        };

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);


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
