package redquadron.com.jargon;

import android.graphics.Paint;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bolts.Task;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    EditText mTaskInput;
    ListView mListView;
    JargonAdapter mAdapter;
    static List<Jargon> jargons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTaskInput = (EditText) findViewById(R.id.task_input);
        mListView = (ListView) findViewById(R.id.task_list);

        mAdapter = new JargonAdapter(this, new ArrayList<Jargon>());
        mListView.setAdapter(mAdapter);

        ParseObject.registerSubclass(Jargon.class);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Jargon");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        updateData();

        mListView.setOnItemClickListener(this);

    }

    public void updateData(){
        ParseQuery<Jargon> query = ParseQuery.getQuery(Jargon.class);
        query.findInBackground(new FindCallback<Jargon>() {

            @Override
            public void done(List<Jargon> tasks, ParseException error) {
                if(tasks != null){

                    Collections.sort(tasks, new Comparator<Jargon>() {
                        public int compare(Jargon v1, Jargon v2) {
                            return v1.getText().compareTo(v2.getText());
                        }
                    });
                    jargons = tasks;

                    mAdapter.clear();
                    mAdapter.addAll(tasks);
                }
            }
        });
    }

    public static Jargon getJargon(int index)
    {
        return jargons.get(index);

    }



    public void createTask(View v) {
        if (mTaskInput.getText().length() > 0){
            Jargon t = new Jargon();
            t.setDescription(mTaskInput.getText().toString());
            t.setCompleted(false);
            t.saveEventually();
            mTaskInput.setText("");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Jargon jargon = mAdapter.getItem(position);

        Bundle args = new Bundle();
        args.putInt("position", position);
        DialogFragment newFragment = new EditNameDialog();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "TAG");


        jargon.saveEventually();
    }


    public static class EditNameDialog extends DialogFragment {
        Jargon jargon;

        public EditNameDialog() {
            // Empty constructor required for DialogFragment
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Bundle mArgs = getArguments();
            int value = mArgs.getInt("position");

            jargon = getJargon(value);
            View view = inflater.inflate(R.layout.details_dialog, container);
            getDialog().setTitle(jargon.getText());

            ((TextView)view.findViewById(R.id.dialog_description)).setText(jargon.getDescription());
            ((TextView)view.findViewById(R.id.dialog_source)).setText(jargon.getSourceName());

            return view;
        }

        public void goSource(View v){

        }
    }

}
