package software.unf.dk.timetracker;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;


public class CustomSettings extends Activity {

    private EditText cataset;
    private Button enter2;
    private Button bremove;
    private Spinner spinner;
    private EditText classificationText;
    private EditText rename;
    private EditText notificationtimeText;
    private String tempCata;
    private String newName;
    private ToggleButton togglebutton;


    // Reference to files
    private final String ACTIONS_FILENAME = "actions.xml";
    private final String CLASSIFICATIONS_FILENAME = "classifications.xml";

    private static String[] paths = {"Calendar Events", "Chores", "Educational", "Entertainment", "Family", "Friends", "Relaxation", "Sports", "Work"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customsettings);

        cataset = (EditText) findViewById(R.id.classificationText);
        enter2 = (Button) findViewById(R.id.adder);

        bremove = (Button) findViewById(R.id.bremove);
        rename = (EditText) findViewById(R.id.renameTekst);
        togglebutton = (ToggleButton) findViewById(R.id.toggleButton);
        notificationtimeText = (EditText) findViewById(R.id.editnotificationtime);

        layoutSetup();



    }

    private void layoutSetup() {
        // Dropdown.
        spinner = (Spinner)findViewById(R.id.spinner);
        classificationText = (EditText) findViewById(R.id.classificationText);

        setSpinner();

    }

    private  void setSpinner(){
        paths = (String[]) Classification.mapToStringList(Classification.classificationMap).toArray(new String[0]);

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

    public void remove(View view){
        Toast.makeText(this, "We would have deleted it but we cant",Toast.LENGTH_LONG).show();
    }

    public void rename(View view){
        newName = rename.getText().toString();

        // Get classification and remove it from the map.
        Classification c = Classification.classificationMap.remove(tempCata);
        // Set field member name value
        c.setName(newName);
        // Add to map with new key
        Classification.classificationMap.put(newName, c);
        // Update spinner contents
        setSpinner();
        rename.setText("");

    }
    public void changenotificationtime(View view){
        int temp;
        try {
            temp = Integer.parseInt(notificationtimeText.getText().toString());
        } catch (Exception e){
            Log.e("Change Time", e.toString());
            return;
        }
        MainActivity.setNotificationtimes(temp);
        notificationtimeText.setText("");

    }

    public void setTogglebutton(){
        togglebutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.setWantnotification(true);
                } else {
                    MainActivity.setWantnotification(false);
                }
            }

        });

    }
}
