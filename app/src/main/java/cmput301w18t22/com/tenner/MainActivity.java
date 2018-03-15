/*
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */

package cmput301w18t22.com.tenner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setupTabs();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                selectedFragment = HomeFragment.newInstance();
                                break;
                            case R.id.navigation_tasks:
                                selectedFragment = TaskFragment.newInstance();
                                break;
                            case R.id.navigation_post:
                                selectedFragment = PostTaskFragment.newInstance();
                                break;
                            case R.id.navigation_bids:
                                selectedFragment = BidFragment.newInstance();
                                break;
                            case R.id.navigation_profile:
                                selectedFragment = ProfileFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        transaction.commit();

    }

    // Set the Action bar title for each Fragment
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    // Tabs
    /*private void setupTabs() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);

        Tab tab1 = actionBar
                .newTab()
                .setText("First")
                .setIcon(R.drawable.ic_account_box_black_24dp)
                .setTabListener(new SupportFragmentTabListener<ItemOneFragment>(R.id.frame_layout, this,
                        "first", ItemOneFragment.class));

        actionBar.addTab(tab1);
        actionBar.selectTab(tab1);

        Tab tab2 = actionBar
                .newTab()
                .setText("Second")
                .setIcon(R.drawable.ic_account_circle_black_24dp)
                .setTabListener(new SupportFragmentTabListener<ItemTwoFragment>(R.id.frame_layout, this,
                        "second", ItemTwoFragment.class));
        actionBar.addTab(tab2);
    }*/
}
