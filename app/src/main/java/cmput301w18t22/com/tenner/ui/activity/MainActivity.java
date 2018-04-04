package cmput301w18t22.com.tenner.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.aspsine.fragmentnavigator.FragmentNavigator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.broadcast.BroadcastManager;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.ui.adapter.FragmentAdapter;
import cmput301w18t22.com.tenner.ui.widget.BottomNavigatorView;
import cmput301w18t22.com.tenner.utils.Constants;
import cmput301w18t22.com.tenner.utils.SharedPrefUtils;

public class MainActivity extends AppCompatActivity implements BottomNavigatorView.OnBottomNavigatorViewItemClickListener {

    private static final int DEFAULT_POSITION = 0;
    private FragmentNavigator mNavigator;
    private BottomNavigatorView bottomNavigatorView;
    private TextView pageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isNetworkAvailable()) {
            setTheme(R.style.Theme_AppCompat_Light);
        }
        setContentView(R.layout.activity_main);

        if (!SharedPrefUtils.isLogin(this)) {
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mNavigator.onSaveInstanceState(outState);
    }

    @Override
    public void onBottomNavigatorViewItemClick(int position, View view) {
        setCurrentTab(position);
    }

    private void logout() {
        SharedPrefUtils.logout(this);
        BroadcastManager.sendLogoutBroadcast(this, 1);
        clearUserCache();
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
            startActivity(intent);
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
        TextView logout = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbar_logout);
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

        TextView edit = (TextView) getSupportActionBar().getCustomView().findViewById(R.id.toolbar_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Edit Profile
                startActivity(new Intent(getApplicationContext(), EditProfileActivity.class));
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onBackPressed() {
    }

    public void clearUserCache() {
        try {
            File cache = new File(getFilesDir(), Constants.FILENAME);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


}
