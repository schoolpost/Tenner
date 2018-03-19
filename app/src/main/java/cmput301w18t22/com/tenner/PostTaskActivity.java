package cmput301w18t22.com.tenner;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class PostTaskActivity extends AppCompatActivity {

    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_task);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_post);
        // set the activity title
        ((TextView) getSupportActionBar().getCustomView().findViewById(R.id.post_action_bar_title)).setText(R.string.title_home);


        // Task Object
        final EditText title = (EditText) findViewById(R.id.editTitle);
        final EditText description = (EditText) findViewById(R.id.editDescription);
        final EditText location = (EditText) findViewById(R.id.editLocation);
        // image

        final Button done = (Button) findViewById(R.id.post_done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titleText = title.getText().toString();
                String descriptionText = description.getText().toString();
                String locationText = location.getText().toString();

                if (done(titleText, descriptionText, locationText)) {

                    // Open TaskActivity with
                    Intent intent = new Intent();
                    intent.setClass(PostTaskActivity.this, TaskActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    // Validate and Save input
    public boolean done(String title, String description, String location) {


        return true;

    }

    private void notify(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }



    // Basic Methods for 5 Main Activity

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(getApplicationContext(), PostTaskActivity.class));
                    return true;
                case R.id.navigation_tasks:
                    startActivity(new Intent(getApplicationContext(), TaskActivity.class));
                    return true;
                case R.id.navigation_post:
                    startActivity(new Intent(getApplicationContext(), PostTaskActivity.class));
                    return true;
                case R.id.navigation_bids:
                    startActivity(new Intent(getApplicationContext(), BidActivity.class));
                    return true;
                case R.id.navigation_profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() { super.onPause(); }

    @Override
    public void onBackPressed() {
        // Disable Back Press
    }
}
