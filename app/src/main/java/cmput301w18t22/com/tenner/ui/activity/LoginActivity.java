package cmput301w18t22.com.tenner.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.broadcast.BroadcastManager;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.server.ElasticServer;
import cmput301w18t22.com.tenner.utils.Authenticator;
import cmput301w18t22.com.tenner.utils.LocalDataHandler;
import cmput301w18t22.com.tenner.utils.SharedPrefUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private TextView signup;
    private Button login;
    private LocalDataHandler localDataHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        localDataHandler = new LocalDataHandler(this);

        etEmail = (EditText) findViewById(R.id.et_email);
        login = (Button) findViewById(R.id.login_in_button);
        signup = (TextView) findViewById(R.id.sign_up_prompt);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
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
            login.setEnabled(false);
            login.setClickable(false);
            tryLogin();
        }
        if (v.getId() == R.id.sign_up_prompt) {
            startActivity(new Intent(this, SignUpActivity.class));
        }
    }

    void tryLogin() {
        String email = String.valueOf(etEmail.getText()).trim();

        if (check(email)) {
            try {
                postLoginUser(email);
            } catch (Exception e) {

            }
        }
    }

    void login(String username) {
        SharedPrefUtils.login(this, username);
        BroadcastManager.sendLoginBroadcast(this, 1);
        startActivity(new Intent(this, MainActivity.class));
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

    @Override
    public void onBackPressed() {

    }

    public void postLoginUser(String username) throws JSONException {

        RequestParams params = new RequestParams();

        try {
            params.put("user", username);
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post("getUser", params, new JsonHttpResponseHandler() {

            @Override
            public void onFinish() {
                super.onFinish();
                login.setEnabled(true);
                login.setClickable(true);
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.has("email")) {

                        Gson gson = new GsonBuilder().create();
                        User user = gson.fromJson(response.toString(), User.class);
                        localDataHandler.saveUserInFile(user);
                        login(user.getEmail());

                    } else if (response.has("Error")) {

                        Toast toast = Toast.makeText(getApplicationContext(), response.get("Error").toString(), Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                    }
                } catch (Exception e) {

                }
            }

        });
    }

}

