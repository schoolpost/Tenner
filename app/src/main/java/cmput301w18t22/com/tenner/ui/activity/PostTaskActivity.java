package cmput301w18t22.com.tenner.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.Location;
import cmput301w18t22.com.tenner.utils.TaskChecker;

public class PostTaskActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etDescription;
    private EditText etLocation;
    private TextView save;
    private Button setLocationButton;

    static final int GET_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_task);

        // Change ActionBar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setElevation(3);
        getSupportActionBar().setCustomView(R.layout.toolbar_post_task);
        save = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_edit_save);

        etTitle = (EditText) findViewById(R.id.editTitle);
        etDescription = (EditText) findViewById(R.id.editDescription);
        etLocation = (EditText) findViewById(R.id.editLocation);

        setLocationButton = (Button) findViewById(R.id.location_button);

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
            //User currentUser = ...
            Location newLocation = new Location(0.0f, 0.0f, address);
            //Task task = new Task(title, description, newLocation, new Date(), currentUser);

            // Send task to elastic search

            // View task details activity
            Intent intent = new Intent();
            intent.setClass(PostTaskActivity.this, TaskDetailActivity.class);
            startActivity(intent);
            finish();
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
}
