package software.unf.dk.timetracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    private int amountOfTenis = 10;
    private int amountTotal = 100;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        PieChart pieChart = (PieChart) findViewById(R.id.pie);

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(amountOfTenis, "Tennis"));
        entries.add(new PieEntry(amountTotal, "Total"));

        PieDataSet set = new PieDataSet(entries, "Sport");
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh


    }
}
