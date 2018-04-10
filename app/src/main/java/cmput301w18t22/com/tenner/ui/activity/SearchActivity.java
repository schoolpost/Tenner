package cmput301w18t22.com.tenner.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
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
    private ListView displayList;
    private int tab;
    private User user;
    private LocalDataHelper localDataHelper;
    private InternetStatusHelper internetStatusHelper = new InternetStatusHelper();
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private ProgressBar progressBar;
    private SearchActivity.WeakRunnable mRunnable = new SearchActivity.WeakRunnable(this);
    private String query;
    private TextView noResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        noResults = (TextView) findViewById(R.id.noResults);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setElevation(3);
        getSupportActionBar().setCustomView(R.layout.toolbar_search);
        TextView title = getSupportActionBar().getCustomView().findViewById(R.id.home_action_bar_title);
        title.setText("Search");

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

        localDataHelper = new LocalDataHelper(this);

        taskList = new ArrayList<Task>();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        displayList = (ListView) findViewById(R.id.tasksList);
        setFilter();

        Intent intent = getIntent();
        query = intent.getStringExtra("query");

        if (savedInstanceState == null) {
            loadData();
        } else {
            bindData();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().subscribeToTopic("bids");
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
            postTaskSearch(query);
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

            }

            @Override
            public void onFinish() {
                super.onFinish();
                sHandler.postDelayed(mRunnable, 500);
            }
        });
    }


    private void bindData() { // Attach to listview
        Log.i("debug", "Binding");

        myAdapter = new TaskAdapter(getApplicationContext(), taskList);
        displayList.setAdapter(myAdapter);

        displayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openTaskDetails(taskList.get(i));
            }
        });

        if (taskList.size() == 0) {
            noResults.setVisibility(View.VISIBLE);
        }

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
