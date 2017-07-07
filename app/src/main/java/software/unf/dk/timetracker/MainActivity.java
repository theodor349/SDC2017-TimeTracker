package software.unf.dk.timetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.concurrent.ConcurrentHashMap;

public class MainActivity extends AppCompatActivity {

    private TextView question;
    private EditText answer;
    private Spinner spinner;
    private TextView testing;
    private Button enter;
    private TextView testing2;
    private String tempAnswer;

    /**  Counting variables   **/
    private ConcurrentHashMap<String, Integer> counterMap = new ConcurrentHashMap<>();


    private static final String[]paths = {"Calendar Events", "Chores", "Educational", "Entertainment", "Family", "Friends", "Relaxation", "Sports", "Work"};
    // temperory catagory variable
    private String tempCata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = (TextView) findViewById(R.id.question);
        testing = (TextView) findViewById(R.id.testing);
        answer = (EditText) findViewById(R.id.svar);
        spinner = (Spinner)findViewById(R.id.spinner);
        enter = (Button) findViewById(R.id.buttonSetter);
        testing2 = (TextView) findViewById(R.id.testing2);

        counterMap.put("Calendar Events",0);
        counterMap.put("Chores",0);
        counterMap.put("Educationel",0);
        counterMap.put("Entertainment",0);
        counterMap.put("Family",0);
        counterMap.put("Friends",0);
        counterMap.put("Relaxation",0);
        counterMap.put("Sports",0);
        counterMap.put("Work",0);


        // Doing so the Array can be put into the Spinner
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        // Listen to things happens on the Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tempCata = paths[i];
                     //paths[i] string for det valgte tag
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void enter(View view){   /** gets runned when Enter is pressed**/
        tempAnswer = answer.getText().toString();
        answer.setText("");
        testing2.setText(tempAnswer);
        counterMap.put(tempCata, counterMap.get(tempCata) + 1);
        testing.setText("" + counterMap.get(tempCata));
    }
    //kalder history function i xml
    public void history (View view){
        Intent hisintent = new Intent( this, History.class);
        startActivity(hisintent);

    }

    /*public void onStart(Bundle savedInstanceState) {

        if(savedInstanceState != null)
        {
            tempAnswer = savedInstanceState.getString("MYANSWER");

        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("MYANSWER", tempAnswer);


    }
    */




}
