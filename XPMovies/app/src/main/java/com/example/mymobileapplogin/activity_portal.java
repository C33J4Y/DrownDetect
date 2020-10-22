package com.example.mymobileapplogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.mymobileapplogin.Notifications.CHANNEL_1_ID;

public class activity_portal extends AppCompatActivity {
    TextView signOutButton;
    Button perimeterCamButton;
    Button poolCamButton;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);

        signOutButton = findViewById(R.id.textSignOut);
        perimeterCamButton = (Button) findViewById(R.id.perimeterButton);
        poolCamButton = (Button) findViewById(R.id.poolButton);
        notificationManager = NotificationManagerCompat.from(this);


        perimeterCamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), perimeter_camView.class);
                startActivity(intent);

            }
        });

        poolCamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), pool_camView.class);
                startActivity(intent);

            }
        });




        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


        }

    public void sendOnChannel1(View v) {
        //String title = editTextTitle.getText().toString();
        //String message = editTextMessage.getText().toString();
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_warning)
                .setContentTitle("ALERT")
                .setContentText("Possible drowning. Please assist person.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1, notification);


    }
}