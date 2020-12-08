package com.example.mymobileapplogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static com.example.mymobileapplogin.Notifications.CHANNEL_1_ID;

public class activity_portal extends AppCompatActivity {
    TextView signOutButton;
    Button perimeterCamButton;
    Button poolCamButton;
    private NotificationManagerCompat notificationManager;

    //Server(RPi) IP address and port listening for connection
    public static final int SERVERPORT = 8123;
    public static final String SERVER_IP = "10.0.0.244";
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

    class ClientThread implements Runnable {

        private Socket socket;
        private BufferedReader input;


        @Override
        public void run() {

            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr, SERVERPORT);



                while (!Thread.currentThread().isInterrupted()) {

                    this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message = input.readLine();

                    activity_portal.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(activity_portal.this, "Hello!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    if (null == message || "Disconnect".contentEquals(message)) {
                        Thread.interrupted();
                        message = "Server Disconnected.";
                        //showMessage(message, Color.RED);
                        break;
                    }
                   // showMessage("Server: " + message, clientTextColor);
                }

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }


    }
}