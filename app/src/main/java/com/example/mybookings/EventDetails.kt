package com.example.mybookings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mybookings.Fragments.Homepage

class EventDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        val btn = findViewById<Button>(R.id.event_btn_1)
        btn.setOnClickListener() {

            val intent = Intent(this, Payment::class.java)
            startActivity(intent)
        }

        val btn1 = findViewById<Button>(R.id.homepage_home_button)
        val btn2 = findViewById<Button>(R.id.homepage_profile_button)
        val btn3 = findViewById<Button>(R.id.homepage_settings_button)


        btn1.setOnClickListener() {

            val intent = Intent(this, Homepage::class.java)
            startActivity(intent)
        }
        btn2.setOnClickListener() {

            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }
        btn3.setOnClickListener() {

            val intent = Intent(this, UserProfile::class.java)
            startActivity(intent)
        }

    }
}