package com.example.computerknowledge

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class Start_AC : AppCompatActivity() {
    lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val back_arrow=findViewById<ImageView>(R.id.back_arrow)
        val startQuiz=findViewById<MaterialButton>(R.id.startQuiz)

        var name_quiz=findViewById<TextView>(R.id.name_quiz)
        var des_quiz=findViewById<TextView>(R.id.description_text)
        var imageView=findViewById<ImageView>(R.id.image_ditel)


            var name=intent.getStringExtra("name");
            var dis=intent.getStringExtra("dis");
            var pic=intent.getStringExtra("pic");
            var  key=intent.getStringExtra("key")



        val uri=Uri.parse(pic);

        name_quiz.setText(name)
      des_quiz.setText(dis)
       Picasso.get().load(uri).into(imageView)




        back_arrow.setOnClickListener({
            finish()
        })
        
        startQuiz.setOnClickListener({
            val intent =Intent(applicationContext,qestion_Answer_AC::class.java)
            intent.putExtra("key",key)
            startActivity(intent)
        })
    }
}