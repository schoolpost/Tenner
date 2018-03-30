package cmput301w18t22.com.tenner.ui.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.math.BigDecimal;

import cmput301w18t22.com.tenner.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        //get task from server here.
        //task_title = server_info.task_title
        //task_lowest = server_info.task_lowestBid.toString();
        //task_location = server_info.task_location.getLocation();
        //task_description = server_info.task_description
        //task_requester = server_info.task_requester

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
    //It should simple to set most of this up, just need to know where the button sends us.
}