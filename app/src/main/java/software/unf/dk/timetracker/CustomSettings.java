package software.unf.dk.timetracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by deltager on 07-07-17.
 */

public class CustomSettings extends Activity {

    private EditText cataset;
    private Button enter2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customsettings);

        cataset = (EditText) findViewById(R.id.cataset);
        enter2 = (Button) findViewById(R.id.enter2);

    }

    public void setter (View view) { //when enter is pressed in catagory settings





    }
}
