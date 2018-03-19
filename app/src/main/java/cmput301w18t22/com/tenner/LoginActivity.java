package cmput301w18t22.com.tenner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

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

    private boolean signIn(String username) {

        User user = new User(username);

        Authenticator auth = new Authenticator();
        if (!auth.searchUser(user)) {
            notify("No account associated with this email!");
            return Boolean.FALSE;
        }

        return Boolean.TRUE;


    }

    private void notify(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}
