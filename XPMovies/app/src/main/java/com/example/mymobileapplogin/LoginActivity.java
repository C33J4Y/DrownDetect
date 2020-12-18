package com.example.mymobileapplogin;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
    Button b1;
    EditText username, password;
    String usernameValue, passwordValue;
    TextView t1;
    DatabaseHelper databaseHelper;

    int counter = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1 = (Button) findViewById(R.id.LoginButton);
        username = (EditText) findViewById(R.id.emailText);
        password = (EditText) findViewById(R.id.passwordText);
        databaseHelper = new DatabaseHelper(this);

        usernameValue = username.getText().toString();
        passwordValue = password.getText().toString();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameValue = username.getText().toString();
                passwordValue = password.getText().toString();


                Intent intent = new Intent(getApplicationContext(), activity_portal.class);
                // validate user
                if(databaseHelper.isLoginValid(usernameValue, passwordValue)){
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_LONG).show();
                    counter--;
                    if (counter == 0) {
                        b1.setEnabled(false);
                    }
                }
            }
        });

        t1 = (TextView)findViewById(R.id.textCreateUser);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateUserActivity.class);
                startActivity(intent);
            }
        });

    }


}
