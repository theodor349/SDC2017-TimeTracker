package software.unf.dk.timetracker;

import android.app.Activity;
import android.content.Context;
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

    @Override
    protected void onResume() {
        super.onResume();
        layoutSetup();
    }

    private void layoutSetup() {
        actionListView = (ListView) findViewById(R.id.actionList);

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
                String itemValue = (String) actionListView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Position :"+position+"  ListItem : " +itemValue , Toast.LENGTH_LONG).show();
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
        View rowView = inflater.inflate(R.layout.history_action_row, parent, false);
        TextView nameText = (TextView) rowView.findViewById(R.id.nameText);
        TextView timeText = (TextView) rowView.findViewById(R.id.timeText);
        TextView dateText = (TextView) rowView.findViewById(R.id.dateText);
        TextView classificationText = (TextView) rowView.findViewById(R.id.classificationText);

        DateFormat dateFormat = new SimpleDateFormat("EEE, dd/MM yyyy", Locale.ENGLISH);
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        nameText.setText(a.getName());
        classificationText.setText(a.getClassification().getName());
        timeText.setText(timeFormat.format(a.getDate()));
        dateText.setText(dateFormat.format(a.getDate()));

        return rowView;
    }

}