package com.example.mybookings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mybookings.Fragments.Homepage
import com.google.firebase.auth.FirebaseAuth

class Settings : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var btn1 : Button
    private lateinit var btn2 : Button
    private lateinit var btn3 : Button
    lateinit var out : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


         btn1 = findViewById(R.id.homepage_home_button)
         btn2 = findViewById(R.id.homepage_profile_button)
         btn3 = findViewById(R.id.homepage_settings_button)


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


        auth = FirebaseAuth.getInstance()

        out = findViewById(R.id.logout)
        out.setOnClickListener {

            auth.signOut()
            signOutUser()
            onBackPressed()

        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    private fun signOutUser() {


        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()

    }
}