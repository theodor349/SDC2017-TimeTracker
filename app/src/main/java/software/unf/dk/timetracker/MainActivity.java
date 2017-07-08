package software.unf.dk.timetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    /** Varibles**/
    // files
    private final String ACTIONS_FILENAME = "actions.xml";
    private final String CLASSIFICATIONS_FILENAME = "classifications.xml";

    // Input text boxes
    private EditText answer;
    private EditText classificationText;
    // Spinner
    private Spinner spinner;
    // Buttons
    private Button enter;
    private Button addClassificationB;
    // Strings
    private String classificationString;
    // String Array
    private static String[] paths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        layoutSetup();
    }

    protected void onResume(){
        super.onResume();
        setSpinner();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }

    /**
     * Read Classification and Action data from XML files
     */
    private void loadData() {
        ClassificationIOHandler classificationIOHandler = new ClassificationIOHandler(new File(getFilesDir(), CLASSIFICATIONS_FILENAME));
        Classification.classificationMap = Classification.listToMap(classificationIOHandler.parseClassifications());
        ActionIOHandler actionIOHandler = new ActionIOHandler(new File(getFilesDir(), ACTIONS_FILENAME));
        Action.actionList = actionIOHandler.parseActions();

        // Testing.
        Classification c = Classification.classificationMap.get("theodor");
        if(c == null)
            Log.e("Test", "HI I DO NOT EXIST");
        else
            Log.e("Test", "HI I DO EXIST " );

        Log.e("Test", c + " HAVE THIS LIST: " + c.getActions());

        ArrayList<Action> as = c.getActions();
        if(as == null)
            Log.e("Test", "THE LIST DO NOT EXIST");
        else{
            Log.e("Test", "THE LIST EXIST AND IS: " + as.size() + " LONG");
        for (Action a : as) {
            Log.e("Test", a.getName());
            }
        }

        Log.e("Test", "Contains 'theodor' = " + Classification.classificationMap.containsKey("theodor"));
    }

    private void saveData() {
        ClassificationIOHandler classificationIOHandler = new ClassificationIOHandler(new File(getFilesDir(), CLASSIFICATIONS_FILENAME));
        classificationIOHandler.writeClassifications(Classification.mapToList(Classification.classificationMap));
        ActionIOHandler actionIOHandler = new ActionIOHandler(new File(getFilesDir(), ACTIONS_FILENAME));
        actionIOHandler.writeActions(Action.actionList);
    }

    /**
     * Assign layout variables and initialize elements
     */
    private void layoutSetup() {
        // Actions.
        answer = (EditText) findViewById(R.id.svar);
        // Dropdown.
        spinner = (Spinner)findViewById(R.id.spinner);
        classificationText = (EditText) findViewById(R.id.classificationText);

        setSpinner();
    }

    private void setSpinner(){
        paths = (String[]) Classification.mapToStringList(Classification.classificationMap).toArray(new String[0]);

        // Doing so the Array can be put into the Spinner
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        // Listen to things happens on the Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    // Runs when Enter is pressed
    public void enter(View view){
        // Creates new instance of an action and adds it to the list of actions.
        String name = answer.getText().toString();
        answer.setText("");
        Classification classification = Classification.classificationMap.get(classificationString);
        Date date = new Date();
        Action.actionList.add(new Action(name, classification, date));
    }

    public void createClassification(View view){
        String name = classificationText.getText().toString();
        if(Classification.classificationMap.containsKey(name)){
            // This classification exist.
            Toast.makeText(this, "Already exists",
                    Toast.LENGTH_LONG).show();
            return;
        }
        // Does not exist.
        Classification.classificationMap.put(name, new Classification(name));

        setSpinner();
        classificationText.setText("");
    }

    // Kalder history function i xml
    public void historya (View view){
        Intent hisintent = new Intent(this, HistoryActivity.class);
        startActivity(hisintent);
    }

    public void settingpath(View view){
        Intent hissettingpath = new Intent(this, CustomSettings.class);
        startActivity(hissettingpath);
    }

    public void piepath(View view){
        Intent hisppath = new Intent(this, PieChartView.class);
        startActivity(hisppath);
    }
}
