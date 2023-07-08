package com.example.computerknowledge

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isGone
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

import io.paperdb.Paper


class Profil_far : Fragment() {
    lateinit var Logout: CardView
    lateinit  var Feedback: CardView
    lateinit var serivices: CardView
    lateinit var Rateus: CardView
    lateinit  var Share: CardView




    lateinit var reference: DatabaseReference
    lateinit var auth:FirebaseAuth
    lateinit var storage:StorageReference
    lateinit var name_profile:TextView
    lateinit var gmail_profile:TextView
    lateinit var rank_profile:TextView
    lateinit var image :ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_profil_far, container, false)
      name_profile =view.findViewById(R.id.name_Profile)
       gmail_profile =view.findViewById(R.id.gmail_Profile)
       rank_profile =view.findViewById(R.id.rank_Profile)
    image =view.findViewById(R.id.image)
       val add_image:ImageView=view.findViewById(R.id.add_image)
        val progres:ProgressBar=view.findViewById(R.id.imagr_progr)



        Logout=view.findViewById(R.id.logout)
        Feedback=view.findViewById(R.id.feedback)
        serivices=view.findViewById(R.id.serivices)
        Rateus=view.findViewById(R.id.rateus)
        Share=view.findViewById(R.id.share)




        progres.setVisibility(View.VISIBLE)


        auth=FirebaseAuth.getInstance()
        reference=FirebaseDatabase.getInstance().getReference()
        storage=FirebaseStorage.getInstance().getReference()


        val user_id =auth.currentUser?.uid.toString()


        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val name=snapshot.child("User").child(user_id).child("Name").getValue()
                val gmail=snapshot.child("User").child(user_id).child("Gmail").getValue()
                val rank=snapshot.child("User").child(user_id).child("Rank").getValue()
                val image_url:Uri=Uri.parse(snapshot.child("User").child(user_id).child("Image").getValue().toString())




                if(image_url.toString().equals("null"))
                {
                    image.setImageResource(R.drawable.userx)
                    progres.setVisibility(View.GONE)
                }else{

                    Picasso.get().load(image_url).into(image)
                    progres.setVisibility(View.GONE)
                }


                Log.d("daxurl2",image_url.toString())
                Log.d("dax5",name.toString()+"\n"+gmail)

                name_profile.setText(name.toString())
                gmail_profile.setText(gmail.toString())
                rank_profile.setText(rank.toString())




            }

             override fun onCancelled(error: DatabaseError) {

            }

        });



        add_image.setOnClickListener {
            val intent=Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(Intent.createChooser(intent,"imaf"),123)


                    }


        //setting

        Logout.setOnClickListener {
            Toast.makeText(view.context,"Log Out", Toast.LENGTH_SHORT).show()

            dilog(view)

        }
        Feedback.setOnClickListener {
            Toast.makeText(view.context,"Feed Back", Toast.LENGTH_SHORT).show()
            feedback()
        }

        serivices.setOnClickListener {
            Toast.makeText(view.context,"servic", Toast.LENGTH_SHORT).show()
        }

        Rateus.setOnClickListener {
            Toast.makeText(view.context,"Rateus", Toast.LENGTH_SHORT).show()
           //rattig


            var dialog=Rating_dailog(view.context)
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }

        Share.setOnClickListener {
            Toast.makeText(view.context,"Share", Toast.LENGTH_SHORT).show()

            sher()
        }


        return view
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        auth=FirebaseAuth.getInstance()
        reference=FirebaseDatabase.getInstance().getReference("User")
        storage=FirebaseStorage.getInstance().getReference()

        val uid =auth.uid.toString()



        if (resultCode==-1)
        {
            if (requestCode==123)
            {

                if(data!=null)
                {
                    val uri :Uri=Uri.parse(data.data.toString())
                    image.setImageURI(uri)

                    storage.child("User").child(uid).child("image").putFile(uri).addOnSuccessListener {

                        storage.child("User").child(uid).child("image").downloadUrl.addOnSuccessListener({it->

                            Log.d("daxuri",it.toString())

                            reference.child(uid).child("Image").setValue(it.toString())
                        })
                    }
                }
            }
        }
    }


    //Setting Function


    private fun sher() {



        var intent=Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_SUBJECT,"Computer Knowle Appliction")
        intent.putExtra(Intent.EXTRA_TEXT,"https://firebasestorage.googleapis.com/v0/b/stroge-parctis.appspot.com/o/image?alt=media&token=151e656c-fd93-4eb9-90b5-f1dda768ab3e")
        startActivity(Intent.createChooser(intent,"Share"))

    }


    private fun feedback() {



        var intent=Intent(Intent.ACTION_SENDTO)
        intent.setData(Uri.parse("mailto:fardeenkhan51214@gmail.com"))
        startActivity(Intent.createChooser(intent,"Share"))

    }

    private fun dilog(view : View) {

        val paperob=Paperlib()
        view?.let { Paper.init(it.context) }
        reference=FirebaseDatabase.getInstance().getReference()
        auth= FirebaseAuth.getInstance()
        val id=auth.uid.toString()

        var name:String?=null
        val dilogbulder=layoutInflater.inflate(R.layout.logout_delog,null)
        val dialog= view?.context?.let { Dialog(it) }
        dialog?.setContentView(dilogbulder)
        dialog?.setCancelable(true)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.show()



        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                name=snapshot.child("User").child(id).child("Name").getValue().toString()

                val name_Text=dilogbulder.findViewById<TextView>(R.id.name_Text)

                name_Text.setText("Hey..$name")
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        val cancle=dilogbulder.findViewById<TextView>(R.id.cancle)
        val yse=dilogbulder.findViewById<TextView>(R.id.Yes)

        cancle.setOnClickListener({
            dialog?.cancel()
        })

        yse.setOnClickListener({

            Paper.book().write(paperob.gmailkey,"")
            Paper.book().write(paperob.passlkey,"")
            val intent=Intent(view?.context,Login_Ac::class.java)
            startActivity(intent)
            activity?.finishAffinity()

        })


    }

}