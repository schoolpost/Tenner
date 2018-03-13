package cmput301w18t22.com.tenner;

import android.app.ActionBar;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class PostTaskActivity extends AppCompatActivity {


    // Nav Bar
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_tasks:
                    mTextMessage.setText("Tasks");
                    return true;
                case R.id.navigation_post:
                    mTextMessage.setText("Posts");
                    return true;
                case R.id.navigation_bids:
                    mTextMessage.setText("Bids");
                    return true;
                case R.id.navigation_profile:
                    mTextMessage.setText("Profile");
                    return true;
            }
            return false;
        }
    };

    // Nav Bar End
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Action Bar Save Button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaater = getMenuInflater();
        inflaater.inflate(R.menu.menu_addtask, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.post_save:
                // Save Action
                done();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void done() {
        // Save Task
    }

}
