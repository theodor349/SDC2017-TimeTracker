package software.unf.dk.timetracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class CustomSettings extends Activity {

    private EditText cataset;
    private Button enter2;
    private Button bremove;
    private Spinner spinner;
    private EditText classificationEntry;
    private String classificationName;
    private String newName;


    // Reference to files
    private final String ACTIONS_FILENAME = "actions.xml";
    private final String CLASSIFICATIONS_FILENAME = "classifications.xml";

    private static String[] paths;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customsettings);

        cataset = findViewById(R.id.classificationText);
        enter2 = findViewById(R.id.adder);

        bremove = findViewById(R.id.bremove);
        //rename = (EditText) findViewById(R.id.renameTekst);
        layoutSetup();
        getIntent();
    }

    private void layoutSetup() {
        // Dropdown.
        spinner = findViewById(R.id.spinner);
        classificationEntry = findViewById(R.id.classificationText);

        setSpinner();
    }

    private  void setSpinner(){
        paths = Classification.mapToStringList(Classification.classificationMap).toArray(new String[0]);

        // Doing so the Array can be put into the Spinner
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        // Listen to things happens on the Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                classificationName = paths[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void adding(View view){
        String name = classificationEntry.getText().toString();

        if (!Classification.createNew(name)) {
            Toast.makeText(this, "Category already exists!", Toast.LENGTH_LONG).show();
            return;
        }

        setSpinner();
        classificationEntry.setText("");
    }

    public void remove(View view){
        Toast.makeText(this, "Not implemented yet",Toast.LENGTH_LONG).show();
    }

    public void rename(View view){
        // Build dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new category name");

        // Set text input
        final EditText inputText = new EditText(this);
        inputText.setInputType(InputType.TYPE_CLASS_TEXT);
        inputText.setHint(classificationName);
        builder.setView(inputText);

        // Define OK button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                newName = inputText.getText().toString();
                Classification c = Classification.getClassificationByName(classificationName);
                c.setName(newName);
                setSpinner();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

}
