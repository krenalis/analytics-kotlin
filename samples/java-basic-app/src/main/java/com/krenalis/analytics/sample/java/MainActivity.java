package com.krenalis.analytics.sample.java;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        MainApplication app = (MainApplication) getApplication();

        Button trackButton = findViewById(R.id.trackButton);
        trackButton.setOnClickListener(v -> {
            Map<String, Object> properties = new HashMap<>();
            properties.put("workout_type", "Cardio");
            properties.put("duration_minutes", 45);
            properties.put("calories_burned", 380);
            properties.put("device", "Smartwatch");
            app.analytics.track("Workout completed", properties);
        });

        Button identifyButton = findViewById(R.id.identifyButton);
        identifyButton.setOnClickListener(v -> {
            Map<String, Object> traits = new HashMap<>();
            traits.put("Name", "Jhon");
            traits.put("Email", "jhon@example.com");
            app.analytics.identify("user1234", traits);
        });

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(v -> {
            app.analytics.reset(false);
        });
    }
}
