package com.example.recipestash;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.recipestash.R;

import java.util.Random;

public class timer_activity extends AppCompatActivity {
    private EditText durationEditText;
    private TextView timerTextView;
    private CountDownTimer timer;

    private static final String CHANNEL_ID = "timer_channel";
    private static final int NOTIFICATION_ID = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_layout);

        Button startButton = findViewById(R.id.startButton);
        durationEditText = findViewById(R.id.durationEditText);
        timerTextView = findViewById(R.id.timerTextView);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
    }

    private void startTimer() {
        String durationString = durationEditText.getText().toString();

        if (!durationString.isEmpty()) {
            int durationMinutes = Integer.parseInt(durationString);
            long durationMillis = (long) durationMinutes * 60 * 1000;

            if (timer != null) {
                timer.cancel();
            }

            timer = new CountDownTimer(durationMillis, 1000) {
                @SuppressLint("SetTextI18n")
                @Override
                public void onTick(long millisUntilFinished) {
                    long seconds = millisUntilFinished / 1000;
                    timerTextView.setText("Time remaining: " + seconds + " seconds");
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onFinish() {
                    timerTextView.setText("Timer finished");
                    createNotification();
                }
            };

            timer.start();
        } else {
            Toast.makeText(this, "Please enter a duration", Toast.LENGTH_SHORT).show();
        }
    }

    private void createNotification() {
        NotificationCompat.Builder mBuilder = new
                NotificationCompat.Builder(timer_activity.this,
                CHANNEL_ID );
        mBuilder.setSmallIcon(R.drawable.mainicon);
        mBuilder.setContentTitle("Notification Alert, Click Me!");
        mBuilder.setContentText("the Timer Is Finished");
        Intent intent = new Intent(timer_activity.this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(timer_activity.this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(contentIntent);
        NotificationChannel channel;
        NotificationManager mNotificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Recipe App";
            String description = "Recipe App Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            mNotificationManager.createNotificationChannel(channel);
        }

        mNotificationManager.notify(1, mBuilder.build());
    }


}
