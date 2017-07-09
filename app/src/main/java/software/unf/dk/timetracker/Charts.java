package software.unf.dk.timetracker;

import android.content.Context;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class Charts {


    /**
     * Pie Chart.
     */

















































    /**
     * Line Chart.
     *
     * To create a chart in another instance you need thees two lines.
     *          lineChart.setData(Charts.createLineData(title, amounts));
     *          lineChart.invalidate();
     */

    /**
     * Code for implementing it in an Instance.
     *

     // If there is a reference to a chart, this will set all the alues.
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

     *
     */


    // Creates all the data for a given line chart.
    public static LineData createLineData(String title, ArrayList<Integer> amounts){
        LineDataSet dataSet = new LineDataSet(createEntries(amounts), title);
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        LineData data = new LineData(dataSet);
        return data;
    }

    // Assigns the labels for the x-axis.
    public static XAxis getXAxisData(String[] names, LineChart lineChart){
        // the labels that should be drawn on the XAxis
        final String[] labels = names;

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return labels[(int) value];
            }

            // we don't draw numbers, so no decimal digits needed
            public int getDecimalDigits() {  return 0; }
        };

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        return xAxis;
    }

    // Formats the list of column values to a list of Entries, with the createLineData can use.
    private static List<Entry> createEntries(ArrayList<Integer> amounts) {
        List<Entry> result = new ArrayList<>();

        for (int i = 0; i < amounts.size(); i++) {
            Entry entry = new Entry((float) i, (float) amounts.get(i));
            result.add(entry);
        }

        return result;
    }
}