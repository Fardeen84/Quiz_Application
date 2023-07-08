package com.example.computerknowledge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import io.paperdb.Paper

class Result_AC : AppCompatActivity() {
    lateinit var reference: DatabaseReference
    lateinit var auth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val play_agin=findViewById<MaterialButton>(R.id.play_again)
        val rightT:TextView=findViewById(R.id.right_qustion)
        val totleT:TextView=findViewById(R.id.all_question)
        val name_text=findViewById<TextView>(R.id.name_Text)
        val rank_T:TextView=findViewById(R.id.rank_result)
     //   val lib=Paperlib()

        //Paper.init(applicationContext)

        var totel:String=intent.getStringExtra("totel").toString()
        var right:String=intent.getStringExtra("right").toString()
        var rig:Int=Integer.parseInt(right)


        var totel_rank:Int=0
        totleT.setText(totel)
        rightT.setText(right)

        reference=FirebaseDatabase.getInstance().getReference("User")
        auth=FirebaseAuth.getInstance()

        var name:String
        var rank:String
        val uid=auth.uid.toString();
        val intent=Intent(applicationContext,Home_AC::class.java)
        intent.putExtra("uid",uid)

        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                name=snapshot.child(uid).child("Name").getValue().toString()
                rank=snapshot.child(uid).child("Rank").getValue().toString()
                Log.d("darq",rank.toString())

                val ran=Integer.parseInt(rank)
                totel_rank=ran+rig
                name_text.setText(name)
                rank_T.setText("Rank:$totel_rank")
                intent.putExtra("rank",totel_rank.toString())

                }
            override fun onCancelled(error: DatabaseError) {
            }

        })





//       val r:String?=Paper.book().read<String>(lib.rank)
//        Log.d("daxg2",r.toString())
//






        play_agin.setOnClickListener{

            startActivity(intent)
            finishAffinity()
        }

    }

    override fun onBackPressed() {

        Toast.makeText(applicationContext,"Back",Toast.LENGTH_SHORT).show()
        startActivity(intent)
        finishAffinity()
    }
}