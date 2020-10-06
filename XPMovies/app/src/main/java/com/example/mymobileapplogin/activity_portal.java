package com.example.mymobileapplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity_portal extends AppCompatActivity {
    TextView signOutButton;
    Button perimeterCamButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);

        signOutButton = findViewById(R.id.textSignOut);
        perimeterCamButton = (Button) findViewById(R.id.perimeterButton);

        perimeterCamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), perimeter_camView.class);
                startActivity(intent);

            }
        });



        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}