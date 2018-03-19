package cmput301w18t22.com.tenner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

public class LoginActivity extends AppCompatActivity {

    private User user = new User("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText username = (EditText) findViewById(R.id.login_input);
        Button login = (Button) findViewById(R.id.login_action);
        TextView signUp = (TextView) findViewById(R.id.sign_up_prompt);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameText = username.getText().toString();

                if (signIn(usernameText)) {

                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        if (!user.getEmail().equals("")) {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, HomeActivity.class);
            intent.putExtra("User", user.getEmail());
            startActivity(intent);
            finish();
            Log.i("User:", "found!");
        }
    }

    private Boolean signIn(String username) {


        Authenticator auth = new Authenticator();
        user = auth.loginUser(new User(username));

        if (user.getEmail().equals("")) {
            notify("No account associated with this email!");
            return Boolean.FALSE;
        }

        saveInFile();
        return Boolean.TRUE;


    }

    private void notify(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(Constants.FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type userType = new TypeToken<User>() {
            }.getType();
            user = gson.fromJson(in, userType);

            Log.i("Phone", user.getPhoneNum());


        } catch (FileNotFoundException e) {
            Log.i("Error", e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    /**
     * Saves the subscriptions GSON into a file.
     */

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
