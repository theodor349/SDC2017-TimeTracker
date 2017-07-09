package software.unf.dk.timetracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;



public class CustomSettings extends Activity {

    private EditText cataset;
    private Button enter2;
    private Button bremove;
    private Spinner spinner;
    private EditText classificationText;
    private EditText rename;
    private String tempCata;
    private String newName;


    // Reference to files
    private final String ACTIONS_FILENAME = "actions.xml";
    private final String CLASSIFICATIONS_FILENAME = "classifications.xml";

    private static String[] paths = {"Calendar Events", "Chores", "Educational", "Entertainment", "Family", "Friends", "Relaxation", "Sports", "Work"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customsettings);

        cataset = findViewById(R.id.classificationText);
        enter2 = (Button) findViewById(R.id.adder);

        bremove = (Button) findViewById(R.id.bremove);
        //rename = (EditText) findViewById(R.id.renameTekst);
        layoutSetup();
        getIntent();
    }

    private void layoutSetup() {
        // Dropdown.
        spinner = (Spinner)findViewById(R.id.spinner);
        classificationText = (EditText) findViewById(R.id.classificationText);

        setSpinner();

    }

    private  void setSpinner(){
        paths = Classification.mapToStringList(Classification.classificationMap).toArray(new String[0]);

        // Doing so the Array can be put into the Spinner
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        // Listen to things happens on the Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                  tempCata = paths[i];

                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> adapterView) {

                                              }
                                          }
        );
    }

    public void adding(View view){
        String name = classificationText.getText().toString();

        if (!Classification.createNew(name)) {
            Toast.makeText(this, "Category already exists!", Toast.LENGTH_LONG).show();
            return;
        }

        setSpinner();
        classificationText.setText("");
    }

    public void remove(View view){
        Toast.makeText(this, "Not implemented yet",Toast.LENGTH_LONG).show();
    }

    public void rename(View view){
        /*// Build dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new category name");

        // Set text input
        final EditText inputText = new EditText(this);
        inputText.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(inputText);

        // Define OK button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newName = inputText.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Get classification and remove it from the map.
        Classification c = Classification.classificationMap.remove(tempCata);
        // Set field member name value
        c.setName(newName);
        // Add to map with new key
        Classification.classificationMap.put(newName, c);
        // Update spinner contents
        setSpinner();
        rename.setText("");*/
        Toast.makeText(this, "Not implemented yet", Toast.LENGTH_LONG).show();
    }

}
