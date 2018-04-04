package cmput301w18t22.com.tenner.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.broadcast.BroadcastManager;
import cmput301w18t22.com.tenner.classes.Bid;
import cmput301w18t22.com.tenner.classes.Task;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.server.ElasticSearchRestClient;
import cmput301w18t22.com.tenner.utils.Authenticator;
import cmput301w18t22.com.tenner.utils.Constants;
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

            User user = new User(email, firstn, lastn, phone, new ArrayList<Task>(), new ArrayList<Task>(), new ArrayList<Bid>());

            ElasticSearchRestClient elasticSearchRestClient = ElasticSearchRestClient.getInstance();
            try {
                elasticSearchRestClient.postUser(user);
            } catch (Exception e) {

            }

            SharedPrefUtils.login(this, "");
            BroadcastManager.sendLoginBroadcast(this, 1);
            saveInFile(user);
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


    private void saveInFile(User user) {
        try {
            FileOutputStream fos = openFileOutput(Constants.FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(user, out);
            out.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}


