package cmput301w18t22.com.tenner.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.Bid;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.helpers.InternetStatusHelper;
import cmput301w18t22.com.tenner.helpers.LocalDataHelper;
import cmput301w18t22.com.tenner.server.ElasticServer;
import cmput301w18t22.com.tenner.ui.adapter.TaskAdapter;
import cmput301w18t22.com.tenner.ui.adapter.TaskBidAdapter;

public class BidHistoryActivity extends AppCompatActivity {

    private ArrayList<Bid> bidList;
    private TaskBidAdapter myAdapter;
    private SwipeMenuListView displayList;
    private Task task;
    private LocalDataHelper localDataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_history);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setElevation(3);
        getSupportActionBar().setCustomView(R.layout.toolbar_home);
        TextView title = getSupportActionBar().getCustomView().findViewById(R.id.home_action_bar_title);
        title.setText("Bid History");

        localDataHelper = new LocalDataHelper(this);

        bidList = new ArrayList<Bid>();
        displayList = (SwipeMenuListView) findViewById(R.id.bidsList);

        loadData();
        bindData();
    }

    private void loadData() {
        // Server Call or Local Data
        task = localDataHelper.getTaskFromFile();
        bidList = task.getBidList();

    }

    private void bindData() { // Attach to listview

        myAdapter = new TaskBidAdapter(getApplicationContext(), bidList);
        displayList.setAdapter(myAdapter);

        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        float dp = 80f;
        float fpixels = metrics.density * dp;
        final int pixels = (int) (fpixels + 0.5f);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(pixels);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete_black_24dp);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        displayList.setMenuCreator(creator);

        displayList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                switch (index) {
                    case 0:
                        break;
                }
                return false;
            }
        });

        displayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent profileViewIntent = new Intent();
                profileViewIntent.putExtra("bidIndex", i);
                profileViewIntent.setClass(getApplicationContext(), ViewProfileActivity.class);
                startActivity(profileViewIntent);
            }
        });
    }
}
