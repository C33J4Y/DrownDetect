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
    //User user;
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
                //if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                if(databaseHelper.isLoginValid(usernameValue, passwordValue)){

                    startActivity(intent);

                }
              //if (validateUser(user)) {
                  //  Toast.makeText(getApplicationContext(),
                  //          "LOGIN SUCCESSFUL", Toast.LENGTH_LONG).show();

              //}
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
                //createUser( user );
                Intent intent = new Intent(LoginActivity.this, CreateUserActivity.class);
                startActivity(intent);
            }
        });

    }

//    private void createUser(User user) {
//        Intent intent=new Intent(this,CreateUserActivity.class);
//        startActivity(intent);
//    }
//
//    private boolean validateUser(User user) {
//        //Call db functions to see if user exists and validate password
//        return true;
//    }
}
