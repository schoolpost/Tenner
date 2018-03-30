package cmput301w18t22.com.tenner.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import cmput301w18t22.com.tenner.R;

public class EditProfileActivity extends AppCompatActivity {

    private EditText etFirst;
    private EditText etLast;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Change ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etFirst = (EditText) findViewById(R.id.et_first);
        etLast = (EditText) findViewById(R.id.et_last);
        etPhone = (EditText) findViewById(R.id.et_phone);

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
