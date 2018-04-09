package cmput301w18t22.com.tenner.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.broadcast.BroadcastManager;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.helpers.LocalDataHelper;
import cmput301w18t22.com.tenner.server.ElasticServer;
import cmput301w18t22.com.tenner.ui.adapter.FragmentAdapter;
import cmput301w18t22.com.tenner.ui.widget.BottomNavigatorView;
import cmput301w18t22.com.tenner.helpers.ConstantsHelper;
import cmput301w18t22.com.tenner.helpers.SharedPrefUtilsHelper;

import cmput301w18t22.com.tenner.helpers.InternetStatusHelper;

public class MainActivity extends AppCompatActivity implements BottomNavigatorView.OnBottomNavigatorViewItemClickListener {

    private static final int DEFAULT_POSITION = 0;
    private FragmentNavigator mNavigator;
    private BottomNavigatorView bottomNavigatorView;
    private TextView pageTitle;
    private User user;
    private LocalDataHelper localDataHelper;
    private Boolean cached = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InternetStatusHelper internetStatusHelper = new InternetStatusHelper();
        localDataHelper = new LocalDataHelper(this);
        user = localDataHelper.loadUserFromFile();

        if (savedInstanceState != null) {
            cached = savedInstanceState.getBoolean("cache", false);
        }

        if (!internetStatusHelper.isConnected(this)) {
            setTheme(R.style.Theme_AppCompat_Light);
        }
        setContentView(R.layout.activity_main);

        if (!SharedPrefUtilsHelper.isLogin(this)) {
            this.onDestroy();
        }

        mNavigator = new FragmentNavigator(getSupportFragmentManager(), new FragmentAdapter(), R.id.container);
        mNavigator.setDefaultPosition(DEFAULT_POSITION);
        mNavigator.onCreate(savedInstanceState);

        bottomNavigatorView = (BottomNavigatorView) findViewById(R.id.bottomNavigatorView);
        if (bottomNavigatorView != null) {
            bottomNavigatorView.setOnBottomNavigatorViewItemClickListener(this);
        }

        setCurrentTab(mNavigator.getCurrentPosition());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!cached) {
            loadTasksLocal();
        }
    }

    public void loadTasksLocal() {
        try {
            getRequestedTasks();
            getProvidingTasks();
            cached = true;

        } catch (Exception e) {

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("cache", cached);
        super.onSaveInstanceState(outState);
        mNavigator.onSaveInstanceState(outState);

    }

    @Override
    public void onBottomNavigatorViewItemClick(int position, View view) {
        setCurrentTab(position);
    }

    private void logout() {
        SharedPrefUtilsHelper.logout(this);
        BroadcastManager.sendLogoutBroadcast(this, 1);
        clearUserData();
    }

    private void setToolBar(final int position) {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setElevation(3);
        switch (position) {
            case 0:
                getSupportActionBar().setCustomView(R.layout.toolbar_home);
                break;
            case 1:
                getSupportActionBar().setCustomView(R.layout.toolbar_tasks);
                break;
            case 4:
                getSupportActionBar().setCustomView(R.layout.toolbar_profile);
                profileToolbarActions();
                break;
            default:
                getSupportActionBar().setCustomView(R.layout.toolbar_home);
                break;
        }
        setPageTitle(position);
    }

    private void setPageTitle(final int position) {
        final int pos = position;
        pageTitle = ((TextView) getSupportActionBar().getCustomView().findViewById(R.id.home_action_bar_title));
        pageTitle.setText(new FragmentAdapter().getName(position));
    }

    private void setCurrentTab(int position) {
        if (position == 2) {
            Intent intent = new Intent();
            intent.setClass(this, PostTaskActivity.class);
            startActivityForResult(intent, ConstantsHelper.ADD_TASK_REQUEST);
        } else {
            if (position != mNavigator.getCurrentPosition()) {
                mNavigator.showFragment(position);
            } else {
                mNavigator.showFragment(position, true);
            }

            bottomNavigatorView.select(position);
            setToolBar(position);
        }
    }

    private void profileToolbarActions() {
        Button logout = (Button) getSupportActionBar().getCustomView().findViewById(R.id.toolbar_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
                Boolean check = getIntent().getBooleanExtra("SIGNUP", false);
                if (check) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                } else {
                    finish();
                }
            }
        });

        Button edit = (Button) getSupportActionBar().getCustomView().findViewById(R.id.toolbar_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Edit Profile
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), EditProfileActivity.class);
                startActivityForResult(intent, ConstantsHelper.EDIT_PROFILE_REQUEST);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }

    public void clearUserData() {
        try {
            File cache = new File(getFilesDir(), ConstantsHelper.USERFILE);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ConstantsHelper.EDIT_PROFILE_REQUEST) {
            mNavigator.showFragment(mNavigator.getCurrentPosition(), true);
        }

        if (resultCode == ConstantsHelper.ADD_TASK_REQUEST) {
            mNavigator.showFragment(1, true);
        }
    }

    public void getProvidingTasks() throws JSONException {

        RequestParams params = new RequestParams();

        try {
            params.put("user", user.getEmail());
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post("getProvidingTasks", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    Gson gson = new GsonBuilder().create();
                    ArrayList<Task> taskList = new ArrayList<Task>();

                    for (int i = 0; i < response.length(); i++) {
                        taskList.add(gson.fromJson(response.get(i).toString(), Task.class));
                    }

                    localDataHelper.saveProvidingTasksToFile(taskList);

                } catch (Exception e) {

                }

            }
        });
    }

    public void getRequestedTasks() throws JSONException {

        RequestParams params = new RequestParams();

        try {
            params.put("user", user.getEmail());
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post("getRequestedTasks", params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {

                    Gson gson = new GsonBuilder().create();
                    ArrayList<Task> taskList = new ArrayList<Task>();

                    for (int i = 0; i < response.length(); i++) {
                        taskList.add(gson.fromJson(response.get(i).toString(), Task.class));
                    }

                    localDataHelper.saveRequestedTasksToFile(taskList);

                } catch (Exception e) {

                }

            }
        });
    }
}