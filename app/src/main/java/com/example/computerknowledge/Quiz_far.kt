package com.example.computerknowledge

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.util.jar.Attributes

class Quiz_far : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_quiz_far, container, false)
        val reference=FirebaseDatabase.getInstance().getReference("User");

        var list=ArrayList<desbord_list>()
        var recyclerView=view.findViewById<RecyclerView>(R.id.quizrecycal);
        var layoutManager= LinearLayoutManager(context)
        val AD=desbord_AD(list)
        recyclerView.layoutManager=layoutManager
        var cunt:Int=1
        var index=0



        var fist_image_rank=view.findViewById<ImageView>(R.id.des_image1)
        var name_fist_rank=view.findViewById<TextView>(R.id.name_fist_rank)
        var gmail_fist_rank=view.findViewById<TextView>(R.id.gmail_fist_rank)
        var fist_rank=view.findViewById<TextView>(R.id.fist_rank)

        var second_image_rank=view.findViewById<ImageView>(R.id.des_image2)
        var name_second_rank=view.findViewById<TextView>(R.id.name_second_rank)
        var gmail_second_rank=view.findViewById<TextView>(R.id.gmail_second_rank)
        var second_rank=view.findViewById<TextView>(R.id.second_rank)

        var Thir_image_rank=view.findViewById<ImageView>(R.id.des_image3)
        var name_Therd_rank=view.findViewById<TextView>(R.id.name_thrid_rank)
        var gmail_Therd_rank=view.findViewById<TextView>(R.id.gmail_third_rank)
        var Therd_rank=view.findViewById<TextView>(R.id.third_rank)

        val list2=ArrayList<desbord_list>()

        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
            for(datas :DataSnapshot in snapshot.children)
            {

                var keyx=datas.key.toString();

                var pic=snapshot.child(keyx).child("Image").getValue().toString()
                var name=snapshot.child(keyx).child("Name").getValue().toString()
                var gmail=snapshot.child(keyx).child("Gmail").getValue().toString()
                var rank=Integer.parseInt(snapshot.child(keyx).child("Rank").getValue().toString())







                    list.add(desbord_list(cunt,name,gmail,pic,rank))
                list2.add(desbord_list(cunt,name,gmail,pic,rank))

                cunt++
            }

                var i=0

                while(i<list.size-1)
                {
                    var j=0
                    while (j<list.size)
                    {
                       if (list[i].level>=list[j].level)
                       {

                           list2[0].name=list[i].name
                           list[i].name=list[j].name
                           list[j].name=list2[0].name

                           list2[0].gmail=list[i].gmail
                           list[i].gmail=list[j].gmail
                           list[j].gmail=list2[0].gmail


                           list2[0].pic=list[i].pic
                           list[i].pic=list[j].pic
                           list[j].pic=list2[0].pic


                           list2[0].level=list[i].level
                           list[i].level=list[j].level
                           list[j].level=list2[0].level
                       }
                        j++
                    }

                    i++
                }

                var x=0
                while (x<list.size)
                {
                    Log.d("daxlist",list.get(x).name)
                    Log.d("daxlist",list.get(x).gmail)
                    Log.d("daxlist",list.get(x).pic)
                    Log.d("daxlist",list.get(x).level.toString())
                    x++
                }

                recyclerView.adapter=AD


                if (list.get(0).pic.toString().equals("null"))
                {
                    fist_image_rank.setImageResource(R.drawable.userx)
                }
                else{
                    val uri:Uri=Uri.parse(list.get(0).pic)
                    Picasso.get().load(uri).into(fist_image_rank)
                }
                name_fist_rank.setText(list.get(0).name)
                name_second_rank.setText(list.get(1).name)
                name_Therd_rank.setText(list.get(2).name)


                if (list.get(1).pic.toString().equals("null"))
                {
                    second_image_rank.setImageResource(R.drawable.userx)
                }
                else{
                    val uri:Uri=Uri.parse(list.get(1).pic)
                    Picasso.get().load(uri).into(second_image_rank)
                }
                gmail_fist_rank.setText(list.get(0).gmail)
                gmail_second_rank.setText(list.get(1).gmail)
                gmail_Therd_rank.setText(list.get(2).gmail)


                if (list.get(2).pic.toString().equals("null"))
                {
                    Thir_image_rank.setImageResource(R.drawable.userx)
                }
                else{
                    val uri:Uri=Uri.parse(list.get(2).pic)
                    Picasso.get().load(uri).into(Thir_image_rank)
                }
                fist_rank.setText(list.get(0).level.toString())
                second_rank.setText(list.get(1).level.toString())
                Therd_rank.setText(list.get(2).level.toString())

                Log.d("daxlix",list.get(0).name)
            }

            override fun onCancelled(error: DatabaseError) {

                Log.d("daxe",  error.message)
            }
        })






        return view;
    }


}