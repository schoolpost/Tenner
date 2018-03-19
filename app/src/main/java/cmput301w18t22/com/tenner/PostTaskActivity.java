package cmput301w18t22.com.tenner;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PostTaskActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_tasks:
                    return true;
                case R.id.navigation_post:
                    return true;
                case R.id.navigation_bids:
                    return true;
                case R.id.navigation_profile:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_task);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_layout);
        // set the activity title
        ((TextView) getSupportActionBar().getCustomView().findViewById(R.id.custom_action_bar_title)).setText(R.string.title_post);

        // Task Object
        final EditText title = (EditText) findViewById(R.id.editTitle);
        final EditText description = (EditText) findViewById(R.id.editDescription);
        final EditText location = (EditText) findViewById(R.id.editLocation);
        // image

        /*signUp.setOnClickListener(new View.OnClickListener() {
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
        });*/

    }

/*    // Action Bar Save Button
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_addtask, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.post_save:
                // Save Action
                // done();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    // Validate and Save input
    public boolean done(String title, String description, String location) {


        return true;

    }

    private void notify(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
