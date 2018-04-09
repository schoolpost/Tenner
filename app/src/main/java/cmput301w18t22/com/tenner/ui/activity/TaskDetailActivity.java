package cmput301w18t22.com.tenner.ui.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.Bid;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.helpers.ConstantsHelper;
import cmput301w18t22.com.tenner.helpers.LocalDataHelper;
import cmput301w18t22.com.tenner.helpers.PhotoConverterHelper;
import cmput301w18t22.com.tenner.server.ElasticServer;

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

    protected String newBidAmt = null;
    boolean alert_done = false;

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
                makeBid();
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


    private void makeBid() {

        AlertDialog.Builder alertBuilder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alertBuilder = new AlertDialog.Builder(this);
        } else {
            alertBuilder = new AlertDialog.Builder(this);
        }

        alertBuilder.setTitle("New Bid");
        alertBuilder.setMessage("Enter bid amount:");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        alertBuilder.setView(input);

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                newBidAmt = null;
                dialogInterface.cancel();
            }
        });

        alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String bid_amt = input.getText().toString();
                newBidAmt = bid_amt;
                dialogInterface.cancel();
                Log.i("bid is", bid_amt);
                if (newBidAmt != null) {
                    if (!newBidAmt.equals("")){
                        Bid newBid = new Bid(user, newBidAmt, new Date(), task);
                        ArrayList<Bid> taskBidList = task.getBidList();
                        boolean userHasExistingBid = false;

                        if (task.getBidList().size() > 0) {

                            for (int i = 0; i < task.getBidList().size(); i++) {

                                Log.i("testing", String.valueOf(i));
                                if (taskBidList.get(i).getOwner().getEmail().equals(user.getEmail())) {
                                    taskBidList.set(i, newBid);
                                    userHasExistingBid = true;
                                    break;
                                }
                            }
                        }
                        if (!userHasExistingBid) {
                            taskBidList.add(newBid);
                        }
                        task.setBidList(taskBidList);
                        task.setStatus("Bidded");
                    }
                }

                Log.i("testing", "before post task");
                try {
                    postTask(task);
                } catch (Exception e) {
                    Log.i("Bid Error", e.getMessage());
                }

                Log.i("testing", "after post task");
            }
        });

        alertBuilder.show();


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

    public void postTask(final Task task) throws JSONException {

        RequestParams params = new RequestParams();
        //https://github.com/google/gson
        Gson gson = new Gson();
        String json = gson.toJson(task);

        try {
            params.put("task", json);
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post("editTask", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.has("Success")) {

                        localDataHelper.saveTaskToFile(task);

                    } else if (response.has("Error")) {

                        Toast toast = Toast.makeText(getApplicationContext(), response.get("Error").toString(), Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 16);
                        toast.show();
                    }
                } catch (Exception e) {

                }
            }
        });
    }
}