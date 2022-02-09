package com.example.mybookings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mybookings.Fragments.Homepage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Registration : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var registerBtn : Button
    lateinit var emailR : EditText
    lateinit var passwordR : EditText
    lateinit var usernameR : EditText
    lateinit var registerBackBtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_registration)

        registerBtn = findViewById(R.id.RegisterUser)
        registerBackBtn = findViewById(R.id.RegisterBackButton)
        emailR = findViewById(R.id.userRegisterEmail)
        passwordR = findViewById(R.id.userRegisterPassword)
        usernameR = findViewById(R.id.registerUsername)

        auth = FirebaseAuth.getInstance()

        registerBtn.setOnClickListener {
            signUpUser()


       }

        registerBackBtn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }


    }



    private fun signUpUser()
    {
        val name = usernameR.text.toString()
        val mail = emailR.text.toString()
        val pass = passwordR.text.toString()

        if(name.isEmpty())
        {
            usernameR.error = "Please enter username"
            usernameR.requestFocus()
            return
        }

        if(name.length < 4)
        {
            usernameR.error = "Username can not be less than 4 characters"
            usernameR.requestFocus()
            return
        }

        if(mail.isEmpty())
        {
            emailR.error = "Please enter email"
            emailR.requestFocus()
            return
        }


        if(!Patterns.EMAIL_ADDRESS.matcher(emailR.text.toString()).matches())
        {
            emailR.error = "Incorrect email address"
            emailR.requestFocus()
            return
        }



        if(pass.isEmpty())
        {
            passwordR.error = "Please enter password"
            passwordR.requestFocus()
            return


        }
        if(pass.length < 8)
        {
            passwordR.error = "password can't be less than 8 characters"
            passwordR.requestFocus()
            return
        }



        auth.createUserWithEmailAndPassword(emailR.text.toString(), passwordR.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Toast.makeText(baseContext, "Sign Up successful", Toast.LENGTH_SHORT).show()


                    val int1 = Intent(this, Homepage::class.java)
                    startActivity(int1)
                    finish()

                } else {

                    Toast.makeText(baseContext, "Signup failed. Try another time.",
                        Toast.LENGTH_SHORT).show()
                    finish()

                }
            }


        //realtime database

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val userId = ref.push().key

        val user = Users(userId, name , mail, pass)

            ref.child(userId.toString()).setValue(user).addOnCompleteListener{
                        Toast.makeText(applicationContext, "Saved Successfully", Toast.LENGTH_LONG).show()

        }



    }




}