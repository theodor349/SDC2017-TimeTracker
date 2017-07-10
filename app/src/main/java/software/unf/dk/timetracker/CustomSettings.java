package software.unf.dk.timetracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class CustomSettings extends Activity {
    private Spinner spinner;
    private EditText classificationEntry;
    private String classificationName;
    private String newName;

    private static String[] spinnerStrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customsettings);
        layoutSetup();
    }

    private void layoutSetup() {
        // Dropdown.
        spinner = findViewById(R.id.spinner);
        classificationEntry = findViewById(R.id.classificationText);

        setSpinner();
    }

    private void setSpinner(){
        spinnerStrings = Classification.mapToStringList(Classification.classificationMap).toArray(new String[0]);

        // Doing so the Array can be put into the Spinner
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerStrings);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        // Listen to things happens on the Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                classificationName = spinnerStrings[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void adding(View view){
        String name = classificationEntry.getText().toString();

        if (!Classification.createNew(name)) {
            Toast.makeText(this, "Category already exists!", Toast.LENGTH_LONG).show();
            setSpinner();
            return;
        }

        setSpinner();
        classificationEntry.setText("");
    }

    public void remove(View view){
        Classification.getClassificationByName(classificationName).setVisible(false);
        setSpinner();
    }

    public void rename(View view){
        // Build dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new category name");

        // Set text input
        final EditText inputText = new EditText(this);
        inputText.setInputType(InputType.TYPE_CLASS_TEXT);
        inputText.setText(classificationName);
        inputText.setSelectAllOnFocus(true);
        builder.setView(inputText);

        // Define OK button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newName = inputText.getText().toString();
                if (newName.equals("")) {
                    showToast("Name can't be empty");
                }
                if (Classification.getClassificationByName(newName) != null) {
                    showToast("Name must be unique");
                    return;
                }
                dialog.dismiss();
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
