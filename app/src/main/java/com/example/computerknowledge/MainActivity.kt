package com.example.computerknowledge


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment


 class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val forgotpage=findViewById<ConstraintLayout>(R.id.forgotpage)
        val backarrow = findViewById<ImageView>(R.id.backarrow)





        backarrow.setOnClickListener({
            val intent=Intent(applicationContext,Login_Ac::class.java)
        })


    }



}






