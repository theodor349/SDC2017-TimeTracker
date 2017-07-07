package software.unf.dk.timetracker;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

//import com.github.mikephil.charting.charts.LineChart;
//import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import software.unf.dk .timetracker.R;

public class PieChartView extends AppCompatActivity {

    private int amountOfTennis = 100;
    private int amountSoccer = 200;
    private int amountSwimming = 50;
    private int amountBadminton = 90;
    private com.github.mikephil.charting.charts.PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        com.github.mikephil.charting.charts.PieChart pieChart = (com.github.mikephil.charting.charts.PieChart) findViewById(R.id.pie);

        List<PieEntry> entries = new ArrayList<>();



        inToChart(amountOfTennis,entries,"Tennis");
        inToChart(amountSoccer,entries,"Soccer");
        inToChart(amountSwimming,entries,"Swimming");
        inToChart(amountBadminton,entries,"Badminton");


        PieDataSet dam = new PieDataSet(entries, "Sport");
        PieData data = new PieData(dam);
        dam.setColors(new int[] { R.color.neonpink, R.color.green, R.color.blue, R.color.lightgreen, R.color.red, R.color.yellow, R.color.lightblue, R.color.magenza, R.color.orange, R.color.turqoise, R.color.pumpkin, R.color.palepink, R.color.svump, R.color.darkpurple }, getApplicationContext());
        pieChart.setData(data);
        pieChart.invalidate(); // refresh


    }

    private void inToChart (int amount, List list, String cataName){
        list.add(new PieEntry(amount, cataName));

    }

    private void makePieChart( ){

    }
}
