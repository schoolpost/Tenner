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

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.helpers.InternetStatusHelper;
import cmput301w18t22.com.tenner.helpers.LocalDataHelper;
import cmput301w18t22.com.tenner.server.ElasticServer;
import cmput301w18t22.com.tenner.ui.adapter.TaskAdapter;

public class SearchActivity extends AppCompatActivity {

    private ImageButton filter;
    private ArrayList<Task> taskList;
    private TaskAdapter myAdapter;
    private SwipeMenuListView displayList;
    private int tab;
    private User user;
    private LocalDataHelper localDataHelper;
    private InternetStatusHelper internetStatusHelper = new InternetStatusHelper();
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private ProgressBar progressBar;
    private SearchActivity.WeakRunnable mRunnable = new SearchActivity.WeakRunnable(this);
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setElevation(3);
        getSupportActionBar().setCustomView(R.layout.toolbar_search);
        TextView title = getSupportActionBar().getCustomView().findViewById(R.id.home_action_bar_title);
        title.setText("Search");

        localDataHelper = new LocalDataHelper(this);

        taskList = new ArrayList<Task>();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        displayList = (SwipeMenuListView) findViewById(R.id.tasksList);
        setFilter();

        Intent intent = getIntent();
        query = intent.getStringExtra("query");

        if (savedInstanceState == null) {
            loadData();
        } else {
            bindData();
        }


    }

    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(getApplicationContext(), v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.task_filters, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });
    }

    public void setFilter() {
        filter = (ImageButton) getSupportActionBar().getCustomView().findViewById(R.id.taskFilterButton);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });
    }

    private void loadData() {
        // Server Call
        showProgressBar(true);
        user = localDataHelper.loadUserFromFile();
        try {
            //postTaskSearch(query);
            getTasks();
        } catch (JSONException e) {

        }

    }

    public void postTaskSearch(String query) throws JSONException {

        RequestParams params = new RequestParams();

        try {
            params.put("query", query);
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post("searchTasks", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    Gson gson = new GsonBuilder().create();
                    taskList = new ArrayList<Task>();

                    for (int i = 0; i < response.length(); i++) {
                        taskList.add(gson.fromJson(response.get(i).toString(), Task.class));
                    }
                    int index = 0;
                    for (Task t : taskList) {
                        index++;
                        Log.i("item" + index, t.getTitle());
                    }


                } catch (Exception e) {

                }

                sHandler.postDelayed(mRunnable, 500);
            }
        });
    }


    private void bindData() { // Attach to listview
        Log.i("debug", "Binding");

        myAdapter = new TaskAdapter(getApplicationContext(), taskList);
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
                openTaskDetails(taskList.get(i));
            }
        });
    }

    public void getTasks() throws JSONException {

        final String url = "getRequestedTasks";

        RequestParams params = new RequestParams();

        try {
            params.put("user", user.getEmail());
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post(url, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    Gson gson = new GsonBuilder().create();
                    taskList = new ArrayList<Task>();

                    for (int i = 0; i < response.length(); i++) {
                        taskList.add(gson.fromJson(response.get(i).toString(), Task.class));
                    }
                    int index = 0;
                    for (Task t : taskList) {
                        index++;
                        Log.i("item" + index, t.getTitle());
                    }

                } catch (Exception e) {

                }

                sHandler.postDelayed(mRunnable, 500);
            }
        });
    }


    public void openTaskDetails(Task task) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), TaskDetailActivity.class);
        localDataHelper.saveTaskToFile(task);
        startActivity(intent);

    }

    private void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private static class WeakRunnable implements Runnable {

        WeakReference<SearchActivity> mSearchActivityReference;

        public WeakRunnable(SearchActivity searchActivity) {
            this.mSearchActivityReference = new WeakReference<SearchActivity>(searchActivity);
        }

        @Override
        public void run() {
            SearchActivity searchActivity = mSearchActivityReference.get();
            if (searchActivity != null && !searchActivity.isDestroyed()) {
                searchActivity.showProgressBar(false);
                searchActivity.bindData();
            }
        }
    }


}
