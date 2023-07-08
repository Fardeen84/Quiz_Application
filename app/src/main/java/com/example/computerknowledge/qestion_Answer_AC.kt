package com.example.computerknowledge

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class qestion_Answer_AC : AppCompatActivity() {




    //var option= arrayOf(arrayOf("A.Hacking to steal sensitive information","B.Hacking to identify vulnerabilities in a system","C.Hacking to disrupt a system’s functionality","D.Hacking to cause damage to a system"),
//        arrayOf("A. To cause damage to a system","B. To gain unauthorized access to a system","C.To identify and fix security vulnerabilities","D.To steal sensitive information"),
//        arrayOf("A.Ethical hacking is legal and sanctioned, while malicious hacking is illegal and unsanctioned.","B.Ethical hacking only involves finding vulnerabilities, while malicious hacking involves exploiting them.","C.Ethical hacking is done with the permission of the system owner, while malicious hacking is done without permission.","D.There is no difference between ethical hacking and malicious hacking."),
//        arrayOf("A.Social engineering","B.Penetration testing","C.SQL injection","D.Denial of service attack"))



  //  var A= arrayOf("B.Hacking to identify vulnerabilities in a system","C.To identify and fix security vulnerabilities","A.Ethical hacking is legal and sanctioned, while malicious hacking is illegal and unsanctioned.","D.Denial of service attack")

    lateinit var reference: DatabaseReference
    lateinit var auth: FirebaseAuth
    lateinit var Q_list:ArrayList<String>
lateinit var op1:ArrayList<String>
lateinit var op2:ArrayList<String>
lateinit var op3:ArrayList<String>
lateinit var op4:ArrayList<String>
lateinit var As:ArrayList<String>
 var As_tem:String?=null

  var right_A=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qestion_answer)

        val option1=findViewById<MaterialButton>(R.id.option1)
        val option2=findViewById<MaterialButton>(R.id.option2)
        val option3=findViewById<MaterialButton>(R.id.option3)
        val option4=findViewById<MaterialButton>(R.id.option4)
        val nextQ=findViewById<MaterialButton>(R.id.nextQ);

        var questionText=findViewById<TextView>(R.id.question)
        var Current_question=findViewById<TextView>(R.id.currentQ)
        var Totel_question=findViewById<TextView>(R.id.totelQ)
        var num=1



        val key:String=intent.getStringExtra("key").toString()
        val reference=FirebaseDatabase.getInstance().getReference("Courses")

        Q_list=ArrayList<String>()

        op1=ArrayList<String>()
        op2=ArrayList<String>()
        op3=ArrayList<String>()
        op4=ArrayList<String>()
        As=ArrayList<String>()


        reference.child(key).child("Question").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                var q:String


                var i=0
               for (sn:DataSnapshot in snapshot.children)
               {
                   q=sn.key.toString()


                   Q_list.add(q)
               while (i<Q_list.size)
               {


                   val a= snapshot.child(Q_list.get(i)).child("A").getValue().toString()
                   val b= snapshot.child(Q_list.get(i)).child("B").getValue().toString()
                   val c= snapshot.child(Q_list.get(i)).child("C").getValue().toString()
                   val d= snapshot.child(Q_list.get(i)).child("D").getValue().toString()
                   val r= snapshot.child(Q_list.get(i)).child("R").getValue().toString()
                   //val arr= arrayOf(arrayOf(a,b,c,d))

                   Log.d("daxr2",a+"\n"+b+"\n"+c+"\n"+d)

                   op1.add(a)
                   op2.add(b)
                   op3.add(c)
                   op4.add(d)
                   As.add(r)
                   i++
               }
               }



                Log.d("daxr",op1.toString())







                var curent=0
                var totel=Q_list.size

                Totel_question.setText(totel.toString())
                Current_question.setText(num.toString())

                questionText.setText(num.toString()+"."+Q_list.get(curent))
                option1.setText(op1[curent])
                option2.setText(op2[curent])
                option3.setText(op3[curent])
                option4.setText(op4[curent])


                nextQ.setOnClickListener({
                   if (As_tem!=null) {

                       var i = 0
                       while (i < As.size) {
                           if (As_tem.equals(As.get(i))) {
                               right_A++
                           }
                           i++
                       }


                       curent++
                       num++
                       option1.setBackgroundColor(resources.getColor(R.color.white))
                       option2.setBackgroundColor(resources.getColor(R.color.white))
                       option3.setBackgroundColor(resources.getColor(R.color.white))
                       option4.setBackgroundColor(resources.getColor(R.color.white))



                       if (curent < totel) {
                           questionText.setText(num.toString() + "." + Q_list.get(curent))
                           option1.setText(op1[curent])
                           option2.setText(op2[curent])
                           option3.setText(op3[curent])
                           option4.setText(op4[curent])


                           Current_question.setText("$num")
                       }

                       if (curent == totel) {
                           Log.d("daxb", right_A.toString())
                           var intent = Intent(applicationContext, Result_AC::class.java)
                           intent.putExtra("totel", "${totel}")
                           intent.putExtra("right", "${right_A}")
                           startActivity(intent)
                           finishAffinity()
                       }
                       As_tem=null
                   }
                    else{
                        Toast.makeText(applicationContext,"Pless Selecct The Answer",Toast.LENGTH_SHORT).show()
                   }
                })


            }

            override fun onCancelled(error: DatabaseError) {

            }

        })






