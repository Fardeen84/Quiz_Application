package com.example.computerknowledge

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import java.lang.ref.Reference

class Home_far : Fragment() {


    lateinit var reference: DatabaseReference
    lateinit var auth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home_far, container, false)
        val imageView:ImageView=view.findViewById(R.id.profilehome)

        reference=FirebaseDatabase.getInstance().getReference()
        auth=FirebaseAuth.getInstance()

        val uid=auth.uid.toString()


        val notification=view.findViewById<ImageView>(R.id.notification)
        val list=ArrayList<course_list>();

        val recyclerView=view.findViewById<RecyclerView>(R.id.recycal)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val AD=course_AD(list)


        reference.child("User").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val imag=snapshot.child(uid).child("Image").getValue().toString()

                if (imag=="null")
                {
                    imageView.setImageResource(R.drawable.userx)
                }
                else{
                    Picasso.get().load(imag).into(imageView)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        reference.child("Courses").addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(snp:DataSnapshot in snapshot.children)
                {
                    var key=snp.key.toString()

                    Log.d("daxcu",key)
                    var name=snapshot.child(key).child("Name").getValue().toString()
                    var des=snapshot.child(key).child("Des").getValue().toString()
                    var uri=Uri.parse(snapshot.child(key).child("Image").getValue().toString())
                    list.add(course_list(name,des,uri,key))
                }

                recyclerView.adapter=AD
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })







            notification.setOnClickListener({
            val intent=Intent(view.context,Notification_AC::class.java)
            startActivity(intent)
        })
        return view
    }


}