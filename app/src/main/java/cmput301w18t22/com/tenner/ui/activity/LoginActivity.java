package cmput301w18t22.com.tenner.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.broadcast.BroadcastManager;
import cmput301w18t22.com.tenner.server.ElasticSearchRestClient;
import cmput301w18t22.com.tenner.utils.Authenticator;
import cmput301w18t22.com.tenner.utils.SharedPrefUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;
    private TextView signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.et_email);
        Button button = (Button) findViewById(R.id.login_in_button);
        signup = (TextView) findViewById(R.id.sign_up_prompt);
        button.setOnClickListener(this);
        signup.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        checkLoggedIn();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLoggedIn();
    }

    public void checkLoggedIn() {
        if (SharedPrefUtils.isLogin(this)) {
            startActivity(new Intent(this, MainActivity.class).putExtra("SIGNUP", true));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_in_button) {
            tryLogin();
        }
        if (v.getId() == R.id.sign_up_prompt) {
            startActivity(new Intent(this, SignUpActivity.class));
        }
    }

    void tryLogin() {
        String email = String.valueOf(etEmail.getText()).trim();

        if (check(email)) {

            ElasticSearchRestClient elasticSearchRestClient = ElasticSearchRestClient.getInstance();
            try {
                elasticSearchRestClient.postLoginUser(email);
            } catch (Exception e) {

            }

            markUserLogin();
            notifyUserLogin();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    boolean check(String email) {
        Authenticator a = new Authenticator();
        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.error_invalid_email));
            return false;
        }
        if (!a.checkEmail(email)) {
            etEmail.setError("incorrect email");
            return false;
        }
        return true;
    }

    private void markUserLogin() {
        SharedPrefUtils.login(this);
    }

    private void notifyUserLogin() {
        BroadcastManager.sendLoginBroadcast(this, 1);
    }

    @Override
    public void onBackPressed() {

    }
}

