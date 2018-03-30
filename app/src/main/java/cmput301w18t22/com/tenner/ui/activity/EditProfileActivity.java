package cmput301w18t22.com.tenner.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cmput301w18t22.com.tenner.R;

public class EditProfileActivity extends AppCompatActivity {

    private TextView tvEmail;
    private EditText etFirst;
    private EditText etLast;
    private EditText etPhone;
    private TextView save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Change ActionBar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setElevation(3);
        getSupportActionBar().setCustomView(R.layout.toolbar_edit_profile);
        save = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_edit_save);

        tvEmail = (TextView) findViewById(R.id.edit_email);
        etFirst = (EditText) findViewById(R.id.edit_firstname);
        etLast = (EditText) findViewById(R.id.edit_lastname);
        etPhone = (EditText) findViewById(R.id.edit_phone);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyChanges();
            }
        });

        // Get the value from the server

        tvEmail.setText("test@gmail.com");
        etFirst.setText("Csaba");
        etLast.setText("Nagy");
        etPhone.setText("XXX-XXX-XXXX");

    }

    public void checkChanges() {
        // Check for inputs against constraints
    }

    public void applyChanges() {
        // Save to profile
        finish(); // Back to profile fragment with updated values
    }

}
