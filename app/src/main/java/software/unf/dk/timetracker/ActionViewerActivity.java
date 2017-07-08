package software.unf.dk.timetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

class ActionViewerActivity extends Activity {
    private TextView actionText;
    private TextView timeText;
    private TextView classificationText;
    public static final String ACTION_EXTRA = "ACTION";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_view);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);

        actionText = findViewById(R.id.actionName);
        classificationText = findViewById(R.id.classificationText);
        timeText = findViewById(R.id.timeText);

        Intent intent = getIntent();
        Action action = (Action) intent.getExtras().get("ACTION");

        actionText.setText(action.getName());
        classificationText.setText(action.getClassification().getName());
        timeText.setText(action.getDate().toString());
    }
}
