package cmput301w18t22.com.tenner.ui.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.Bid;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.helpers.ConstantsHelper;
import cmput301w18t22.com.tenner.helpers.LocalDataHelper;
import cmput301w18t22.com.tenner.helpers.PhotoConverterHelper;

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
    private ImageView imageView;
    private User user;
    private TextView toolbarEdit;
    private Bid bidToPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        localDataHelper = new LocalDataHelper(this);
        task = localDataHelper.getTaskFromFile();
        user = localDataHelper.loadUserFromFile();
        intent = getIntent();
        PhotoConverterHelper photoConverterHelper = new PhotoConverterHelper();

        Button bid_button = (Button) findViewById(R.id.bid_button);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setElevation(3);
        getSupportActionBar().setCustomView(R.layout.toolbar_task_detail);

        if (user.getEmail().equals(task.getRequester().getEmail()) && task.getStatus().equals("requested")) {
            getSupportActionBar().setCustomView(R.layout.toolbar_task_edit);
            toolbarEdit = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_edit_task);
            toolbarEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent editIntent = new Intent();
                    editIntent.setClass(getApplicationContext(), EditTaskActivity.class);
                    startActivity(editIntent);
                }
            });

            bid_button.setEnabled(false);
            bid_button.setVisibility(View.GONE);

        }


        TextView title = getSupportActionBar().getCustomView().findViewById(R.id.home_action_bar_title);
        lowest = (TextView) findViewById(R.id.bid_amt);
        location = (TextView) findViewById(R.id.location);
        description = (TextView) findViewById(R.id.desc);
        requester = (TextView) findViewById(R.id.task_owner);
        imageView = (ImageView) findViewById(R.id.imageView5);


        bid_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        lowest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bidIntent = new Intent();
                bidIntent.setClass(getApplicationContext(), BidHistoryActivity.class);
                startActivity(bidIntent);
            }
        });

        requester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileViewIntent = new Intent();
                profileViewIntent.setClass(getApplicationContext(), ViewProfileActivity.class);
                startActivity(profileViewIntent);

            }
        });

        String taskTitle = task.getTitle().substring(0, 1).toUpperCase() + task.getTitle().substring(1);
        title.setText(taskTitle);
        if (task.getBidList().size() == 0) {
            lowest.setText("$0.00");
        } else {
            lowest.setText(task.getLowestBid().toString());
        }

        location.setText(task.getLocation().getAddress());
        description.setText(task.getDescription());
        requester.setText(task.getRequester().toProfileName());

        Bitmap bm;
        if (task.getPhotos().size() != 0) {
            bm = photoConverterHelper.convertStringToBM(task.getPhotos().get(0));
            imageView.setImageBitmap(bm);
        }


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