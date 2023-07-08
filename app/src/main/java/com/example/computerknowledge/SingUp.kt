package com.example.computerknowledge

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.paperdb.Paper

class SingUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)
        val Login=findViewById<TextView>(R.id.login)
        val name=findViewById<EditText>(R.id.name)
        val email=findViewById<EditText>(R.id.Email)
        val pass=findViewById<EditText>(R.id.Passwoed)
        val process=findViewById<ProgressBar>(R.id.pro)
        val sinup_button=findViewById<Button>(R.id.button)
        val auth=FirebaseAuth.getInstance();
        val reference=FirebaseDatabase.getInstance().getReference();
        val texterror=findViewById<TextView>(R.id.texterror)
        Paper.init(applicationContext)
        val paperOB=Paperlib()

        sinup_button.setOnClickListener({
            val gmail_S:String=email.text.toString()
            val pass_S:String=pass.text.toString()
            val name_S : String=name.text.toString()


          if(name_S.isEmpty()||gmail_S.isEmpty()||pass_S.isEmpty()) {
              if(name_S.isEmpty()) {
                  name.setError("fill the dites")
              }
             else if(gmail_S.isEmpty())
              {
                  email.setError("fill the dites")
                 }
              else if(pass_S.isEmpty()) {
                  pass.setError("fill the dites")
              }
          }
          else {
              process.setVisibility(View.VISIBLE)
            auth.createUserWithEmailAndPassword(gmail_S,pass_S).addOnCompleteListener { it->
               if (it.isSuccessful){
                   val uid=auth.uid.toString()

                   Paper.book().write(paperOB.gmailkey,gmail_S)
                   Paper.book().write(paperOB.passlkey,pass_S)


                   reference.child("User").child(uid).child("Gmail").setValue(gmail_S);
                   reference.child("User").child(uid).child("Pass").setValue(pass_S);
                   reference.child("User").child(uid).child("Name").setValue(name_S);
                   reference.child("User").child(uid).child("Image").setValue("null");
                   reference.child("User").child(uid).child("Rank").setValue("0")
                   reference.child("User").child(uid).child("Rating").setValue("0")
                   reference.child("User").child(uid).child("Comment").setValue("null").addOnCompleteListener({itx->
                       if (itx.isSuccessful)
                       {
                           Toast.makeText(applicationContext, "Suscesful", Toast.LENGTH_SHORT).show()
                           val intent1 = Intent(applicationContext, Home_AC::class.java)
                           startActivity(intent1)
                           finishAffinity()
                       }
                   })


               }
               else{

                   process.setVisibility(View.GONE)
                   Toast.makeText(applicationContext,it.exception.toString(),Toast.LENGTH_SHORT).show()
                  texterror.setText(it.exception?.message)
               }

               }
           }
        })



        Login.setOnClickListener({
            val intent=Intent(applicationContext,Login::class.java)
            startActivity(intent)
            finishAffinity()
        })



    }



}