package cmput301w18t22.com.tenner;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public class ProfileActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar_layout);
        // set the activity title
        ((TextView) getSupportActionBar().getCustomView().findViewById(R.id.custom_action_bar_title)).setText(R.string.title_profile);


        ImageView profile;
        TextView name;
        TextView email;
        TextView phone;

        // Profile image
        name = findViewById(R.id.profile_name);
        email = findViewById(R.id.profile_email);
        phone = findViewById(R.id.profile_phone);

        //TODO: Set value from elastic search

        Authenticator auth = new Authenticator();
        user = auth.loginUser(new User(loadFromFile()));


        name.setText(user.getFirstName() + " " + user.getLastName());
        email.setText(user.getEmail());
        phone.setText(user.getPhoneNum());

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        break;
                    case R.id.navigation_tasks:
                        startActivity(new Intent(getApplicationContext(), TaskActivity.class));
                        break;
                    case R.id.navigation_post:
                        startActivity(new Intent(getApplicationContext(), PostTaskActivity.class));
                        break;
                    case R.id.navigation_bids:
                        break;
                    case R.id.navigation_profile:
                        break;
                }
                return false;
            }
        });

    }

    private String loadFromFile() {

        try {
            FileInputStream fis = openFileInput(Constants.FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type userType = new TypeToken<User>() {
            }.getType();
            User user = gson.fromJson(in, userType);

            return user.getEmail();


        } catch (FileNotFoundException e) {
            Log.i("Error", e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return "";

    }


}
