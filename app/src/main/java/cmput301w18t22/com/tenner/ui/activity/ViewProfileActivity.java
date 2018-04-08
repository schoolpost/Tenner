package cmput301w18t22.com.tenner.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.classes.Bid;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.helpers.LocalDataHelper;
import cmput301w18t22.com.tenner.helpers.PhotoConverterHelper;

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
        Intent intent = getIntent();

        // Load Requester profile from task
        task = localDataHelper.getTaskFromFile();

        if (intent.getExtras() == null) {
            Log.i("actually", "we got here");
            user = task.getRequester();
        } else {
            Log.i("we", "got here");
            //task.addBid(new Bid(new User("", "John", "Smith", "1234567890", ""), "2.75", new Date(), task));
            //task.addBid(new Bid(new User("", "John", "Smith", "1234567890", ""), "3.75", new Date(), task));
            //task.addBid(new Bid(new User("", "John", "Smith", "1234567890", ""), "4.75", new Date(), task));

            int index = 0;
            intent.getIntExtra("bidIndex", index);
            user = task.getBidList().get(index).getOwner();
        }

        // Set the value to display
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
