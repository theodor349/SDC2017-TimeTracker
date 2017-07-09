package software.unf.dk.timetracker;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

//import com.github.mikephil.charting.charts.LineChart;
//import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

public class PieChartView extends AppCompatActivity {

    private com.github.mikephil.charting.charts.PieChart pieChart;


    //Varibles
    //Strings
    private String classificationString;
    //Strings Arrays
    private static String[] paths;
    //Spinners
    private Spinner pieChooser;
    private final String ACTIONS_FILENAME = "actions.xml";
    private final String CLASSIFICATIONS_FILENAME = "classifications.xml";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        setReference();
        createdummychart();
    }

    private void createdummychart(){
        String title = "sport";
        ArrayList<String> names = new ArrayList<>();

        names.add("Fodbold");
        names.add("Swimming");
        names.add("Running");
        names.add("Basket");
        names.add("Rugby");

        ArrayList<Integer> amounts = new ArrayList<>();
        amounts.add(5);
        amounts.add(6);
        amounts.add(7);
        amounts.add(4);
        amounts.add(3);

        makePieChart(title, names, amounts, pieChart);
    }

    private void setReference(){
        /*pieChart = (com.github.mikephil.charting.charts.PieChart) findViewById(R.id.pie);
        pieChooser = (Spinner) findViewById(piespinner);
        setSpinner();*/
    }

    /** Setter Spinner**/
    private void setSpinner(){
        paths = Classification.mapToStringList(Classification.classificationMap).toArray(new String[0]);

        // Doing so the Array can be put into the Spinner
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pieChooser.setAdapter(adapter);
        // Listen to things happens on the Spinner
        pieChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Set string to selected spinner value
                classificationString = paths[i];
                                              }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
                                          }
        );
    }

    // If there is a reference to a chart, this will set all the values.
    private void makePieChart(String title, ArrayList<String> names, ArrayList<Integer> amounts, PieChart pieChart) {
        int[] colors = new int[] { R.color.neonpink, R.color.green, R.color.blue, R.color.lightgreen, R.color.red, R.color.yellow, R.color.lightblue, R.color.magenza, R.color.orange, R.color.turqoise, R.color.pumpkin, R.color.palepink, R.color.svump, R.color.darkpurple }, getApplicationContext;
        PieDataSet dataSet = new PieDataSet(Charts.createEntries(names, amounts), title);
        dataSet.setColors(colors, this);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh
    }

    public void piechartInput(View view){
        String title = classificationString;
        ArrayList<String> names = Action.getNames(classificationString);
        ArrayList<Integer> amounts = new ArrayList<>();
        for (String name : names) {
            amounts.add(Action.getAmount(name));
        }

//        makePieChart(title, names, amounts);
    }

}
