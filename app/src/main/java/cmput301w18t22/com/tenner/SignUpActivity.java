package cmput301w18t22.com.tenner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * InComplete without ElasticSearch
 */

public class SignUpActivity extends AppCompatActivity {

    public EditText email;
    public EditText fname;
    public EditText lname;
    public EditText phone;
    public Button sign;
    public Button cancel;
    public User userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.emailId);
        fname = findViewById(R.id.fnameId);
        lname = findViewById(R.id.lnameId);
        phone = findViewById(R.id.phoneId);
        sign = findViewById(R.id.signId);
        cancel = findViewById(R.id.cancelId);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
                changeHomeActivity();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInActivity();
            }
        });
    }

    public void changeHomeActivity() {
        Intent homeActivity = new Intent();
        homeActivity.setClass(SignUpActivity.this, HomeActivity.class);
        startActivity(homeActivity);
    }

    public void logInActivity() {
        Intent logActivity = new Intent();
        logActivity.setClass(SignUpActivity.this, LoginActivity.class);
        startActivity(logActivity);
    }



    private void attemptLogin() {

        // Reset errors.
        email.setError(null);
        fname.setError(null);
        lname.setError(null);
        phone.setError(null);

        // Store values at the time of the login attempt.
        String emailvalue = email.getText().toString();
        String fnamevalue = fname.getText().toString();
        String lnamevalue = lname.getText().toString();
        String phonevalue = email.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a validity, if the user entered one.
        if (!TextUtils.isEmpty(fnamevalue) && !isFnameValid(fnamevalue)) {
            fname.setError("Please enter first name correctly");
            focusView = fname;
            cancel = true;
        }

        if (!TextUtils.isEmpty(lnamevalue) && !isLnameValid(lnamevalue)) {
            lname.setError("Please enter last name correctly");
            focusView = lname;
            cancel = true;
        }

        if (!TextUtils.isEmpty(phonevalue) && !isLnameValid(phonevalue)) {
            phone.setError("Please enter phone number correctly");
            focusView = phone;
            cancel = true;
        }

        if (!TextUtils.isEmpty(fnamevalue) && !isEmailValid(fnamevalue)) {
            fname.setError("Please enter first name correctly");
            focusView = fname;
            cancel = true;
        }

        if (!TextUtils.isEmpty(fnamevalue) && !isEmailValid(fnamevalue)) {
            fname.setError("Please enter first name correctly");
            focusView = fname;
            cancel = true;
        }

        if (TextUtils.isEmpty(emailvalue)) {
            email.setError(getString(R.string.error_field_required));
            focusView = email;
            cancel = true;
        } else if (!isEmailValid(emailvalue)) {
            email.setError(getString(R.string.error_invalid_email));
            focusView = email;
            cancel = true;
        }

        if (cancel) {

            focusView.requestFocus();
        }
    }
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isFnameValid(String fname) {
        return fname.length() < 30;
    }

    private boolean isLnameValid(String lname) {
        return lname.length() < 30;
    }

    private boolean isPhoneValid(String phone) {
        return phone.length() < 10;
    }


    }
