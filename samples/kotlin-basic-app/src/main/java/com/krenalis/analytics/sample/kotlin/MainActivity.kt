package com.krenalis.analytics.sample.kotlin

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.krenalis.analytics.kotlin.core.Analytics
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        Analytics.debugLogsEnabled = true
        val app = application as MainApplication
        val analytics = app.analytics

        val startSessionButton: Button = findViewById(R.id.startSessionButton)
        startSessionButton.setOnClickListener {
            analytics.startSession(null)
        }

        val endSessionButton: Button = findViewById(R.id.endSessionButton)
        endSessionButton.setOnClickListener {
            analytics.endSession()
        }

        val clickButton: Button = findViewById(R.id.clickButton)
        clickButton.setOnClickListener {
            analytics.track(
                "Workout completed", 
                buildJsonObject { 
                    put("workout_type", "Cardio");
                    put("duration_minutes", 45);
                    put("calories_burned", 380);
                    put("device", "Smartwatch")
                },
            )
        }

        val identifyButton: Button = findViewById(R.id.identifyButton)
        identifyButton.setOnClickListener {
            analytics.identify("user1234", buildJsonObject { put("Name", "Jhon") })
        }

        val identifyWithEmailButton: Button = findViewById(R.id.identifyWithEmailButton)
        identifyWithEmailButton.setOnClickListener {
            analytics.identify("", buildJsonObject { put("Email", "user1234@example.com") })
        }

        val identifyWithAgeButton: Button = findViewById(R.id.identifyWithAgeButton)
        identifyWithAgeButton.setOnClickListener {
            analytics.identify("", buildJsonObject { put("Age", "35") })
        }

        val resetButton: Button = findViewById(R.id.resetButton)
        resetButton.setOnClickListener {
            analytics.reset()
        }
    }

}