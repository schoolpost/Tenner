package cmput301w18t22.com.tenner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SignUpActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //sign up form
        final EditText username = (EditText) findViewById(R.id.sign_up_username);
        final EditText first = (EditText) findViewById(R.id.sign_up_first);
        final EditText last = (EditText) findViewById(R.id.sign_up_last);
        final EditText phone = (EditText) findViewById(R.id.sign_up_phone);
        final Button signUp = (Button) findViewById(R.id.sign_up_button);
        TextView cancel = (TextView) findViewById(R.id.sign_up_cancel);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameText = username.getText().toString();
                String firstnText = first.getText().toString();
                String lastnText = last.getText().toString();
                String phoneText = phone.getText().toString();

                if (signUp(usernameText, firstnText, lastnText, phoneText)) {
                    Intent intent = new Intent();
                    intent.setClass(SignUpActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private Boolean signUp(String username, String firstn, String lastn, String phone) {

        Authenticator auth = new Authenticator();

        if (auth.checkUserExists(username)) {
            return Boolean.FALSE;
        }

        if (!auth.checkEmail(username)) {
            notify("Email is Invalid!");
            return Boolean.FALSE;
        }
        if (!auth.checkName(firstn) && !auth.checkName(lastn)) {
            notify("Name is Invalid!");
            return Boolean.FALSE;
        }
        if (phone.length() > 0) {
            if (!auth.checkPhoneNum(phone)) {
                notify("Phone number is Invalid!");
                return Boolean.FALSE;
            }
        }

        user = new User(username, firstn, lastn, phone);

        if (!auth.searchUser(user)) {

            ElasticSearchController.AddUsers newUser = new ElasticSearchController.AddUsers();
            newUser.execute(user);

        } else {

            notify("User already exists!");
            return Boolean.FALSE;

        }

        saveInFile();


        return Boolean.TRUE;

    }

    private void notify(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void saveInFile() {
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


    @Override
    public void onBackPressed() {

    }

}
