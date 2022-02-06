package com.example.mybookings.Fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import androidx.viewpager.widget.ViewPager
import com.example.mybookings.Adapter.MyAdapter
import com.example.mybookings.EventDetails
import com.example.mybookings.R
import com.example.mybookings.Settings
import com.example.mybookings.UserProfile
import com.google.android.material.tabs.TabLayout

class Homepage : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)



        val btn = findViewById<Button>(R.id.homepage_btn_1)

        btn.setOnClickListener() {

            val intent = Intent(this, EventDetails::class.java)
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