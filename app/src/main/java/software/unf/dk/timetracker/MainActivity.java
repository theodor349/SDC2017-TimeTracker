package software.unf.dk.timetracker;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    /** Varibles**/
    // files
    private final String ACTIONS_FILENAME = "actions.xml";
    private final String CLASSIFICATIONS_FILENAME = "classifications.xml";



    //Integers
    private static Integer notificationtimes=1;
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
    //Boolean
    private static Boolean wantnotification = true;

    public static void setWantnotification(Boolean wantnotification) {
        wantnotification = wantnotification;
    }
    public static void setNotificationtimes(Integer notificationtimes) {
        MainActivity.notificationtimes = notificationtimes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test();

        loadData();
        layoutSetup();
    }

    // Used for testing ONLY!
    private void test(){
        File file = new File(getFilesDir(), CLASSIFICATIONS_FILENAME);
        file.delete();

        file = new File(getFilesDir(), ACTIONS_FILENAME);
        file.delete();
    }

    protected void onResume(){
        super.onResume();
        setSpinner();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
        if(wantnotification){
            new CountDownTimer(notificationtimes*60*1000,1000){

                public void onTick(long millisUntilFinished) {}

                public void onFinish() {
                    createNotification();
                }

            }.start();
        }

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
        answer = (EditText) findViewById(R.id.svar);
        // Dropdown.
        spinner = (Spinner)findViewById(R.id.spinner);
        classificationText = (EditText) findViewById(R.id.classificationText);

        setSpinner();
    }

    private void setSpinner(){
        paths = Classification.mapToStringList(Classification.classificationMap).toArray(new String[0]);

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

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // Runs when Enter is pressed
    public void enter(View view){
        // Creates new instance of an action and adds it to the list of actions.
        String name = answer.getText().toString();
        if (name.equals("")) {
            showToast("Name can't be empty!");
            return;
        }
        answer.setText("");
        Classification classification = Classification.getClassificationByName(classificationString);
        Log.e("Test", classification + "");
        Date date = new Date();
        Action.actionList.add(new Action(name, classification, date));
    }

    public void createClassification(View view){
        String name = classificationText.getText().toString();

        //Safety checks
        if (name.equals("")) {
            showToast("Name can't be empty");
            return;
        }
        if (!Classification.createNew(name)) {
            showToast("Category already exists!");
            return;
        }

        setSpinner();
        classificationText.setText("");
    }

    public void historyActivity(View view){
        Intent hisintent = new Intent(this, HistoryActivity.class);
        startActivity(hisintent);
    }

    public void settingsActivity(View view){
        Intent hissettingpath = new Intent(this, CustomSettings.class);
        startActivity(hissettingpath);
    }

    public void statisticsActivity(View view){
        Intent hisppath = new Intent(this, PieChartView.class);
        startActivity(hisppath);
    }

    public void createNotification (){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("TimeTracker")
                .setContentText("What are you doing");
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(3, mBuilder.build());
    }

}
