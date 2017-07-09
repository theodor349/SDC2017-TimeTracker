package software.unf.dk.timetracker;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

//import com.github.mikephil.charting.charts.LineChart;
//import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static software.unf.dk.timetracker.R.id.actionList;
import static software.unf.dk.timetracker.R.id.piespinner;

public class PieChartView extends AppCompatActivity {

    private com.github.mikephil.charting.charts.PieChart pieChart;


    //Varibles
    // Buttons
    private Button button;
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
        setContentView(R.layout.activity_piechart);
        setReference();
    }
    private void setReference(){
        button =(Button)  findViewById(R.id.button);
        pieChart = (com.github.mikephil.charting.charts.PieChart) findViewById(R.id.pie);
        setSpinner();
        pieChooser = (Spinner) findViewById(piespinner);
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
    /** Makes a Pie Chart**/
    private void makePieChart(String title, ArrayList<String> names, ArrayList<Integer> amounts) {
        PieDataSet dataSet = new PieDataSet(createEntries(names, amounts), title);
        dataSet.setColors(new int[] { R.color.neonpink, R.color.green, R.color.blue, R.color.lightgreen, R.color.red, R.color.yellow, R.color.lightblue, R.color.magenza, R.color.orange, R.color.turqoise, R.color.pumpkin, R.color.palepink, R.color.svump, R.color.darkpurple }, getApplicationContext());
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh

    }
    /** Creates Entries to Pie Chart**/
    private List<PieEntry> createEntries(ArrayList<String> names, ArrayList<Integer> amounts) {
        List<PieEntry> result = new ArrayList<>();

        if(amounts.size() != names.size()) {
            throw new IllegalArgumentException("List of either amounts or names is not equal to each other and can there fore not be handled");
        }

        for (int i = 0; i < names.size(); i++) {
            PieEntry entry = new PieEntry(amounts.get(i), names.get(i));
            result.add(entry);
        }

        return result;
    }

    private void loadData() {
        ClassificationIOHandler classificationIOHandler = new ClassificationIOHandler(new File(getFilesDir(), CLASSIFICATIONS_FILENAME));
        Classification.classificationMap = Classification.listToMap(classificationIOHandler.parseClassifications());
        ActionIOHandler actionIOHandler = new ActionIOHandler(new File(getFilesDir(), ACTIONS_FILENAME));
        Action.actionList = actionIOHandler.parseActions();
    }

    public void piechartInput(View view){
        String title = classificationString;
        ArrayList<String> names = Action.getNames(classificationString);
        ArrayList<Integer> amounts = new ArrayList<>();
        for (String name : names) {
            amounts.add(Action.getAmount(name));
        }

        makePieChart(title, names, amounts);
    }

}
