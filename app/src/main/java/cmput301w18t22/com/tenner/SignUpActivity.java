package cmput301w18t22.com.tenner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

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

        User user = new User(username, firstn, lastn, phone);

        if (!auth.searchUser(user)) {

            ElasticSearchController.AddUsers newUser = new ElasticSearchController.AddUsers();
            newUser.execute(user);

        } else {

            notify("User already exists!");
            return Boolean.FALSE;

        }


        return Boolean.TRUE;

    }

    private void notify(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

    }

}
