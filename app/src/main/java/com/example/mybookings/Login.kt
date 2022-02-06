package com.example.mybookings

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.mybookings.Fragments.Homepage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var loginBtn : Button
    lateinit var emailU : EditText
    lateinit var passwordU : EditText
    lateinit var forgotPassword : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val btn = findViewById<Button>(R.id.RegisterPage)
        val btn2 = findViewById<Button>(R.id.loginButton)
        emailU = findViewById(R.id.loginEmail)
        passwordU = findViewById(R.id.loginPassword)
        forgotPassword = findViewById(R.id.forgotpassword)


        btn.setOnClickListener{

            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
            finish()

        }

        btn2.setOnClickListener {

            doLogin()
        }

        forgotPassword.setOnClickListener {

            val builder : AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view : View = layoutInflater.inflate(R.layout.forgot_password_dialog, null)
            val username = view.findViewById<EditText>(R.id.forgotPasswordDialog)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->

                forgotUserPassword(username)


            })

            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->  })
            builder.show()
            


        }


    }

    private fun forgotUserPassword(username : EditText) {

        if(username.text.toString().isEmpty())
        {
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches())
        {
            return
        }

        auth.sendPasswordResetEmail(username.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Email Sent", Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun doLogin() {


        if(emailU.text.toString().isEmpty())
        {
            emailU.error = "Please enter email"
            emailU.requestFocus()
            return
        }

            if(!Patterns.EMAIL_ADDRESS.matcher(emailU.text.toString()).matches())
            {
                emailU.error = "Incorrect email address"
                emailU.requestFocus()
                return
            }


        if(passwordU.text.toString().isEmpty())
        {
            passwordU.error = "Please enter password"
            passwordU.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(emailU.text.toString(), passwordU.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Login Successful", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    updateUI(null)
                }
            }
    }


    /*
    public override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.currentUser
        updateUI(currentUser)
    }

     */

    private fun updateUI(currentUser : FirebaseUser?)
    {
            if(currentUser != null)
            {
                val intent = Intent(this, Homepage::class.java)
                startActivity(intent)

            }
            else
            {
                Toast.makeText(baseContext, "Login Failed", Toast.LENGTH_SHORT).show()
            }
    }






}