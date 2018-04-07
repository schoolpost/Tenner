package cmput301w18t22.com.tenner.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.helpers.ConstantsHelper;
import cmput301w18t22.com.tenner.helpers.LocalDataHelper;

public class TaskDetailActivity extends AppCompatActivity {

    private String task_title;
    private String task_lowest;
    private String task_location;
    private String task_description;
    private String task_requester;

    private TextView title;
    private TextView lowest;
    private TextView location;
    private TextView description;
    private TextView requester;
    private LocalDataHelper localDataHelper;
    private Task task;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        localDataHelper = new LocalDataHelper(this);
        task = localDataHelper.getTaskFromFile();

        intent = getIntent();

        title = (TextView) findViewById(R.id.t_title);
        lowest = (TextView) findViewById(R.id.bid_text);
        location = (TextView) findViewById(R.id.location);
        description = (TextView) findViewById(R.id.desc);
        requester = (TextView) findViewById(R.id.task_owner);

        title.setText(task.getTitle());
        lowest.setText("");
        location.setText(task.getLocation().getAddress());
        description.setText(task.getDescription());
        requester.setText(task.getRequester().toProfileName());
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (intent.getBooleanExtra("added", false)) {
            setResult(ConstantsHelper.ADD_TASK_RESULT, intent);
            finish();
        } else {
            finish();
        }

    }
}