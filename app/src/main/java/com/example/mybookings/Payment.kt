package com.example.mybookings
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import com.example.mybookings.Fragments.Homepage
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Payment : AppCompatActivity() {

    lateinit var username : TextInputEditText
    lateinit var spinner : Spinner
    lateinit var number : TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val btn1 = findViewById<Button>(R.id.homepage_home_button)
        val btn2 = findViewById<Button>(R.id.homepage_profile_button)
        val btn3 = findViewById<Button>(R.id.homepage_settings_button)
        val btn = findViewById<Button>(R.id.makePaymentButton)

        username = findViewById(R.id.payment_name)
        number = findViewById(R.id.payment_phone_number)

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
        btn.setOnClickListener(){

            payments()


        }

        //spinner start for payment modes

        spinner = findViewById<Spinner>(R.id.payment_mode_spinner)
        var options = arrayOf("Airtel Money", "Tnm Mpamba")

        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                options[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }


        //spinner finish


    }

    private fun payments()
    {
        val nam = username.text.toString()
        val phone = number.text.toString()
        val mode = spinner.selectedItem.toString()
        val price = "10,000"

        if(nam.isEmpty())
        {
            username.error = "Name is needed"
            username.requestFocus()
            return
        }

        if(phone.isEmpty())
        {
            number.error = "Number is needed"
            number.requestFocus()
            return
        }


        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference().child("Payments")

        val userMap = HashMap<String, String>()
        userMap.put("Name", nam)
        userMap.put("Price", price)
        userMap.put("PaymentMode",mode)
        userMap.put("Phone", phone)


        ref.push().setValue(userMap)
        Toast.makeText(this, "Payment Requested Successfully", Toast.LENGTH_LONG).show()

        val intent = Intent(this, Payment_Notification::class.java)
        startActivity(intent)



    }
}