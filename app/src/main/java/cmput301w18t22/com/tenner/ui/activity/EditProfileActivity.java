package cmput301w18t22.com.tenner.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cmput301w18t22.com.tenner.R;
import cmput301w18t22.com.tenner.broadcast.BroadcastManager;
import cmput301w18t22.com.tenner.classes.User;
import cmput301w18t22.com.tenner.server.ElasticSearchRestClient;
import cmput301w18t22.com.tenner.server.ElasticServer;
import cmput301w18t22.com.tenner.utils.Authenticator;
import cmput301w18t22.com.tenner.utils.Constants;
import cmput301w18t22.com.tenner.utils.LocalDataHandler;
import cmput301w18t22.com.tenner.utils.SharedPrefUtils;

public class EditProfileActivity extends AppCompatActivity {

    private TextView tvEmail;
    private EditText etFirst;
    private EditText etLast;
    private EditText etPhone;
    private TextView save;

    private User user;
    private LocalDataHandler localDataHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        localDataHandler = new LocalDataHandler(this);

        // Change ActionBar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setElevation(3);
        getSupportActionBar().setCustomView(R.layout.toolbar_edit_profile);
        save = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_edit_save);

        tvEmail = (TextView) findViewById(R.id.edit_email);
        etFirst = (EditText) findViewById(R.id.edit_firstname);
        etLast = (EditText) findViewById(R.id.edit_lastname);
        etPhone = (EditText) findViewById(R.id.edit_phone);

        loadData();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyChanges();
            }
        });


    }

    private void loadData() {
        user = localDataHandler.loadUserFromFile();

        etFirst.setText(user.getFirstName());
        etLast.setText(user.getLastName());
        tvEmail.setText(user.getEmail());
        etPhone.setText(user.getPhoneNum());
    }

    boolean check(String first, String last, String phone) {
        Authenticator a = new Authenticator();

        if (!a.checkName(first)) {
            etFirst.setError("invalid first name");
            return false;
        }
        if (!a.checkName(last)) {
            etLast.setError("invalid last name");
            return false;
        }
        if (!a.checkPhoneNum(phone)) {
            etPhone.setError("invalid phone number");
            return false;
        }
        return true;
    }

    public void applyChanges() {

        String firstName = String.valueOf(etFirst.getText()).trim();
        String lastName = String.valueOf(etLast.getText()).trim();
        String phone = String.valueOf(etPhone.getText()).trim();

        if (check(firstName, lastName, phone)) {
            if (TextUtils.isEmpty(firstName)) {
                firstName = user.getFirstName();
            }
            if (TextUtils.isEmpty(lastName)) {
                lastName = user.getLastName();
            }
            if (TextUtils.isEmpty(firstName)) {
                phone = user.toDisplayPhone();
            }

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNum(phone);

            // post the edited user
            try {
                postEditUser(user);
            } catch (Exception e) {

            }

        }

    }

    public void postEditUser(final User postUser) throws JSONException {

        RequestParams params = new RequestParams();
        Gson gson = new Gson();
        String json = gson.toJson(postUser);

        try {
            params.put("user", json);
        } catch (Exception e) {

        }

        ElasticServer.RestClient.post("editUser", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    localDataHandler.saveUserInFile(postUser);
                    finish();
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

}
