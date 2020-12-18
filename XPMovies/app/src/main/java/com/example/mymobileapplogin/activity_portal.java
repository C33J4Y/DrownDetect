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
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static com.example.mymobileapplogin.Notifications.CHANNEL_1_ID;
import static com.example.mymobileapplogin.Notifications.CHANNEL_2_ID;

public class activity_portal extends AppCompatActivity {
    TextView signOutButton;
    Button perimeterCamButton;
    Button poolCamButton;
    private NotificationManagerCompat notificationManager;

    //Server(RPi) IP address and port listening for connection
    public static final int SERVERPORT = 8123;
    public static final String SERVER_IP = "172.20.10.2";
    private ClientThread clientThread;
    private Thread thread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);

        signOutButton = findViewById(R.id.textSignOut);
        perimeterCamButton = (Button) findViewById(R.id.perimeterButton);
        poolCamButton = (Button) findViewById(R.id.poolButton);
        notificationManager = NotificationManagerCompat.from(this);

        //Setting up threads for socket connection
        clientThread = new ClientThread();
        thread = new Thread(clientThread);
        thread.start();

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

    //Alerts user of a possible drowning
    public void sendOnChannel1(View v) {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_warning)
                .setContentTitle("ALERT")
                .setContentText("Possible drowning. Please assist person.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1, notification);


    }

    //Alerts user that people have entered the pool
    public void sendOnChannel2(View v) {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_warning)
                .setContentTitle("Warning")
                .setContentText("People have entered the pool area.")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(2, notification);


    }

    class ClientThread implements Runnable {

        private Socket socket;
        private BufferedReader input;
        private String message;
        View v;


        @Override
        public void run() {

            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr, SERVERPORT);
                this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                message = input.readLine();

                activity_portal.this.runOnUiThread(new Runnable() {
                    public void run() {

                        Toast.makeText(activity_portal.this, message, Toast.LENGTH_SHORT).show();
                        sendOnChannel2(v);


                    }
                });


            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}