
package com.example.computerknowledge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.paperdb.Paper


class Login_Ac : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        val gmal=findViewById<EditText>(R.id.Email)
        val pass=findViewById<EditText>(R.id.Passwoed)
        val button=findViewById<Button>(R.id.buttonx)
        val create=findViewById<TextView>(R.id.create)
        val forgot=findViewById<ImageView>(R.id.forgot)
        val process=findViewById<ProgressBar>(R.id.pro)
        val auth= FirebaseAuth.getInstance();
        val reference= FirebaseDatabase.getInstance().getReference();
        val curentUser=auth.currentUser?.uid.toString();
        val  texterror=findViewById<TextView>(R.id.texterror)
        Paper.init(applicationContext)
        val PaperOB=Paperlib();


        button.setOnClickListener({it->
            process.setVisibility(it.visibility)
            var email:String = gmal.text.toString()
            var passw:String = pass.text.toString()
            Toast.makeText(applicationContext, "$email\n $passw", Toast.LENGTH_SHORT).show()

            if(email.isEmpty()&&passw.isEmpty())
            {
                if (email.isEmpty()){
                    gmal.setError("Gmail is Empty")
                    process.setVisibility(View.GONE)

                }
                else if (passw.isEmpty())
                {
                    process.setVisibility(View.GONE)
                    pass.setError("password is Empty")
                }
            }
            else{
                auth.signInWithEmailAndPassword(email,passw).addOnCompleteListener({task->


                    if (task.isSuccessful){
                        Paper.book().write(PaperOB.gmailkey,email)
                        Paper.book().write(PaperOB.passlkey,passw)
                        val intent= Intent(applicationContext,Home_AC::class.java)
                        startActivity(intent)
                        Toast.makeText(applicationContext, "Susscful..", Toast.LENGTH_SHORT).show();
                      finishAffinity()
                    }
                    else{
                        process.setVisibility(View.GONE)
                        texterror.setText(task.exception?.message)
                    }
                })
            }


        })

        create.setOnClickListener({
            val intent = Intent(applicationContext, SingUp::class.java)
            startActivity(intent)

        })

        forgot.setOnClickListener({

            Toast.makeText(applicationContext, "Fragmet", Toast.LENGTH_SHORT).show();


        })




    }
}