package cmput301w18t22.com.tenner.ui.activity;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.helpers.LocalDataHelper;
import cmput301w18t22.com.tenner.helpers.PhotoConverterHelper;
import cmput301w18t22.com.tenner.ui.fragment.ProfileFragment;

public class ViewProfileActivity extends AppCompatActivity {

    private TextView pageTitle;
    private TextView name;
    private TextView email;
    private TextView phone;
    private User user;
    private Task task;
    private LocalDataHelper localDataHelper;
    private ImageView profilePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        localDataHelper = new LocalDataHelper(this);

        // Change ActionBar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setElevation(3);
        getSupportActionBar().setCustomView(R.layout.toolbar_home);
        pageTitle = (getSupportActionBar().getCustomView().findViewById(R.id.home_action_bar_title));
        pageTitle.setText("User Profile");

        name = (TextView) findViewById(R.id.view_profile_name);
        email = (TextView) findViewById(R.id.view_profile_email);
        phone = (TextView) findViewById(R.id.view_profile_phone);
        profilePic = (ImageView) findViewById(R.id.view_profile_picture);


        loadFromFile();
    }



    public void loadFromFile(){
        task = localDataHelper.getTaskFromFile();
        user = task.getRequester();

        name.setText(user.toProfileName()); // First + Last Name
        email.setText(user.getEmail());
        phone.setText(user.toDisplayPhone());

        if (user.getPhoto().equals("")) {
            profilePic.setImageResource(R.drawable.user_pic);
        } else {
            profilePic.setImageBitmap(new PhotoConverterHelper().convertStringToBM(user.getPhoto()));
        }
    }
}
