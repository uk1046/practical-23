package com.example.practical23;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMessages;

    private BroadcastReceiver systemBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getAction();
            updateMessages(message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMessages = findViewById(R.id.textViewMessages);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerBroadcastReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterBroadcastReceiver();
    }

    private void registerBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BOOT_COMPLETED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        // Register the receiver for system broadcast intents
        registerReceiver(systemBroadcastReceiver, filter);
    }

    private void unregisterBroadcastReceiver() {
        // Unregister the receiver
        unregisterReceiver(systemBroadcastReceiver);
    }

    private void updateMessages(String message) {
        String currentText = textViewMessages.getText().toString();
        textViewMessages.setText(currentText + "\n" + message);
    }
}
