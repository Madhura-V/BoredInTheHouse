package com.mv.boredinthehouse

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login.*
import java.lang.NumberFormatException

//Compile-Time Constants :- const
const val ExtraMessage = "LoginValue"

class Login : AppCompatActivity() {

    private var strName = ""
    private var strAge: Int = 0
    private val tag = "Login"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val btnClick = findViewById<Button>(R.id.btnLogin)

        btnClick.setOnClickListener {
            checkAge()
        }
    }

    private fun checkAge() {
        strName = edEnterName.text.toString()
        Log.e("$tag: strName ", strName)

        try {
            strAge = edEnterAge.text.toString().toInt()
            Log.e("$tag:strAge:", strAge.toString())
        } catch (nfe: NumberFormatException) {
        }

        if (edEnterAge.text.toString().isEmpty()) {
            Toast.makeText(applicationContext, "Enter age!", Toast.LENGTH_SHORT).show()

        } else if (strAge >= 18 && edEnterAge.text.toString().isNotEmpty()) {

            // Scope functions, and Lambda expression
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(ExtraMessage, strName)
            }
            startActivity(intent)

            Toast.makeText(applicationContext, "Welcome $strName", Toast.LENGTH_SHORT).show()   //String template

        } else if (strAge <= 18) {
            //Named Argument
            showToast(context = applicationContext,
                message = "You are not allowed to view this",
                duration = Toast.LENGTH_SHORT)

            //Required parameter
//            showToast(message = "You are not allowed to view this")
        }
    }

//    Required parameter for function is:- message : String
//    Default values for function is:- duration: Int = Toast.LENGTH_SHORT ,
//    and context: Context = applicationContext
    private fun showToast(context: Context = applicationContext, message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }

}