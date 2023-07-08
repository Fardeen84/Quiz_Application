package com.example.computerknowledge

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Home_AC : AppCompatActivity() {

    val homeFar=Home_far()
    val Profil_far=Profil_far()
    val Quiz_far=Quiz_far()
    lateinit var reference: DatabaseReference
    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



            val bottomnev = findViewById<BottomNavigationView>(R.id.bottom_nev)



            val rank=intent.getStringExtra("rank")
            val uid=intent.getStringExtra("uid")


            if(uid!=null&&rank!=null)
            {
                reference=FirebaseDatabase.getInstance().getReference("User")

                reference.child(uid).child("Rank").setValue(rank)
            }



            opnfar(homeFar)
            bottomnev.setOnItemSelectedListener {
                it
                when (it.itemId) {
                    R.id.home -> opnfar(homeFar)
                    R.id.quiz -> opnfar(Quiz_far)
                    R.id.profile -> opnfar(Profil_far)

                }
                true
            }



    }

     fun opnfar(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.fram,fragment);
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onBackPressed() {


        reference=FirebaseDatabase.getInstance().getReference()
        auth=FirebaseAuth.getInstance()

        val id=auth.currentUser?.uid.toString()
        var name:String?=null



        val dilogbid=layoutInflater.inflate(R.layout.dilog_box,null)
        val dialog=Dialog(this)
        dialog.setContentView(dilogbid)
        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()


        reference.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                name=snapshot.child("User").child(id).child("Name").getValue().toString()

                val name_Text=dilogbid.findViewById<TextView>(R.id.name_Text)

                name_Text.setText("Hey..$name")
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        val cancle=dilogbid.findViewById<TextView>(R.id.cancle)
        val yse=dilogbid.findViewById<TextView>(R.id.Yes)

        cancle.setOnClickListener({
            dialog.cancel()
        })

        yse.setOnClickListener({
            finishAffinity()
        })




    }


}