//        Current_question.setText(1.toString())

//
        option1.setOnClickListener(this::onCreat)
        option2.setOnClickListener(this::onCreat)
        option3.setOnClickListener(this::onCreat)
        option4.setOnClickListener(this::onCreat)
//


    }


    fun onCreat( view:View)
    {
        val option1=findViewById<MaterialButton>(R.id.option1)
        val option2=findViewById<MaterialButton>(R.id.option2)
        val option3=findViewById<MaterialButton>(R.id.option3)
        val option4=findViewById<MaterialButton>(R.id.option4)
        val button_select:MaterialButton
        var button=view

        if(button.id==R.id.option1||button.id==R.id.option2||button.id==R.id.option3||button.id==R.id.option4)
        {

            option1.setBackgroundColor(resources.getColor(R.color.white))
            option2.setBackgroundColor(resources.getColor(R.color.white))
            option3.setBackgroundColor(resources.getColor(R.color.white))
            option4.setBackgroundColor(resources.getColor(R.color.white))
            button.setBackgroundColor(resources.getColor(R.color.blu))


            var teg=button.getTag().toString()


            if (teg.equals("1"))
            {
               As_tem=option1.text.toString()
            }

            if (teg.equals("2"))
            {
                As_tem=option2.text.toString()
            }

            if (teg.equals("3"))
            {
                As_tem=option3.text.toString()
            }

            if (teg.equals("4"))
            {
                As_tem=option4.text.toString()
            }





        }
    }

    override fun onBackPressed() {

        auth=FirebaseAuth.getInstance()
        reference= FirebaseDatabase.getInstance().getReference()

        val id=auth.currentUser?.uid.toString()




        var dialogbilder=layoutInflater.inflate(R.layout.dilog_qusetion,null)
        val dialog=Dialog(this)
        dialog.setContentView(dialogbilder)
        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val yse=dialogbilder.findViewById<TextView>(R.id.Yes)
        val cancel=dialogbilder.findViewById<TextView>(R.id.cancle)



        var name:String?=null
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val name_Text=dialogbilder.findViewById<TextView>(R.id.name_Text)
                name=snapshot.child("User").child(id).child("Name").getValue().toString()

                name_Text.setText("Hey..$name")
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


        yse.setOnClickListener({

            finish()
        })

        cancel.setOnClickListener({
            dialog.cancel()
        })

        dialog.show()

    }
}