package software.unf.dk.timetracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class HistoryActivity extends Activity{
    private ListView actionListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_view);
        layoutSetup();
    }

    private void layoutSetup() {
        actionListView = (ListView) findViewById(R.id.actionList);
        final Context context = this;

        Action[] values = Action.actionList.toArray(new Action[0]);
        String logString = "";
        for (Action a : values) {
            logString += a.getName() + " ";
        }
        Log.e("TimeTracker", logString);

        ArrayAdapter<Action> adapter = new ActionArrayAdapter(this, values);
        actionListView.setAdapter(adapter);

        actionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Action action = (Action) actionListView.getItemAtPosition(position);
                Intent intent = new Intent(context, ActionViewerActivity.class);
                intent.putExtra(ActionViewerActivity.ACTION_EXTRA, action);
            }
        });
    }
}

class ActionArrayAdapter extends ArrayAdapter<Action> {
    private final Context context;
    private final Action[] values;

    public ActionArrayAdapter(Context context, Action[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Action a = values[position];

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.history_action_row, parent, false);
        TextView nameText = (TextView) convertView.findViewById(R.id.nameText);
        TextView timeText = (TextView) convertView.findViewById(R.id.timeText);
        TextView dateText = (TextView) convertView.findViewById(R.id.dateText);
        TextView classificationText = (TextView) convertView.findViewById(R.id.classificationText);

        DateFormat dateFormat = new SimpleDateFormat("EEE, dd/MM yyyy", Locale.ENGLISH);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        nameText.setText(a.getName());
        classificationText.setText(a.getClassification().getName());
        timeText.setText(timeFormat.format(a.getDate()));
        dateText.setText(dateFormat.format(a.getDate()));

        return convertView;
    }

}