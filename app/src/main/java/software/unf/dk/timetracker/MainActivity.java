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

public class MainActivity extends AppCompatActivity {

    private TextView question;
    private EditText answer;
    private Spinner spinner;
    private TextView testing;
    private Button enter;
    private TextView testing2;
    private String tempAnswer;

    /**  Counting variables   **/
    private int catCalendarEvents = 0;
    private int catChoresCount = 0;
    private int catEducational = 0;
    private int catEntertainment = 0;
    private int catFamily = 0;
    private int catFriends = 0;
    private int catRelaxation = 0;
    private int catSports = 0;
    private int catWork = 0;

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


    public void submit(View view){   /** gets runned when Enter is pressed**/
        tempAnswer = answer.getText().toString();
        answer.setText("");
        testing2.setText(tempAnswer);
        // call the function to save it all
        if(tempCata == "Calender Events"){
            catCalendarEvents++;
            testing.setText(""+catCalendarEvents);}
        else if(tempCata == "Chores"){
            catChoresCount++;
            testing.setText(""+catChoresCount);
        }
        else if(tempCata == "Educational"){
            catEducational++;
            testing.setText(""+catEducational);
        }
        else if(tempCata == "Entertainment"){
            catEntertainment++;
            testing.setText(""+catEntertainment);
        }
        else if(tempCata == "Family"){
            catFamily++;
            testing.setText(""+catFamily);
        }
        else if(tempCata == "Friends"){
            catFriends++;
            testing.setText(""+catFriends);
        }
        else if(tempCata == "Relaxation"){
            catRelaxation++;
            testing.setText(""+catRelaxation);
        }
        else if(tempCata == "Sports"){
            catSports++;
            testing.setText(""+catSports);
        }
        else if(tempCata == "Work"){
            catWork++;
            testing.setText(""+catWork);
        }
    }

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
