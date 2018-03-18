package cmput301w18t22.com.tenner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //sign up form
        final EditText username = (EditText) findViewById(R.id.sign_up_username);
        final EditText firstn = (EditText) findViewById(R.id.sign_up_firstn);
        final EditText lastn = (EditText) findViewById(R.id.sign_up_lastn);
        final EditText phone = (EditText) findViewById(R.id.sign_up_phone);
        Button signUp = (Button) findViewById(R.id.sign_up_button);
        TextView cancel = (TextView) findViewById(R.id.sign_up_cancel);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
