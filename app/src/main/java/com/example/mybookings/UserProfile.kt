package com.example.mybookings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mybookings.Fragments.Homepage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase

class UserProfile : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var username : EditText
    lateinit var email : EditText
    lateinit var password : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        auth = FirebaseAuth.getInstance()

        val btn1 = findViewById<Button>(R.id.homepage_home_button)
        val btn2 = findViewById<Button>(R.id.homepage_profile_button)
        val btn3 = findViewById<Button>(R.id.homepage_settings_button)
        val btn4 = findViewById<Button>(R.id.save_changes)

        username = findViewById(R.id.edit_username)
        email = findViewById(R.id.edit_email)
        password = findViewById(R.id.edit_password)


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

        btn4.setOnClickListener {

            updateUserDetails()

        }


    }

    private fun updateUserDetails() {

        val name = username.text.toString()


        if(username.text.toString().isEmpty() && email.text.toString().isEmpty() && password.text.toString().isEmpty())
        {
            username.error = "Can't send blank information"
            email.error = "Can't send blank information"
            password.error = "Can't send blank information"

            username.requestFocus()
            email.requestFocus()
            password.requestFocus()
        }



        val user = FirebaseAuth.getInstance().currentUser ?: return

        val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(name).build()

        user.updateProfile(profileUpdates).addOnCompleteListener(this) { task ->

            if(task.isSuccessful)
            {
                Toast.makeText(this, "Username Updated.", Toast.LENGTH_LONG).show()
            }

        }


    }
}


