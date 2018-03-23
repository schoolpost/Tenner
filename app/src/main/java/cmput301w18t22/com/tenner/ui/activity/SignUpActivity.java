package cmput301w18t22.com.tenner.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.broadcast.BroadcastManager;
import cmput301w18t22.com.tenner.utils.Authenticator;
import cmput301w18t22.com.tenner.utils.SharedPrefUtils;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etFirst;
    private EditText etLast;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        etEmail = (EditText) findViewById(R.id.et_email);
        etFirst = (EditText) findViewById(R.id.et_first);
        etLast = (EditText) findViewById(R.id.et_last);
        etPhone = (EditText) findViewById(R.id.et_phone);
        Button button = (Button) findViewById(R.id.sign_up_button);
        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_up_button) {
            tryLogin();
        }
    }

    void tryLogin() {
        String email = String.valueOf(etEmail.getText()).trim();
        String firstn = String.valueOf(etFirst.getText()).trim();
        String lastn = String.valueOf(etLast.getText()).trim();
        String phone = String.valueOf(etPhone.getText()).trim();

        if (check(email, firstn, lastn, phone)) {
            markUserLogin();
            notifyUserLogin();
            finish();
        }
    }

    boolean check(String email, String first, String last, String phone) {
        Authenticator a = new Authenticator();
        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.error_invalid_email));
            return false;
        }
        if (!a.checkEmail(email)) {
            etEmail.setError("invalid email");
            return false;
        }

        if (checkUserExist(email)) {
            etEmail.setError("email is already registered");
            return false;
        }

        if (TextUtils.isEmpty(first)) {
            etFirst.setError("first name is empty");
            return false;
        }
        if (!a.checkName(first)) {
            etFirst.setError("invalid first name");
            return false;
        }
        if (TextUtils.isEmpty(last)) {
            etLast.setError("last name is empty");
            return false;
        }
        if (!a.checkName(last)) {
            etLast.setError("invalid last name");
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            etPhone.setError("phone number is empty");
            return false;
        }
        if (!a.checkPhoneNum(phone)) {
            etPhone.setError("invalid phone number");
            return false;
        }
        return true;
    }

    private boolean checkUserExist(String email) {
        return false;
    }

    private void markUserLogin() {
        SharedPrefUtils.login(this);
    }

    private void notifyUserLogin() {
        BroadcastManager.sendLoginBroadcast(this, 1);
    }
}


