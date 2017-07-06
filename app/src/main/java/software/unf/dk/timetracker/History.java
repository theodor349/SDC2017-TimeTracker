package software.unf.dk.timetracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;

public class History extends AppCompatActivity {

    private int amountOfTenis = 10;
    private int amountTotal = 100;
    private LineChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        LineChart pieChart = (LineChart) findViewById(R.id.pie);


    }
}
