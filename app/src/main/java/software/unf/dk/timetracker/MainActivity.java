package software.unf.dk.timetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private final String ACTIONS_FILENAME = "actions.xml";
    private final String CLASSIFICATIONS_FILENAME = "classifications.xml";

    private EditText answer;
    private EditText classificationText;
    private Spinner spinner;
    private TextView question;
    private Button enter;
    private Button addClassificationB;
    private String classificationString;

    private static String[] paths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        layoutSetup();
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
        question = (TextView) findViewById(R.id.question);
        answer = (EditText) findViewById(R.id.svar);
        enter = (Button) findViewById(R.id.buttonSetter);
        // Dropdown.
        spinner = (Spinner)findViewById(R.id.spinner);
        classificationText = (EditText) findViewById(R.id.classificationText);
        addClassificationB = (Button) findViewById(R.id.addClassification);
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
    }

    // Kalder history function i xml
    public void history (View view){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}
