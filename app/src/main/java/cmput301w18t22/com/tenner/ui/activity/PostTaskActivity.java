package cmput301w18t22.com.tenner.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.Location;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.server.ElasticServer;
import cmput301w18t22.com.tenner.utils.LocalDataHandler;
import cmput301w18t22.com.tenner.utils.TaskChecker;

public class PostTaskActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDescription;
    private EditText etLocation;
    private TextView save;
    private Button setLocationButton;
    private FusedLocationProviderClient mFusedLocationClient;
    private LatLng currentloc;
    private LocalDataHandler localDataHandler;

    static final int GET_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localDataHandler = new LocalDataHandler(this);

        setContentView(R.layout.activity_post_task);

        setLocationButton = (Button) findViewById(R.id.location_button);
        setLocationButton.setEnabled(false);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getcurrloc();

        // Change ActionBar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setElevation(3);
        getSupportActionBar().setCustomView(R.layout.toolbar_post_task);
        save = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_edit_save);

        etTitle = (EditText) findViewById(R.id.editTitle);
        etDescription = (EditText) findViewById(R.id.editDescription);
        etLocation = (EditText) findViewById(R.id.editLocation);
        etLocation.setEnabled(false);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryPost();
            }
        });

        setLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent();
                mapIntent.putExtra("lat", currentloc.latitude);
                mapIntent.putExtra("lng", currentloc.longitude);
                mapIntent.setClass(PostTaskActivity.this, SetLocationActivity.class);
                //TODO: Get result of this activity
                startActivityForResult(mapIntent, GET_LOCATION);
            }
        });
    }

    private void tryPost() {
        String title = String.valueOf(etTitle.getText()).trim();
        String description = String.valueOf(etDescription.getText()).trim();
        String address = String.valueOf(etLocation.getText()).trim();

        if (check(title, description, address)) {
            //TODO: Current User and Elastic Search

            User user = localDataHandler.loadUserFromFile();
            User currentUser = user;
            Location newLocation = new Location(0.0f, 0.0f, address);
            Task task = new Task(title, description, newLocation, new Date(), currentUser);

            try {
                postTask(task);
            } catch (Exception e) {

            }

        }

    }

    private boolean check(String title, String description, String address) {
        TaskChecker ch = new TaskChecker();
        if (TextUtils.isEmpty(title)) {
            etTitle.setError("title is empty");
            return false;
        }

        if (!ch.checkTitle(title)) {
            etTitle.setError("invalid title");
            return false;
        }

        if (!ch.checkDuplicateTitle(title)) {
            etTitle.setError("duplicate title");
            return false;
        }

        if (TextUtils.isEmpty(description)) {
            etDescription.setError("description is empty");
            return false;
        }

        if (!ch.checkDescription(description)) {
            etDescription.setError("invalid description");
            return false;
        }

        if (!ch.checkAddress(address)) {
            etLocation.setError("invalid address");
            return false;
        }
        return true;
    }

    public void postTask(Task task) throws JSONException {

        RequestParams params = new RequestParams();
        //https://github.com/google/gson
        Gson gson = new Gson();
        String json = gson.toJson(task);

        try {
            params.put("task", json);
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post("addTask", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.has("Success")) {

                        Intent intent = new Intent();
                        intent.setClass(PostTaskActivity.this, TaskDetailActivity.class);
                        startActivity(intent);
                        finish();


                    } else if (response.has("Error")) {

                    }
                } catch (Exception e) {

                }
            }
        });
    }

    public void getcurrloc() throws SecurityException {
        mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<android.location.Location>() {
            @Override
            public void onSuccess(android.location.Location location) {

                if (location != null) {

                    currentloc = new LatLng(location.getLatitude(), location.getLongitude());
                    setLocationButton.setEnabled(true);

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 20) {
            etLocation.setText(data.getStringExtra("location"));
        }

    }
}
