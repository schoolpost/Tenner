package cmput301w18t22.com.tenner.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.helpers.ConstantsHelper;

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

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        intent = getIntent();

        title = (TextView) findViewById(R.id.t_title);
        lowest = (TextView) findViewById(R.id.bid_text);
        location = (TextView) findViewById(R.id.location);
        description = (TextView) findViewById(R.id.desc);
        requester = (TextView) findViewById(R.id.task_owner);

        title.setText(task_title);
        lowest.setText(task_lowest);
        location.setText(task_location);
        description.setText(task_description);
        requester.setText(task_requester);
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