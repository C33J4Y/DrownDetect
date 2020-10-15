package com.example.mymobileapplogin;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CreateUserActivity extends Activity {

    ImageView imageView;
    TextView username, email, password;
    String usernameValue, emailValue, passwordValue;
    Button createUser;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createuser);

        username = findViewById(R.id.textName);
        email = findViewById(R.id.textEmail);
        password = findViewById(R.id.textPassword);
        createUser = findViewById(R.id.buttonCreateUser);

        databaseHelper = new DatabaseHelper(this);



      createUser.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              usernameValue = username.getText().toString();
              emailValue = email.getText().toString();
              passwordValue = password.getText().toString();

              if (usernameValue.length() > 1){
                  ContentValues contentValues = new ContentValues();
                  contentValues.put("username", usernameValue);
                  contentValues.put("email", emailValue);
                  contentValues.put("password", passwordValue);

                  databaseHelper.insertUser(contentValues);
                  Toast.makeText(CreateUserActivity.this, "User Registered.", Toast.LENGTH_SHORT).show();

              }else{
                  Toast.makeText(CreateUserActivity.this, "Complete all fields.", Toast.LENGTH_SHORT).show();
              }
          }
      });

    }

    private void createUser() {
        Toast.makeText(getApplicationContext(), "New User Created", Toast.LENGTH_LONG).show();
        System.out.println("");
        finish();
    }
}

