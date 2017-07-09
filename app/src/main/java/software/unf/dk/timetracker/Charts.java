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
    public static LineData createLineData(String title, ArrayList<Integer> amounts){
        LineDataSet dataSet = new LineDataSet(createEntries(amounts), title);
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        LineData data = new LineData(dataSet);
        return data;
    }

    public static XAxis getXAxisData(String[] names, LineChart lineChart){
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

        return xAxis;
    }

    private static List<Entry> createEntries(ArrayList<Integer> amounts) {
        List<Entry> result = new ArrayList<>();

        for (int i = 0; i < amounts.size(); i++) {
            Entry entry = new Entry((float) i, (float) amounts.get(i));
            result.add(entry);
        }

        return result;
    }
}