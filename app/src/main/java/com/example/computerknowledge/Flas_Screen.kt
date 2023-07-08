package com.example.computerknowledge

import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.os.postDelayed
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.paperdb.Paper

class Flas_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flas_screen)


                if (execut())
                {

                    Handler(Looper.getMainLooper()).postDelayed(2000){
                        val  paperOB=Paperlib()
                        Paper.init(applicationContext)
                        var reference =FirebaseDatabase.getInstance().getReference()
                        val auth=FirebaseAuth.getInstance()
                        var pass:String?=null
                        var gmail:String?=null
                        var passS:String?=null
                        var gmailS:String?=null
                        val cuser=auth.currentUser?.uid.toString()
                        gmailS=Paper.book().read<String>(paperOB.gmailkey)
                        passS=Paper.book().read<String>(paperOB.passlkey)
                        reference.addValueEventListener(object: ValueEventListener{
                            override fun onDataChange(snapshot: DataSnapshot) {7
                                gmail=snapshot.child("User").child(cuser).child("Gmail").getValue().toString();
                                pass=snapshot.child("User").child(cuser).child("Pass").getValue().toString()
                                Log.d("dafi",cuser)
                                Log.d("dafi",gmail.toString())
                                Log.d("dafi",pass.toString())

                                Log.d("daxx",gmail+"\n"+pass+"\n"+gmailS+"\n"+passS)
                                if (gmailS==null&&passS==null)
                                {
                                    startActivity(Intent(applicationContext,Login_Ac::class.java))
                                    finish()
                                }
                                else{
                                    if (gmailS==gmail&&pass==passS)
                                    {
                                        startActivity(Intent(applicationContext,Home_AC::class.java))
                                        finish()
                                    }
                                    else{
                                        startActivity(Intent(applicationContext,Login_Ac::class.java))
                                        finish()
                                    }
                                }
                            }
                            override fun onCancelled(error: DatabaseError) {
                            }
                        })


                    }

                }

                else{
                    val inent=Intent(applicationContext,No_Internet::class.java)
                    startActivity(intent)
                }



        }

    fun execut():Boolean{
        var con = false
        try {
            val connectivityManager = application.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = connectivityManager.activeNetworkInfo
            con = info != null && info.isConnected && info.isAvailable
        } catch (e: Exception) {
            println(e.message)
        }
        return con
    }

}